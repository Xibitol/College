/**
 * @file n_threads.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdlib.h>
#include <pthread.h>
#include <stdio.h>

#define EXEC_NAME "n_threads"

static unsigned int number = 0;
static pthread_mutex_t mutex;

static void* threadMain(void* arg){
	unsigned int i = *((unsigned int*) arg);

	pthread_mutex_lock(&mutex);

	number += i;
	printf("Thread n°%d: number=%d.\n", i, number);

	pthread_mutex_unlock(&mutex);

	return NULL;
}

int main(void){
	unsigned int exitCode = EXIT_SUCCESS;

	unsigned int count = 20;

	{
		pthread_t threads[count];
		unsigned int values[count];

		for(unsigned int i = 0; i < count; i++){
			values[i] = i;

			if(pthread_create(&threads[i], NULL, &threadMain, &values[i]) != 0)
				exitCode = EXIT_FAILURE;
		}

		for(unsigned int i = 0; i < count; i++){
			if(pthread_join(threads[i], NULL) != 0)
				exitCode = EXIT_FAILURE;
		}

		printf("Number is now %d and should be %d.\n",
			number, count*(count - 1)/2
		);
	}

	pthread_exit(&exitCode);
}