/**
 * @file dentiste0.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdbool.h>
#include <stdlib.h>
#include <unistd.h>
#include <semaphore.h>
#include <pthread.h>
#include <stdio.h>

#define EXEC_NAME "dentiste0"

static unsigned int exitCode = EXIT_SUCCESS;

static sem_t treatingSeats;
static sem_t waitingSeats;
static bool closed = false;

#define eprintf(...) fprintf(stderr, __VA_ARGS__)

static void* dentistWorker(void* arg){
	while(!closed){
		if(sem_trywait(&waitingSeats) == 0){
			printf("Dentiste: Je reçois un nouveau patient que je torture...\n");

			sleep(2);

			printf("Dentiste: Le patient n'a pas avoué, une place est donc libre. Au suivant!\n");

			if(sem_post(&treatingSeats) == -1){
				perror(EXEC_NAME), exitCode = EXIT_FAILURE;

				closed = true;
			}
		}
	}

	return arg;
}

static void* clientWorker(void* arg){
	pthread_t tid = pthread_self();

	printf("Patient n°%lu: Je suis laaaaaaaà.\n", tid);

	if(sem_post(&waitingSeats) == -1){
		perror(EXEC_NAME), exitCode = EXIT_FAILURE;

		pthread_detach(tid);
		return arg;
	}
	else if(sem_wait(&treatingSeats) == -1){
		perror(EXEC_NAME), exitCode = EXIT_FAILURE;

		pthread_detach(tid);
		return arg;
	}

	printf("Patient n°%lu: Je rentre dans la pièce et me fait soigner.\n", tid);

	if(pthread_detach(tid) == -1)
		eprintf("Cannot join dentist."), exitCode = EXIT_FAILURE;

	return arg;
}

int main(void){
	if(sem_init(&treatingSeats, 0, 1) == -1){
		perror(EXEC_NAME);
		return exitCode = EXIT_FAILURE;
	}
	else if(sem_init(&waitingSeats, 0, 0) == -1){
		perror(EXEC_NAME);

		sem_destroy(&treatingSeats);

		return exitCode = EXIT_FAILURE;
	}

	pthread_t dentist;
	if(pthread_create(&dentist, NULL, &dentistWorker, NULL) == -1){
		eprintf("Cannot create dentist.");

		sem_destroy(&treatingSeats);
		sem_destroy(&waitingSeats);

		return exitCode = EXIT_FAILURE;
	}

	{
		pthread_t client;
		int c;

		printf("Ouverture du cabinet: \"Artisant de la dent.\".\n");

		while(!closed){
			c = getchar();

			if(c == 'q' || c == EOF) closed = true;
			else if(pthread_create(&client, NULL, &clientWorker, NULL) == -1)
				eprintf("Cannot create client."), exitCode = EXIT_FAILURE;
		}
	}

	if(pthread_join(dentist, NULL) == -1)
		eprintf("Cannot join dentist."), exitCode = EXIT_FAILURE;

	if(sem_destroy(&treatingSeats))
		perror(EXEC_NAME), exitCode = EXIT_FAILURE;
	else if(sem_destroy(&waitingSeats))
		perror(EXEC_NAME), exitCode = EXIT_FAILURE;

	pthread_exit(&exitCode);
}