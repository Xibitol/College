#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <assert.h>

#include "fraction.inc.h"
#include "../src/fraction.h"

int main(void){
	// Outputs 8 because an int is 4 bytes, and there are two of it.
	printf("%ld\n", sizeof(Fraction));

	return EXIT_SUCCESS;
}