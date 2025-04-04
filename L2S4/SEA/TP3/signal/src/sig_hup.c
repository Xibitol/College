/**
 * @file sig_hup.c
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

#define EXEC_NAME "sig_hup"

static void signalHUPHandler(int signal){
	if(signal != SIGHUP) return;

	FILE* file = fopen("sighup.txt", "w");
	if(file == NULL) perror(EXEC_NAME);
	else{
		if(fprintf(file, "Intercepted SIGHUP (%d)", signal) < 0)
			perror(EXEC_NAME);

		if(fclose(file) == EOF)
			perror(EXEC_NAME);
	}
}

int main(){
	unsigned int exitCode = EXIT_SUCCESS;

	if(signal(SIGHUP, &signalHUPHandler) == SIG_ERR){
		printf("Error while trying to set signal n°%u handler (%s);\n",
			SIGHUP, strerror(errno)
		);
		return exitCode = EXIT_FAILURE;
	}

	{
		pause();
	}

	return exitCode;
}