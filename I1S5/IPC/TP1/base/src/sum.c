/**
 * @file sum.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdlib.h>
#include <stdio.h>

int main(void){
	unsigned int n = 5;
	printf("Somme des entiers de 0 à %d est égal à %d.\n", n, n*(n + 1)/2);
	return 0;
}