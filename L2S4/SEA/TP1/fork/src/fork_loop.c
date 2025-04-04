/**
 * @file fork_loop.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <errno.h>
#include <string.h>
#include <stdlib.h>
#include <sys/time.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>

#define EXEC_NAME "fork_loop"

static long getMicrotime(){
	struct timeval currentTime;
	gettimeofday(&currentTime, NULL);

	return currentTime.tv_sec*(int) 1e6 + currentTime.tv_usec;
}

int main(int argc, const char* argv[]){
	if(argc != 2){
		printf("Usage: %s [child count]\n", EXEC_NAME);
		return EXIT_SUCCESS;
	}
	unsigned int count = atoi(argv[1]);

	if(count > 15){
		fprintf(stderr, "%s: %u childs is a bit too much.\n", EXEC_NAME, count);
		return EXIT_FAILURE;
	}

	setbuf(stdout, NULL); // Fixes printf buffer copies not flushed.

	printf("[%ld] MASTER: PID %d and PPID %d\n",
		getMicrotime(), getpid(), getppid()
	);

	pid_t fpid = -1;
	for(unsigned int i = 0; i < count; i++){
		fpid = fork();

		if(fpid == -1){
			fprintf(stderr, "[%ld] CHILD from %d: %s\n",
				getMicrotime(), getpid(), strerror(errno)
			);
		}else if(fpid > 0){
			printf("[%ld] CHILD from %d: PID=%d (%d) and PPID of PPID=%d\n",
				getMicrotime(), getpid(), fpid, count - i - 1, getppid()
			);

			int cStatus = 0;
			fpid = waitpid(fpid, &cStatus, 0);
		}
	}

	return EXIT_SUCCESS;
}
