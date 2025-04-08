#include <stdlib.h>
#include <assert.h>

#include "../src/fraction.inc.h"
#include "../src/fraction.h"

int test_substraction(void){
	fraction_init();

	Fraction a = {1, 2};
	Fraction b = {1, 2};
	Fraction d;
	Fraction *c = fraction_substraction(&d, &a, &b);
	assert(d.numerator == 0 && d.denominator == 1 && &d == c);

	a = (Fraction) {19, 31};
	b = (Fraction) {20, 10};
	fraction_substraction(c, &a, &b);
	assert(c->numerator == -43 && c->denominator == 31);

	a = (Fraction) {-7548, 543};
	b = (Fraction) {93586, 1928};
	fraction_substraction(c, &a, &b);
	assert(c->numerator == -10894957 && c->denominator == 174484);

	a = (Fraction) {1, 1};
	b = (Fraction) {1, 1};
	fraction_substraction(c, &a, &b);
	assert(c->numerator == 0 && c->denominator == 1);

	fraction_finish();
	return EXIT_SUCCESS;
}