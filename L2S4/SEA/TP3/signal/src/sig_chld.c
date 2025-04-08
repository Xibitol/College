/**
 * @file sig_chld.c
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
#include <stdio.h>

#define EXEC_NAME "sig_chld"

static void signalCHLDHandler(int signal){
	if(signal != SIGCHLD) return;

	printf("Intercepted SIGCHLD (%d)\n", signal);
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
		int fpid = fork();
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