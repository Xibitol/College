/**
 * @file zombie2.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

#define EXEC_NAME "zombie2"

int main(void){
	int fpid = fork();

	if(fpid == -1){
		perror(EXEC_NAME);
		return EXIT_FAILURE;
	}else if(fpid > 0){
		printf("%s: Parent has PID=%d, PPID=%d and child has PID=%d\n",
			EXEC_NAME, getpid(), getppid(), fpid
		);
	}else{
		printf("%s: Child has PID=%d and PPID=%d\n",
			EXEC_NAME, getpid(), getppid()
		);

		while(1) sleep(1);

		return 2;
	}

	return EXIT_SUCCESS;
}