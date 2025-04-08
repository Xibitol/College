#include <stdlib.h>
#include <assert.h>

#include "../src/fraction.inc.h"
#include "../src/fraction.h"

int test_mem(void){
	fraction_init();

	Fraction *a = fraction_create_full(2, 4);
	Fraction *b = fraction_create_full(6, 8);
	assert(
		a->numerator == 1 && a->denominator == 2
		&& b->numerator == 3 && b->denominator == 4
	);

	fraction_destroy(a);
	fraction_destroy(b);

	a = fraction_create_default();
	assert(
		a->numerator == fraction_numerator_default
		&& a->denominator == fraction_denominator_default
	);

	fraction_destroy(a);

	fraction_finish();
	return EXIT_SUCCESS;
}