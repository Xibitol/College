/**
 * @file fork.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>

#define EXEC_NAME "fork"

int main(void){
	pid_t fpid = fork();
	
	if(fpid == -1){
		perror(EXEC_NAME);
		return EXIT_FAILURE;
	}else if(fpid > 0){
		printf("%s: Parent has PID=%d, PPID=%d and child has PID=%d\n",
			EXEC_NAME, getpid(), getppid(), fpid
		);

		int cStatus = 0;
		fpid = waitpid(fpid, &cStatus, 0); // Or wait(&stat_loc)
		if(fpid == -1){
			perror(EXEC_NAME);
			return EXIT_FAILURE;
		}else if(WIFEXITED(cStatus)){
			printf("%s: Child exited with code %d\n",
				EXEC_NAME, WEXITSTATUS(cStatus)
			);
		}
	}else{
		printf("%s: Child has PID=%d and PPID=%d\n",
			EXEC_NAME, getpid(), getppid()
		);

		return 2;
	}

	return EXIT_SUCCESS;
}
