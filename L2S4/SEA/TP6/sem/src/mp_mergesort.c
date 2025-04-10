/**
 * @file mt_mergesort.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdbool.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <sys/wait.h>
#include <unistd.h>
#include <semaphore.h>
#include <stdio.h>

typedef struct {
	unsigned int size;
	int* array;
} Table;

#define EXEC_NAME "mp_mergesort"
#define VALUES_COUNT 100000
#define TABLE_SHARED_MEM_NAME "mp_mergesort-table"

static unsigned int exitCode = EXIT_SUCCESS;
static unsigned int remainingCore = 0;

#define eprintf(...) fprintf(stderr, __VA_ARGS__)

static bool isSorted(Table* t){
	unsigned int i = 0;

	while(i++ < t->size && t->array[i - 1] <= t->array[i]);

	return i >= t->size;
}

static void lightweightMerge(Table* t, const Table lTable, const Table rTable){
	int* lOverflow = malloc(sizeof(int)*lTable.size);

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

	free(lOverflow);
}

static void mergesort(Table* t){
	Table lTable = {t->size/2, t->array};
	Table rTable = {t->size - t->size/2, t->array + t->size/2};

	if(lTable.size >= 2)
		mergesort(&lTable);

	if(rTable.size >= 2)
		mergesort(&rTable);

	lightweightMerge(t, lTable, rTable);
}
static void mpaMergesort(Table* t){
	if(remainingCore <= 0){
		mergesort(t);
		return;
	}

	// Sems init and mem mapping
	sem_t* sem = mmap(NULL, sizeof(sem_t),
		PROT_READ | PROT_WRITE,
		MAP_SHARED | MAP_ANONYMOUS,
		-1, 0
	);
	if(sem == MAP_FAILED){
		perror(EXEC_NAME), exitCode = EXIT_FAILURE;
		return;
	}

	if(sem_init(sem, true, 0) == -1){
		perror(EXEC_NAME), exitCode = EXIT_FAILURE;

		munmap(sem, sizeof sem);

		return;
	}

	// Table division
	Table lTable = {t->size/2, t->array};
	Table rTable = {t->size - t->size/2, t->array + t->size/2};

	// Forking and working
	remainingCore--;

	pid_t fpid;
	switch(fpid = fork()){
		case -1:
			perror(EXEC_NAME), exitCode = EXIT_FAILURE;
			break;
		case 0: // CHILD
			remainingCore = remainingCore/2;

			if(lTable.size >= 2)
				mpaMergesort(&lTable);

			if(sem_wait(sem) != -1)
				lightweightMerge(t, lTable, rTable);
			else
				perror(EXEC_NAME), exitCode = EXIT_FAILURE;

			exit(exitCode);
			break;
		default: // PARENT
			remainingCore = remainingCore/2 + remainingCore%2;

			if(rTable.size >= 2)
				mpaMergesort(&rTable);
			if(sem_post(sem) == -1)
				perror(EXEC_NAME), exitCode = EXIT_FAILURE;

			if(waitpid(fpid, NULL, 0) == -1)
				perror(EXEC_NAME), exitCode = EXIT_FAILURE;

			break;
	}

	// Cleaning sems and mem maps
	if(sem_destroy(sem) == -1)
		perror(EXEC_NAME), exitCode = EXIT_FAILURE;

	if(munmap(sem, sizeof sem) == -1)
		perror(EXEC_NAME), exitCode = EXIT_FAILURE;
}

int main(void){
	srand(getpid());
	setbuf(stdout, NULL);

	remainingCore = sysconf(_SC_NPROCESSORS_ONLN) - 2 - 1;

	// Create shared obj
	int tsmFD = shm_open(TABLE_SHARED_MEM_NAME,
		O_RDWR | O_CREAT | O_EXCL, 0600
	);
	if(tsmFD == -1){
		perror(EXEC_NAME), exitCode = EXIT_FAILURE;
		return exitCode;
	}

	if(ftruncate(tsmFD, sizeof(int)*VALUES_COUNT) == -1){
		perror(EXEC_NAME), exitCode = EXIT_FAILURE;

		shm_unlink(TABLE_SHARED_MEM_NAME);

		return exitCode;
	}

	// Mem map + table and its values
	int* values = mmap(NULL, sizeof(int)*VALUES_COUNT,
		PROT_READ | PROT_WRITE,
		MAP_SHARED,
		tsmFD, 0
	);
	Table t = {VALUES_COUNT, values};

	if(values == MAP_FAILED){
		perror(EXEC_NAME), exitCode = EXIT_FAILURE;

		shm_unlink(TABLE_SHARED_MEM_NAME);

		return exitCode;
	}

	for(unsigned int i = 0; i < VALUES_COUNT; i++)
		values[i] = rand()%31020;

	// Remove shared obj
	if(shm_unlink(TABLE_SHARED_MEM_NAME) == -1)
		perror(EXEC_NAME), exitCode = EXIT_FAILURE;

	// Merge sort
	{
		// printf("Before:");
		// for(unsigned int i = 0; i < VALUES_COUNT; i++)
		// 	printf(" %d,", values[i]);
		// printf("\n");

		printf("i.e ");
		if(isSorted(&t)) printf("sorted!\n");
		else printf("not sorted!\n");

		mpaMergesort(&t);

		// printf("After:");
		// for(unsigned int i = 0; i < VALUES_COUNT; i++)
		// 	printf(" %d,", values[i]);
		// printf("\n");

		printf("i.e ");
		if(isSorted(&t)) printf("sorted!\n");
		else printf("not sorted!\n");
	}

	// Freeing mem map
	if(munmap(values, sizeof values) == -1)
		perror(EXEC_NAME), exitCode = EXIT_FAILURE;

	return exitCode;
}