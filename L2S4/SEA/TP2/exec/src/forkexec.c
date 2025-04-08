/**
 * @file forkexec.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>

#define EXEC_NAME "forkexec"

int main(void){
	int exitCode = EXIT_SUCCESS;

	int fpid = fork();
	switch(fpid){
		case -1:
			perror(EXEC_NAME), exitCode =  EXIT_FAILURE;
			break;
		case 0:;
			int err = execlp("ps", "ps", "aux", NULL);

			if(err == -1)
				perror(EXEC_NAME), exitCode = EXIT_FAILURE;
			break;
		default:;
			int status;
			pid_t pid = waitpid(fpid, &status, 0);

			if(pid == -1)
				perror(EXEC_NAME), exitCode = EXIT_FAILURE;
			else
				printf("Child exit status: %d\n", WEXITSTATUS(status));
	}

	return exitCode;
}