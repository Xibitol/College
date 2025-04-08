#include <stdlib.h>
#include <stdio.h>
#include <assert.h>

#ifdef NDEBUG
#undef NDEBUG
#endif

#include "./arithmetic.h"

int main(void){
	assert(arithmetic_gcd(32, 56) == 8);
	assert(arithmetic_gcd(101, 23) == 1);
	assert(arithmetic_gcd(101, 0) == 101);
	assert(arithmetic_gcd(0, 101) == 101);
	assert(arithmetic_gcd(0, 0) == 0);

	return EXIT_SUCCESS;
}