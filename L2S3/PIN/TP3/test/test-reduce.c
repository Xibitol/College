#include <stdlib.h>
#include <assert.h>

#include "../src/fraction.h"

int test_reduce(void){
	fraction_init();

	Fraction a = {0, 0};
	Fraction b;
	Fraction *c = fraction_reduce(&b, &a);
	assert(b.numerator == 0 && b.denominator == 0 && &b == c);

	a = (Fraction) {0, 4};
	fraction_reduce(c, &a);
	assert(c->numerator == 0 && c->denominator == 1);

	a = (Fraction) {1, 2};
	fraction_reduce(c, &a);
	assert(c->numerator == 1 && c->denominator == 2);

	a = (Fraction) {-285, 645};
	fraction_reduce(c, &a);
	assert(c->numerator == -19 && c->denominator == 43);

	a = (Fraction) {320, 640};
	fraction_reduce(c, &a);
	assert(c->numerator == 1 && c->denominator == 2);

	a = (Fraction) {-16384, 32768};
	fraction_reduce(c, &a);
	assert(c->numerator == -1 && c->denominator == 2);

	fraction_finish();
	return EXIT_SUCCESS;
}