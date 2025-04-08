#include <stdlib.h>
#include <string.h>
#include <assert.h>

#include "../src/fraction.h"

int test_toString(void){
	fraction_init();

	Fraction a = (Fraction) {1, 2};
	Fraction b = (Fraction) {3, 4};
	Fraction c = (Fraction) {0x80000000, 0xffffffff};

	const char *sa = fraction_to_string(&a);
	const char *sb = fraction_to_string(&b);
	assert(strcmp(sa, "1/2") == 0 && strcmp(sb, "3/4") == 0);

	const char *sc = fraction_to_string(&c);
	assert(strcmp(sc, "-2147483648/4294967295") == 0);

	fraction_finish();
	return EXIT_SUCCESS;
}