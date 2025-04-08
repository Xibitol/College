/**
 * @file signals.c
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

#define EXEC_NAME "signals"

static unsigned int signalCount[_NSIG];

static void signalHandler(int signal){
	signalCount[signal]++;

	printf("Intercepted n°%d %u times\n", signal, signalCount[signal]);
}

int main(){
	unsigned int exitCode = EXIT_SUCCESS;

	for(unsigned int s = 1; s < _NSIG; s++){
		signalCount[s] = 0;

		if(signal(s, &signalHandler) == SIG_ERR){
			printf("Error while trying to set signal n°%u handler (%s);\n",
				s, strerror(errno)
			);
			exitCode = EXIT_FAILURE;
		}
	}

	{
		while(1) pause();
	}

	return exitCode;
}