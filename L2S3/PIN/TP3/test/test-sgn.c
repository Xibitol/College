#include <stdlib.h>
#include <assert.h>

#include "../src/fraction.h"

int test_sgn(void){
	assert(_sgn(1) == 1);
	assert(_sgn(-2) == -1);
	assert(_sgn(6) == 1);
	assert(_sgn(0) == 1);
	assert(_sgn(-1) == -1);

	return EXIT_SUCCESS;
}