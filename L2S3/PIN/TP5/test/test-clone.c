#include <stdlib.h>
#include <assert.h>

#include "../src/fraction.inc.h"
#include "../src/fraction.h"

int test_clone(void){
	fraction_init();

	Fraction a = {1, 2};
	Fraction *c = fraction_clone(&a);
	assert(c->numerator == 1 && c->denominator == 2 && &a != c);

	fraction_destroy(c);

	fraction_finish();
	return EXIT_SUCCESS;
}