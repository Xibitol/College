/**
 * @file compte_arg.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdlib.h>
#include <stdio.h>

#define EXEC_NAME "compte_arg"

int main(int argc, char const* argv[]){
	int sum = 0;

	for(int i = 1; i < argc; i++)
		sum += atoi(argv[i]);

	printf("%s: Sum of arguments equal to %d\n", EXEC_NAME, sum);

	return EXIT_SUCCESS;
}