/**
 * @file threads_100.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <semaphore.h>
#include <pthread.h>
#include <stdio.h>

#define EXEC_NAME "threads_100"

static const unsigned int max = 100;

static sem_t parent_sem, child_sem;

static void* threadMain(void* arg){
	unsigned int number = 1;

	while(number <= max){
		sem_wait(&child_sem);

		printf("%u\n", number);
		number += 2;

		sem_post(&parent_sem);
	}

	return NULL;
}

int main(void){
	unsigned int exitCode = EXIT_SUCCESS;
	int err = 0;

	if(sem_init(&parent_sem, 0, 0) == -1){
		perror(EXEC_NAME);
		return exitCode = EXIT_FAILURE;
	}
	if(sem_init(&child_sem, 0, 1) == -1){
		perror(EXEC_NAME);
		return exitCode = EXIT_FAILURE;
	}

	pthread_t child;

	if((err = pthread_create(&child, NULL, &threadMain, NULL)) != 0){
		fprintf(stderr, "%s: %s\n", EXEC_NAME, strerror(err));

		sem_destroy(&parent_sem);
		sem_destroy(&child_sem);

		return exitCode = EXIT_FAILURE;
	}

	{
		unsigned int number = 2;

		while(number <= max){
			sem_wait(&parent_sem);

			printf("%d\n", number);
			number += 2;

			sem_post(&child_sem);
		}
	}

	if((err = pthread_join(child, NULL)) != 0){
		fprintf(stderr, "%s: %s\n", EXEC_NAME, strerror(err));
		exitCode = EXIT_FAILURE;
	}

	if(sem_destroy(&parent_sem) == -1)
		perror(EXEC_NAME), exitCode = EXIT_FAILURE;
	if(sem_destroy(&child_sem) == -1)
		perror(EXEC_NAME), exitCode = EXIT_FAILURE;

	return exitCode;
}