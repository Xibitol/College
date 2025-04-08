#include <stdlib.h>

#include <arithmetic.h>
#include "fraction.h"

// PRIVATE
signed char _sgn(const int x){
	return x < 0 ? -1 : 1;
}

// PUBLIC
Fraction fraction_reduce(const Fraction a){
	unsigned int div = arithmetic_gcd(abs(a.numerator), a.denominator);

	if(div <= 1) return a;

	return (Fraction) {
		a.numerator/abs((int) div),
		a.denominator/div
	};
}

Fraction fraction_addition(const Fraction a, const Fraction b){
	return fraction_reduce((Fraction) {
		a.numerator*b.denominator + b.numerator*a.denominator,
		a.denominator*b.denominator
	});
}
Fraction fraction_symmetric(const Fraction a){
	return (Fraction) {-a.numerator, a.denominator};
}
Fraction fraction_substraction(const Fraction a, const Fraction b){
	return fraction_addition(a, fraction_symmetric(b));
}

Fraction fraction_multiplication(const Fraction a, const Fraction b){
	return fraction_reduce((Fraction) {
		a.numerator*b.numerator,
		a.denominator*b.denominator
	});
}
Fraction fraction_inverse(const Fraction a){
	return (Fraction) {
		_sgn(a.numerator)*a.denominator,
		abs(a.numerator)
	};
}
Fraction fraction_division(const Fraction a, const Fraction b){
	return fraction_multiplication(a, fraction_inverse(b));
}