#include <stdlib.h>
#include <stdio.h>

#include <arithmetic.h>
#include "fraction.h"

// PRIVATE
static int initCount = 0;

// PROTECTED
extern signed char _sgn(const int x){
	return x < 0 ? -1 : 1;
}

// PUBLIC
int fraction_numerator_default = 1;
unsigned int fraction_denominator_default = 2;

// Initialization
bool fraction_init(void){
	initCount++;
	if(initCount > 1) return true;

	printf("fraction: Fraction library successfully initialized.\n");
	return true;
}
bool fraction_finish(void){
	if(initCount == 0) return false;

	initCount--;
	if(initCount > 0) return true;

	printf("fraction: Fraction library successfully finished.\n");
	return true;
}

// Creation and Destruction (Mem alloc)
Fraction *fraction_fill_full(Fraction *fraction,
	const int numerator,
	const unsigned int denominator
){
	return fraction_reduce(fraction, &((Fraction) {numerator, denominator}));
}
Fraction *fraction_create_full(
	const int numerator,
	const unsigned int denominator
){
	return fraction_fill_full(malloc(sizeof(Fraction)), numerator, denominator);
}
Fraction *fraction_create_default(void){
	return fraction_create_full(
		fraction_numerator_default,
		fraction_denominator_default
	);
}
void fraction_destroy(Fraction *fraction){
	free(fraction);
}

// Cloning
Fraction *fraction_copy(Fraction *dest, const Fraction *src){
	*dest = *src;
	return dest;
}
Fraction *fraction_clone(const Fraction *src){
	return fraction_copy(malloc(sizeof(Fraction)), src);
}

// Utils
Fraction *fraction_reduce(Fraction *dest, const Fraction *a){
	unsigned int div = arithmetic_gcd(abs(a->numerator), a->denominator);
	*dest = *a;

	if(div > 1){
		dest->numerator = a->numerator/abs((int) div);
		dest->denominator = a->denominator/div;
	}

	return dest;
}

// Arithmetic operations
Fraction *fraction_addition(Fraction *dest,
	const Fraction *a, const Fraction *b
){
	return fraction_reduce(dest, &((Fraction) {
		a->numerator*b->denominator + b->numerator*a->denominator,
		a->denominator*b->denominator
	}));
}
Fraction *fraction_symmetric(Fraction *dest, const Fraction *a){
	*dest = (Fraction) {-a->numerator, a->denominator};

	return dest;
}
Fraction *fraction_substraction(Fraction *dest,
	const Fraction *a, const Fraction *b
){
	return fraction_addition(dest, a, fraction_symmetric(dest, b));
}

Fraction *fraction_multiplication(Fraction *dest,
	const Fraction *a, const Fraction *b
){
	return fraction_reduce(dest, &((Fraction) {
		a->numerator*b->numerator,
		a->denominator*b->denominator
	}));
}
Fraction *fraction_inverse(Fraction *dest, const Fraction *a){
	*dest = (Fraction) {_sgn(a->numerator)*a->denominator, abs(a->numerator)};

	return dest;
}
Fraction *fraction_division(Fraction *dest,
	const Fraction *a, const Fraction *b
){
	return fraction_multiplication(dest, a, fraction_inverse(dest, b));
}