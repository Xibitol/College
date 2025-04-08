#include <stdlib.h>
#include <assert.h>

#include "../src/fraction.h"

int test_fill(void){
	fraction_init();

	Fraction a;
	Fraction *c = fraction_fill_full(&a, 2, 4);
	assert(a.numerator == 1 && a.denominator == 2 && &a == c);

	fraction_finish();
	return EXIT_SUCCESS;
}