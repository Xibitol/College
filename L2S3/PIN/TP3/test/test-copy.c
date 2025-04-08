#include <stdlib.h>
#include <assert.h>

#include "../src/fraction.h"

int test_copy(void){
	fraction_init();

	Fraction a = (Fraction) {1, 2};
	Fraction b = (Fraction) {3, 4};
	Fraction *c = fraction_copy(&b, &a);
	assert(b.numerator == 1 && b.denominator == 2 && &b == c);

	fraction_finish();
	return EXIT_SUCCESS;
}