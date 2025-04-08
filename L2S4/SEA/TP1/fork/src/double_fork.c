/**
 * @file double_fork.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>

#define EXEC_NAME "double_fork"

static int work(void){
	printf("Don't talk, think that's a good rule of thumb for life. (Rick Grimes)\n");
	sleep(10);

	return EXIT_SUCCESS;
}

int main(void){
	pid_t fpid;

	switch(fpid = fork()){
		case -1:
			perror(EXEC_NAME);
			return EXIT_FAILURE;
		case 0:
			fpid = fork();

			switch(fpid){
				case -1:
					perror(EXEC_NAME);
					return EXIT_FAILURE;
				case 0:
					return work();
			}

			break;
		default:;
			pid_t wpid = waitpid(fpid, NULL, 0);

			if(wpid == -1){
				perror(EXEC_NAME);
				return EXIT_FAILURE;
			}

			break;
	}

	return EXIT_SUCCESS;
}