#ifndef _FRACTION_H
#define _FRACTION_H

#include <stdbool.h>
#include <stdio.h>

// PROTECTED
extern signed char _sgn(const int x);

// PUBLIC
extern int fraction_numerator_default;
extern unsigned int fraction_denominator_default;
struct _Fraction{
	int numerator;
	unsigned int denominator;
};
typedef struct _Fraction Fraction;

// Initialization
extern bool fraction_init(void);
extern bool fraction_finish(void);

// Creation and Destruction
extern Fraction *fraction_fill_full(Fraction *fraction,
	const int numerator,
	const unsigned int denominator
);
extern Fraction *fraction_create_full(
	const int numerator,
	const unsigned int denominator
);
extern Fraction *fraction_create_default(void);
extern void fraction_destroy(Fraction *fraction);

// Cloning
extern Fraction *fraction_copy(Fraction *dest, const Fraction *src);
extern Fraction *fraction_clone(const Fraction *src);

// Utils
extern Fraction *fraction_reduce(Fraction *dest, const Fraction *a);

// Arithmetic operations
extern Fraction *fraction_addition(Fraction *dest,
	const Fraction *a, const Fraction *b
);
extern Fraction *fraction_symmetric(Fraction *dest, const Fraction *a);
extern Fraction *fraction_substraction(Fraction *dest,
	const Fraction *a, const Fraction *b
);

extern Fraction *fraction_multiplication(Fraction *dest,
	const Fraction *a, const Fraction *b
);
extern Fraction *fraction_inverse(Fraction *dest, const Fraction *a);
extern Fraction *fraction_division(Fraction *dest,
	const Fraction *a, const Fraction *b
);

// Streaming
extern const char *fraction_to_string(const Fraction *fraction);

extern const Fraction *fraction_fwrite(const Fraction *fraction, FILE *stream);
extern Fraction *fraction_fread(Fraction *fraction, FILE *stream);

#endif // _FRACTION_H