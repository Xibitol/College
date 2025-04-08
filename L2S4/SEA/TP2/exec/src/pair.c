/**
 * @file pair.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>

#define EXEC_NAME "pair"

int main(void){
	int exitCode = EXIT_SUCCESS;

	setbuf(stdout, NULL);

	pid_t fpid = 0;
	for(int i = 0; i < 2; i++){
		fpid = fork();
		switch(fpid){
			case -1:
				perror(EXEC_NAME), exitCode =  EXIT_FAILURE;
				break;
			case 0:;
				printf("Child N°%d: PID=%d\n", i, getpid());
				break;
			default:;
				waitpid(fpid, NULL, 0);
		}

		if(getpid()%2 != 0) i = 2;
	}

	return exitCode;
}