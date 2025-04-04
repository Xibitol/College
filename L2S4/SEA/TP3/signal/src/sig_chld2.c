/**
 * @file sig_chld1.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <errno.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/wait.h>
#include <stdio.h>

#define EXEC_NAME "sig_chld2"

int main(){
	unsigned int exitCode = EXIT_SUCCESS;

	if(signal(SIGCHLD, SIG_IGN) == SIG_ERR){
		printf("Error while trying to set signal n°%u handler (%s);\n",
			SIGCHLD, strerror(errno)
		);
		return exitCode = EXIT_FAILURE;
	}

	{
		pid_t fpid = fork();
		switch(fpid){
			case -1:
				perror(EXEC_NAME), exitCode = EXIT_FAILURE;
				break;
			case 0:
				printf("Child's PID: %d\n", getpid());

				sleep(1);
				break;
			default:
				pause();
		}
	}

	return exitCode;
}