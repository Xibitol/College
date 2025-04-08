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

#define EXEC_NAME "sig_chld1"

static void signalCHLDHandler(int signal){
	if(signal != SIGCHLD) return;

	printf("Intercepted SIGCHLD (%d)\n", signal);

	pid_t wpid = waitpid(-1, NULL, WNOHANG);
	switch(wpid){
		case -1:
			perror(EXEC_NAME);
			break;
		case 0:
			printf("Wut ?\n");
			break;
		default:
			printf("Child n°%d is now dead (I did nothing I swear!).\n", wpid);
	}
}

int main(){
	unsigned int exitCode = EXIT_SUCCESS;

	if(signal(SIGCHLD, &signalCHLDHandler) == SIG_ERR){
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