#ifndef _FRACTION_H
#define _FRACTION_H

// PRIVATE
extern signed char _sgn(const int x);

// PUBLIC
struct _Fraction{
	int numerator;
	unsigned int denominator;
};
typedef struct _Fraction Fraction;

extern Fraction fraction_reduce(const Fraction a);

extern Fraction fraction_addition(const Fraction a, const Fraction b);
extern Fraction fraction_symmetric(const Fraction a);
extern Fraction fraction_substraction(const Fraction a, const Fraction b);

extern Fraction fraction_multiplication(const Fraction a, const Fraction b);
extern Fraction fraction_inverse(const Fraction a);
extern Fraction fraction_division(const Fraction a, const Fraction b);

#endif // _FRACTION_H