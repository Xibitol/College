#include <stdlib.h>
#include <stdio.h>

#include <arithmetic.h>

#include "fraction.inc.h"
#include "fraction.h"

// PRIVATE
static const short INT_REPR_MAX_LENGTH = 11; // (-0x80000000 =) -2147483648
static const short UINT_REPR_MAX_LENGTH = 10; // (0xffffffff =) 4294967295
static const char* FRACTION_FORMAT = "%d/%u";
static const short FRACTION_FORMAT_MAX_LENGTH = (
	INT_REPR_MAX_LENGTH + 1 + UINT_REPR_MAX_LENGTH + 1 // \0 at end.
);

static int initCount = 0;
static int strReprCount = 0;
static char **strReprs;

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

	strReprs = fraction_malloc(__SIZEOF_POINTER__);

	printf("fraction: Fraction library successfully initialized.\n");
	return true;
}
bool fraction_finish(void){
	if(initCount == 0) return false;

	initCount--;
	if(initCount > 0) return true;
	
	while(strReprCount > 0) fraction_free(strReprs[--strReprCount]);
	fraction_free(strReprs);

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
	return fraction_fill_full(fraction_malloc(sizeof(Fraction)),
		numerator,
		denominator
	);
}
Fraction *fraction_create_default(void){
	return fraction_create_full(
		fraction_numerator_default,
		fraction_denominator_default
	);
}
void fraction_destroy(Fraction *fraction){
	fraction_free(fraction);
}

// Getters
void *(*fraction_malloc)(size_t size) = malloc;
void *(*fraction_realloc)(void *ptr, size_t size) = realloc;
void (*fraction_free)(void *ptr) = free;
int fraction_get_numerator(const Fraction *fraction){
	return fraction->numerator;
}
unsigned int fraction_get_denominator(const Fraction *fraction){
	return fraction->denominator;
}

// Cloning
Fraction *fraction_copy(Fraction *dest, const Fraction *src){
	*dest = *src;
	return dest;
}
Fraction *fraction_clone(const Fraction *src){
	return fraction_copy(fraction_malloc(sizeof(Fraction)), src);
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

// Streaming
const char *fraction_to_string(const Fraction *fraction){
	int pos = strReprCount++;
	if(pos > 0) strReprs = fraction_realloc(strReprs,
		__SIZEOF_POINTER__*strReprCount
	);
	strReprs[pos] = fraction_malloc(FRACTION_FORMAT_MAX_LENGTH);

	snprintf(strReprs[pos], FRACTION_FORMAT_MAX_LENGTH,
		FRACTION_FORMAT, fraction->numerator, fraction->denominator
	);

	return strReprs[pos];
}

const Fraction *fraction_fwrite(const Fraction *fraction, FILE *stream){
	if(!stream) return NULL;

	return fwrite(fraction,
		sizeof(fraction[0]), (sizeof *fraction)/sizeof(fraction[0]),
		stream
	) == 0 ? NULL : fraction;
}
Fraction *fraction_fread(Fraction *fraction, FILE *stream){
	if(!stream) return NULL;

	return fread(fraction,
		sizeof(fraction[0]), (sizeof *fraction)/sizeof(fraction[0]),
		stream
	) == 0 ? NULL : fraction;
}