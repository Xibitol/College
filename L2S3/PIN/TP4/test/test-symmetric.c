#include <stdlib.h>
#include <assert.h>

#include "../src/fraction.h"

int test_symmetric(void){
	fraction_init();

	Fraction a = {1, 2};
	Fraction b;
	Fraction *c = fraction_symmetric(&b, &a);
	assert(b.numerator == -1 && b.denominator == 2 && &b == c);

	fraction_finish();
	return EXIT_SUCCESS;
}