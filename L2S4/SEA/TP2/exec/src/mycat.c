/**
 * @file cp.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>

#define EXEC_NAME "mycat"
#define BUF_SIZE 1

int main(const int argc, const char *const argv[]){
	unsigned int exitCode = EXIT_SUCCESS;

	setbuf(stdout, NULL);

	if(argc > 2 || (argc == 2 && strcmp(argv[1], "-h") == 0)){
		printf("Usage: mycat [-h]\n   or: mycat <output>\n");
		return exitCode;
	}

	int fd = -1;
	if(argc == 2 && (
		(fd = open(argv[1], O_WRONLY | O_CREAT | O_TRUNC, 0666)) == -1
		|| dup2(fd, STDOUT_FILENO) == -1
	)){
		perror(EXEC_NAME), exitCode = EXIT_FAILURE;
		return exitCode;
	}

	{
		char buffer[BUF_SIZE];
		int readLength = 0;
		while((readLength = read(STDIN_FILENO, buffer, BUF_SIZE)) != 0){
			if(readLength == -1){
				perror(EXEC_NAME), exitCode = EXIT_FAILURE;
				break;
			}

			if(write(STDOUT_FILENO, buffer, readLength) == -1){
				perror(EXEC_NAME), exitCode = EXIT_FAILURE;
				break;
			}
		}
	}

	if(argc == 2 && close(fd) == -1)
		perror(EXEC_NAME), exitCode = EXIT_FAILURE;

	return exitCode;
}