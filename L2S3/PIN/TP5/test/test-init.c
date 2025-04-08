#include <stdlib.h>
#include <assert.h>

#include "../src/fraction.inc.h"
#include "../src/fraction.h"

int test_init(void){
	assert(fraction_init());
	assert(fraction_init());
	assert(fraction_init());

	assert(fraction_finish());
	assert(fraction_finish());
	assert(fraction_finish());
	assert(!fraction_finish());

	return EXIT_SUCCESS;
}