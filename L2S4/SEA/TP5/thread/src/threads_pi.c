/**
 * @file threads_pi.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <math.h>
#include <stdlib.h>
#include <malloc.h>
#include <pthread.h>
#include <stdio.h>

#define EXEC_NAME "threads_pi"

static void* threadMain(void* arg){
	unsigned int i = *((unsigned int*) arg);

	double* piPart = malloc(sizeof(double));
	*piPart = 1/pow(i, 2);

	return piPart;
}

int main(void){
	unsigned int exitCode = EXIT_SUCCESS;

	unsigned int count = 20000;

	{
		double piApprox = 0;

		pthread_t threads[count];
		unsigned int values[count];

		for(unsigned int i = 0; i < count; i++){
			values[i] = i + 1;

			if(pthread_create(&threads[i], NULL, &threadMain, &values[i]) != 0)
				exitCode = EXIT_FAILURE;
		}

		double* piPart = NULL;
		for(unsigned int i = 0; i < count; i++){
			if(pthread_join(threads[i], (void*) &piPart) != 0)
				exitCode = EXIT_FAILURE;

			piApprox += *piPart;
			free(piPart);
		}

		printf("PI approx: %f\n", sqrt(piApprox*6));
	}

	pthread_exit(&exitCode);
}