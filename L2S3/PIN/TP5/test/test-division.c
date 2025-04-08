#include <stdlib.h>
#include <assert.h>

#include "../src/fraction.inc.h"
#include "../src/fraction.h"

int test_division(void){
	fraction_init();

	Fraction a = {1, 2};
	Fraction b = {1, 2};
	Fraction d;
	Fraction *c = fraction_division(&d, &a, &b);
	assert(c->numerator == 1 && c->denominator == 1 && &d == c);

	a = (Fraction) {19, 31};
	b = (Fraction) {20, 10};
	fraction_division(&d, &a, &b);
	assert(c->numerator == 19 && c->denominator == 62);

	a = (Fraction) {-7548, 543};
	b = (Fraction) {93586, 1928};
	fraction_division(&d, &a, &b);
	assert(c->numerator == -2425424 && c->denominator == 8469533);

	a = (Fraction) {-1, 1};
	b = (Fraction) {-1, 1};
	fraction_division(&d, &a, &b);
	assert(c->numerator == 1 && c->denominator == 1);

	fraction_finish();
	return EXIT_SUCCESS;
}