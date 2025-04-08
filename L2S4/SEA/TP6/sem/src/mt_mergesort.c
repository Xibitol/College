/**
 * @file mt_mergesort.c
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

typedef struct {
	unsigned int size;
	int* array;
} Table;

#define EXEC_NAME "mt_mergesort"
#define VALUES_COUNT 256

static unsigned int exitCode = EXIT_SUCCESS;

#define eprintf(...) fprintf(stderr, __VA_ARGS__)

static void* threadedMerge(void* arg){
	Table* t = ((Table*) arg);

	pthread_t lThread;
	Table lTable = {t->size/2, t->array};

	pthread_t rThread;
	Table rTable = {t->size - t->size/2, t->array + t->size/2};

	if(lTable.size >= 2){
		pthread_create(&lThread, NULL, &threadedMerge, &lTable);
		pthread_join(lThread, NULL);
	}
	if(rTable.size >= 2){
		pthread_create(&rThread, NULL, &threadedMerge, &rTable);
		pthread_join(rThread, NULL);
	}

	int lOverflow[lTable.size];
	unsigned int lofEndIdx = 0;
	for(unsigned int i = 0, rIdx = 0, lIdx = 0, lofIdx = 0; i < t->size; i++){
		if(rIdx >= rTable.size){
			t->array[i] = lOverflow[lofIdx];
			lofIdx++;
		}else if(lofIdx != lofEndIdx){
			if(lIdx < lTable.size){
				lOverflow[lofEndIdx] = lTable.array[lIdx];
				lofEndIdx++;
				lIdx++;
			}

			if(lOverflow[lofIdx] > rTable.array[rIdx]){
				t->array[i] = rTable.array[rIdx];
				rIdx++;
			}else{
				t->array[i] = lOverflow[lofIdx];
				lofIdx++;
			}
		}else if(lIdx < lTable.size){
			if(lTable.array[lIdx] > rTable.array[rIdx]){
				lOverflow[lofEndIdx] = lTable.array[lIdx];
				lofEndIdx++;

				t->array[i] = rTable.array[rIdx];
				rIdx++;
			}

			lIdx++;
		}else
			break;
	}

	return NULL;
}

static bool isSorted(Table* t){
	unsigned int i = 0;

	while(i++ < t->size && t->array[i - 1] <= t->array[i]);

	return i >= t->size;
}

int main(void){
	srand(getpid());

	int values[VALUES_COUNT];
	Table t = {VALUES_COUNT, values};

	for(unsigned int i = 0; i < VALUES_COUNT; i++)
		values[i] = rand()%31020;

	{
		// printf("Before:");
		// for(unsigned int i = 0; i < VALUES_COUNT; i++)
		// 	printf(" %d,", values[i]);
		// printf("\n");

		printf("i.e ");
		if(isSorted(&t)) printf("sorted!\n");
		else printf("not sorted!\n");

		threadedMerge(&t);

		// printf("After:");
		// for(unsigned int i = 0; i < VALUES_COUNT; i++)
		// 	printf(" %d,", values[i]);
		// printf("\n");

		printf("i.e ");
		if(isSorted(&t)) printf("sorted!\n");
		else printf("not sorted!\n");
	}

	pthread_exit(&exitCode);
}