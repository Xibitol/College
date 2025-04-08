/**
 * @file ring.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <fcntl.h>
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/param.h>
#include <sys/stat.h>
#include <unistd.h>
#include <wait.h>

#define EXEC_NAME "ring"

#define FIFO_NAME_MAX_SIZE 32

#define EPRINTF(...) fprintf(stdout, __VA_ARGS__)
#define GET_FIFO_NAME(buffer, index) \
	snprintf(buffer, FIFO_NAME_MAX_SIZE, "fifo-%s-%u", EXEC_NAME, index)

enum PipeFDS{
	READ = 0,
	WRITE = 1
};

static unsigned int randOnPID(void){
	srand(getpid());
	return rand()%1024;
}

static int worker(unsigned int index, unsigned int count){
	unsigned int exitCode = EXIT_SUCCESS;

	char fifoName[FIFO_NAME_MAX_SIZE];
	int fifoFD = -1;
	unsigned int newNumber = 0;
	unsigned int number = 0;

	printf("Hey! I'm worker n°%u (PID: %d).\n", index + 1, getpid());

	if(index != 0){
		GET_FIFO_NAME(fifoName, index - 1);

		if((fifoFD = open(fifoName, O_RDONLY)) == -1){
			perror(EXEC_NAME), exitCode = EXIT_FAILURE;
			return exitCode;
		}

		if(read(fifoFD, &number, sizeof number) != sizeof number)
			perror(EXEC_NAME), exitCode = EXIT_FAILURE;

		if(close(fifoFD)) perror(EXEC_NAME), exitCode = EXIT_FAILURE;
	}

	number = MAX(number, randOnPID());

	{
		GET_FIFO_NAME(fifoName, index);

		if((fifoFD = open(fifoName, O_WRONLY)) == -1){
			perror(EXEC_NAME), exitCode = EXIT_FAILURE;
			return exitCode;
		}

		if(write(fifoFD, &number, sizeof number) != sizeof number)
			perror(EXEC_NAME), exitCode = EXIT_FAILURE;

		if(close(fifoFD)) perror(EXEC_NAME), exitCode = EXIT_FAILURE;
	}

	if(index == 0){
		GET_FIFO_NAME(fifoName, count - 1);

		if((fifoFD = open(fifoName, O_RDONLY)) == -1){
			perror(EXEC_NAME), exitCode = EXIT_FAILURE;
			return exitCode;
		}

		if(read(fifoFD, &number, sizeof number) != sizeof number)
			perror(EXEC_NAME), exitCode = EXIT_FAILURE;

		printf("Biggest random number: %u\n", number);

		if(close(fifoFD)) perror(EXEC_NAME), exitCode = EXIT_FAILURE;
	}

	return EXIT_SUCCESS;
}

int main(const int argc, const char *const argv[]){
	unsigned int exitCode = EXIT_SUCCESS;

	if(argc != 2){
		printf("Usage: %s <count>\n", EXEC_NAME);
		return exitCode;
	}

	unsigned int count = atoi(argv[1]);

	if(count > 20){
		EPRINTF("%s: Too many processes will be created! (%u)\n",
			EXEC_NAME, count
		);
		return exitCode = EXIT_FAILURE;
	}

	{
		char fifoName[FIFO_NAME_MAX_SIZE];
		unsigned int workerPIDs[count];

		for(unsigned int i = 0; i < count; i++){
			GET_FIFO_NAME(fifoName, i);

			if(mkfifo(fifoName, 0600) == -1)
				perror(EXEC_NAME), exitCode = EXIT_FAILURE;
		}

		if(exitCode == EXIT_SUCCESS){
			pid_t fpid = 0;
			for(unsigned int i = 0;
				i < count && (i == 0 || workerPIDs[i - 1] != 0);
				i++
			){
				switch(fpid = fork()){
					case -1:
						perror(EXEC_NAME), exitCode = EXIT_FAILURE;
						workerPIDs[i] = 0;
						break;
					case 0:
						exit(worker(i, count));
					default:
						workerPIDs[i] = fpid;
				}
			}

			unsigned int i = 0;
			while(i < count && workerPIDs[i] != 0)
				waitpid(workerPIDs[i++], NULL, 0);
		}

		for(unsigned int i = 0; i < count; i++){
			GET_FIFO_NAME(fifoName, i);

			if(access(fifoName, F_OK) == 0 && remove(fifoName))
				perror(EXEC_NAME), exitCode = EXIT_FAILURE;
		}
	}

	return exitCode;
}