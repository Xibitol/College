#include <stdlib.h>
#include <assert.h>

#include "../src/fraction.h"

int test_getters(void){
	fraction_init();

	Fraction *a = fraction_create_full(1, 2);
	assert(fraction_get_numerator(a) == 1 && fraction_get_denominator(a) == 2);
	fraction_destroy(a);

	fraction_finish();
	return EXIT_SUCCESS;
}