#include <stdlib.h>
#include <stdio.h>
#include "arithmetic.h"

// extern unsigned int arithmetic_gcd(unsigned int a, unsigned int b);

int main(void){
	int a = 32;
	int b = 56;

	printf("gcd %d %d: %u\n", a, b, arithmetic_gcd(a, b));

	return EXIT_SUCCESS;
}