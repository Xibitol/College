#include <stdlib.h>
#include <assert.h>

#include "../src/fraction.inc.h"
#include "../src/fraction.h"

int test_inverse(void){
	fraction_init();

	Fraction a = {1, 2};
	Fraction b;
	Fraction *c = fraction_inverse(&b, &a);
	assert(b.numerator == 2 && b.denominator == 1 && &b == c);

	a = (Fraction) {-3, 1};
	fraction_inverse(c, &a);
	assert(c->numerator == -1 && c->denominator == 3);

	fraction_finish();
	return EXIT_SUCCESS;
}