/**
 * @file tests.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdlib.h>
#include <stdio.h>

int main(void){
	unsigned int a = 4;
	unsigned int b = 2;

	if(a < b) printf("a < b\n");
	else if(a > b) printf("a > b\n");
	else printf("a == b\n");

	unsigned int c = a;
	a = b;
	b = c;

	printf("a=%d et b=%d\n", a, b);
	return 0;
}