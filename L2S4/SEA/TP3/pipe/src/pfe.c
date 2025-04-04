/**
 * @file pfe.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>

#define EXEC_NAME "pfe"

enum PipeFDS{
	READ = 0,
	WRITE = 1
};

static int execOn(char* const cmd[], const int fdIn, const int fdOut){
	if(fdIn != STDIN_FILENO){
		if(close(STDIN_FILENO) == -1 || dup2(fdIn, STDIN_FILENO) == -1)
			return -1;
	}

	if(fdOut != STDOUT_FILENO){
		if(close(STDOUT_FILENO) == -1 || dup2(fdOut, STDOUT_FILENO) == -1)
			return -1;
	}

	return execvp(cmd[0], cmd);
}

int main(){
	unsigned int exitCode = EXIT_SUCCESS;

	{
		int fpid;

		// Pipe: First | Second
		int pipe12fds[2];
		if(pipe(pipe12fds) == -1){
			perror(EXEC_NAME), exitCode = EXIT_FAILURE;
			return exitCode;
		}

		// First (ps -aux)
		fpid = fork();
		if(fpid == -1)
			perror(EXEC_NAME), exitCode = EXIT_FAILURE;
		else if(fpid == 0){
			if(close(pipe12fds[READ]) == -1)
				perror(EXEC_NAME), exitCode = EXIT_FAILURE;

			char* cmd[] = {"ps", "-aux"};
			if(execOn(cmd, STDIN_FILENO, pipe12fds[WRITE]) != -1)
				perror(EXEC_NAME), exitCode = EXIT_FAILURE;

			// If execOn doesn't worked
			if(close(pipe12fds[WRITE]) == -1)
				perror(EXEC_NAME), exitCode = EXIT_FAILURE;

			return exitCode;
		}

		if(close(pipe12fds[WRITE]) == -1)
			perror(EXEC_NAME), exitCode = EXIT_FAILURE;

		// Pipe: Second | Third
		int pipe23fds[2];
		if(pipe(pipe23fds) == -1){
			perror(EXEC_NAME), exitCode = EXIT_FAILURE;

			if(close(pipe12fds[READ]) == -1)
				perror(EXEC_NAME), exitCode = EXIT_FAILURE;

			return exitCode;
		}

		// Second (grep root)
		fpid = fork();
		if(fpid == -1)
			perror(EXEC_NAME), exitCode = EXIT_FAILURE;
		else if(fpid == 0){
			if(close(pipe23fds[READ]) == -1)
				perror(EXEC_NAME), exitCode = EXIT_FAILURE;

			char* cmd[] = {"grep", "-E", "^root"};
			if(execOn(cmd, pipe12fds[READ], pipe23fds[WRITE]) != -1)
				perror(EXEC_NAME), exitCode = EXIT_FAILURE;

			// If execOn doesn't worked
			if(close(pipe12fds[READ]) == -1)
				perror(EXEC_NAME), exitCode = EXIT_FAILURE;
			if(close(pipe23fds[WRITE]) == -1)
				perror(EXEC_NAME), exitCode = EXIT_FAILURE;

			return exitCode;
		}

		if(close(pipe12fds[READ]) == -1)
			perror(EXEC_NAME), exitCode = EXIT_FAILURE;
		if(close(pipe23fds[WRITE]) == -1)
			perror(EXEC_NAME), exitCode = EXIT_FAILURE;

		// Third (wc -l)
		fpid = fork();
		if(fpid == -1)
			perror(EXEC_NAME), exitCode = EXIT_FAILURE;
		else if(fpid == 0){
			char* cmd[] = {"wc", "-l"};
			if(execOn(cmd, pipe23fds[READ], STDOUT_FILENO) != -1)
				perror(EXEC_NAME), exitCode = EXIT_FAILURE;

			// If execOn doesn't worked
			if(close(pipe23fds[READ]) == -1)
				perror(EXEC_NAME), exitCode = EXIT_FAILURE;

			return exitCode;
		}

		if(close(pipe23fds[READ]) == -1)
			perror(EXEC_NAME), exitCode = EXIT_FAILURE;

		// Waiting for results
		if(waitpid(fpid, NULL, 0) == -1)
			perror(EXEC_NAME), exitCode = EXIT_FAILURE;
	}

	return exitCode;
}