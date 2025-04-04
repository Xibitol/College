/**
 * @file cp.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdbool.h>
#include <string.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdio.h>

#define EXEC_NAME "cp"
#define BUF_SIZE 2048

static off_t reverseSeek(const int fd, const off_t offset, const off_t max){
	return lseek(fd, -(max < offset ? max : offset), SEEK_END);
}

static char copy(const int fdIn, const int fdOut, const bool reversed){
	off_t pos = 0;

	if(reversed){
		off_t pos = reverseSeek(fdIn, 0, 0);

		if((pos = reverseSeek(fdIn, BUF_SIZE, pos)) == -1)
			return -1;
	}

	char buffer[BUF_SIZE];
	ssize_t size = -1;
	do{
		if((size = read(fdIn, buffer, BUF_SIZE)) == -1)
			return -1;

		if(reversed){
			char temp = '\0';
			for(int i = 0; i < size/2; i++){
				temp = buffer[i];
				buffer[i] = buffer[size - i - 1];
				buffer[size - i -1] = temp;
			}
		}

		if(size != 0 && (size = write(fdOut, buffer, size)) == -1)
			return -1;

		if(reversed && (pos = reverseSeek(fdIn, BUF_SIZE*2, pos)) == -1)
			return -1;
	}while((reversed || size != 0) && (!reversed || pos != 0));

	return 0;
}

int main(const int argc, const char *const argv[]){
	unsigned int exitCode = EXIT_SUCCESS;

	setbuf(stdout, NULL);

	if(argc != 3 && argc != 4){
		printf("Usage: cp <src> <dest>\n  or: cp <src> <dest> --reversed\n");
		return exitCode;
	}

	const char* src = argv[1];
	const char* dest = argv[2];
	bool reversed = argc == 4 ? strcmp(argv[3], "--reversed") == 0 : false;

	{
		// Opens files
		int fdIn = open(src, O_RDONLY);
		if(fdIn == -1){
			perror(EXEC_NAME), exitCode = EXIT_FAILURE;
			return exitCode;
		}

		int fdOut = open(dest, O_WRONLY | O_CREAT | O_TRUNC, 0666);
		if(fdOut == -1){
			perror(EXEC_NAME), exitCode = EXIT_FAILURE;

			if(close(fdIn) == -1)
				perror(EXEC_NAME), exitCode = EXIT_FAILURE;

			return exitCode;
		}

		// Copies
		if(copy(fdIn, fdOut, reversed) == -1)
			perror(EXEC_NAME), exitCode = EXIT_FAILURE;

		// Closes files
		if(close(fdIn) == -1)
			perror(EXEC_NAME), exitCode = EXIT_FAILURE;
		if(close(fdOut) == -1)
			perror(EXEC_NAME), exitCode = EXIT_FAILURE;
	}

	return exitCode;
}