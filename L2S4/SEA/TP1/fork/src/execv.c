/**
 * @file execv.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

#define EXEC_NAME "execv"

int main(void){
	if(execv("/bin/ps", ((char* const[]) {"ps", "aux", NULL})) == -1){
		perror(EXEC_NAME);
		return EXIT_FAILURE;
	}
}