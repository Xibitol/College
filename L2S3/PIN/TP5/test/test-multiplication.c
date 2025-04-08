#include <stdlib.h>
#include <assert.h>

#include "../src/fraction.inc.h"
#include "../src/fraction.h"

int test_multiplication(void){
	fraction_init();

	Fraction a = {1, 2};
	Fraction b = {1, 2};
	Fraction d;
	Fraction *c = fraction_multiplication(&d, &a, &b);
	assert(d.numerator == 1 && d.denominator == 4 && &d == c);

	a = (Fraction) {19, 31};
	b = (Fraction) {20, 10};
	fraction_multiplication(c, &a, &b);
	assert(c->numerator == 38 && c->denominator == 31);

	a = (Fraction) {-7548, 543};
	b = (Fraction) {93586, 1928};
	fraction_multiplication(c, &a, &b);
	assert(c->numerator == -29432797 && c->denominator == 43621);

	a = (Fraction) {-1, 1};
	b = (Fraction) {-1, 1};
	fraction_multiplication(c, &a, &b);
	assert(c->numerator == 1 && c->denominator == 1);

	fraction_finish();
	return EXIT_SUCCESS;
}