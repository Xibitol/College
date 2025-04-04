/**
 * @file sync.c
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

#define EXEC_NAME "sync"

static unsigned char counter = 0;

static void signalUSR1Handler(int signal){
	if(signal != SIGUSR1) return;

	printf("%d\n", counter);
	counter += 2;
}

int main(){
	unsigned int exitCode = EXIT_SUCCESS;

	{
		if(signal(SIGUSR1, &signalUSR1Handler) == SIG_ERR){
			printf("Error while trying to set signal n°%u handler (%s);\n",
				SIGCHLD, strerror(errno)
			);
			return exitCode = EXIT_FAILURE;
		}

		pid_t fpid = fork(); // Parent must run first
		switch(fpid){
			case -1:
				perror(EXEC_NAME), exitCode = EXIT_FAILURE;
				return exitCode;
			case 0:
				counter = 1;
				printf("Child's PID: %d\n", getpid());

				if(signal(SIGUSR1, &signalUSR1Handler) == SIG_ERR){
					printf(
						"Error while trying to set signal n°%u handler (%s);\n",
						SIGCHLD, strerror(errno)
					);
					return exitCode = EXIT_FAILURE;
				}

				break;
			default:
				pause();
		}

		while(counter <= 100){
			if(kill(fpid == 0 ? getppid() : fpid, SIGUSR1) == -1)
				perror(EXEC_NAME), exitCode = EXIT_FAILURE;

			pause(); // Every process must stop there and never somewhere else
		}

		if(fpid == 0){
			if(kill(getppid(), SIGUSR1) == -1)
				perror(EXEC_NAME), exitCode = EXIT_FAILURE;
		}else if(fpid > 0){
			if(waitpid(fpid, NULL, 0) == -1)
				perror(EXEC_NAME), exitCode = EXIT_FAILURE;
		}
	}

	return exitCode;
}