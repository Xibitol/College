/**
 * @file pipe.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>

#define EXEC_NAME "pipe"
#define EXEC_WRITER_NAME "pipe-writer"
#define EXEC_READER_NAME "pipe-reader"
#define BUF_SIZE 2048

enum PipeFDS{
	READ = 0,
	WRITE = 1
};

static int writeWorker(const char* src, int pipefds[]){
	unsigned int exitCode = EXIT_SUCCESS;
	int fdIn;
	char buffer[BUF_SIZE];

	if(close(pipefds[READ]) == -1)
		perror(EXEC_NAME), exitCode = EXIT_FAILURE;

	if((fdIn = open(src, O_RDONLY)) == -1)
		perror(EXEC_WRITER_NAME), exitCode = EXIT_FAILURE;
	else{
		ssize_t size = -1;
		do{
			if((size = read(fdIn, buffer, BUF_SIZE)) == -1)
				perror(EXEC_WRITER_NAME), exitCode = EXIT_FAILURE;

			if(size >= 0 && write(pipefds[WRITE], buffer, size) == -1)
				perror(EXEC_WRITER_NAME), exitCode = EXIT_FAILURE;
		}while(size > 0);

		if(close(fdIn) == -1)
			perror(EXEC_WRITER_NAME), exitCode = EXIT_FAILURE;
	}

	if(close(pipefds[WRITE]) == -1)
		perror(EXEC_NAME), exitCode = EXIT_FAILURE;

	return exitCode;
}

static int readWorker(const char* dest, int pipefds[]){
	unsigned int exitCode = EXIT_SUCCESS;
	int fdOut;
	char buffer[BUF_SIZE];

	if(close(pipefds[WRITE]) == -1)
		perror(EXEC_NAME), exitCode = EXIT_FAILURE;

	if((fdOut = open(dest, O_WRONLY | O_CREAT | O_TRUNC, 0666)) == -1)
		perror(EXEC_READER_NAME), exitCode = EXIT_FAILURE;
	else{
		ssize_t size = -1;
		do{
			if((size = read(pipefds[READ], buffer, BUF_SIZE)) == -1)
				perror(EXEC_READER_NAME), exitCode = EXIT_FAILURE;

			if(size >= 0 && write(fdOut, buffer, size) == -1)
				perror(EXEC_READER_NAME), exitCode = EXIT_FAILURE;
		}while(size > 0);

		if(close(fdOut) == -1)
			perror(EXEC_READER_NAME), exitCode = EXIT_FAILURE;
	}

	if(close(pipefds[READ]) == -1)
		perror(EXEC_READER_NAME), exitCode = EXIT_FAILURE;

	return exitCode;
}

int main(const int argc, const char *const argv[]){
	unsigned int exitCode = EXIT_SUCCESS;

	if(argc != 3){
		printf("Usage: pipe <src> <dest>\n");
		return exitCode;
	}

	const char* src = argv[1];
	const char* dest = argv[2];

	{
		int pipefds[2];

		if(pipe(pipefds) == -1){
			perror(EXEC_NAME), exitCode = EXIT_FAILURE;
			return exitCode;
		}

		// Writing worker
		const pid_t writerFPID = fork();
		if(writerFPID == -1)
			perror(EXEC_NAME), exitCode = EXIT_FAILURE;
		else if(writerFPID == 0)
			return writeWorker(src, pipefds);

		// Reading worker
		const pid_t readerFPID = fork();
		if(readerFPID == -1)
			perror(EXEC_NAME), exitCode = EXIT_FAILURE;
		else if(readerFPID == 0)
			return readWorker(dest, pipefds);

		// Closes pipes on parent
		if(close(pipefds[READ]) == -1)
			perror(EXEC_NAME), exitCode = EXIT_FAILURE;

		if(close(pipefds[WRITE]) == -1)
			perror(EXEC_NAME), exitCode = EXIT_FAILURE;

		// Waiting for workers
		if(waitpid(writerFPID, NULL, 0) == -1)
			perror(EXEC_NAME), exitCode = EXIT_FAILURE;

		if(waitpid(readerFPID, NULL, 0) == -1)
			perror(EXEC_NAME), exitCode = EXIT_FAILURE;
	}

	return exitCode;
}