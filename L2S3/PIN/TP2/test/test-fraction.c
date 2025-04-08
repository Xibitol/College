#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <assert.h>

#ifdef NDEBUG
#undef NDEBUG
#endif

#include "../src/fraction.h"
#include "test-fraction.h"

int main(int argc, const char *argv[]){
	if(argc < 2){
		printf("test: fatal error: missing arguments (Got %d).\n", argc);
		return EXIT_FAILURE;
	}else if(strlen(argv[1]) > 1){
		printf("test: fatal error: too long argument (Got %ld chars).\n",
			strlen(argv[1])
		);
		return EXIT_FAILURE;
	}

	char name = *argv[1];
	switch(name){
		case 'n': return test_sgn();
		case 'r': return test_reduce();

		case 'a': return test_addition();
		case 'y': return test_symmetric();
		case 's': return test_substraction();

		case 'm': return test_multiplication();
		case 'i': return test_inverse();
		case 'd': return test_division();

		default:
			printf("test: fatal error: no test named %c.\n", name);
			return EXIT_FAILURE;
	}
}

// TESTS
int test_sgn(void){
	assert(_sgn(1) == 1);
	assert(_sgn(-2) == -1);
	assert(_sgn(6) == 1);
	assert(_sgn(0) == 1);
	assert(_sgn(-1) == -1);

	return EXIT_SUCCESS;
}
int test_reduce(void){
	Fraction a = {0, 0};
	Fraction c = fraction_reduce(a);
	assert(c.numerator == 0 && c.denominator == 0);

	a = (Fraction) {0, 4};
	c = fraction_reduce(a);
	assert(c.numerator == 0 && c.denominator == 1);

	a = (Fraction) {1, 2};
	c = fraction_reduce(a);
	assert(c.numerator == 1 && c.denominator == 2);

	a = (Fraction) {-285, 645};
	c = fraction_reduce(a);
	assert(c.numerator == -19 && c.denominator == 43);

	a = (Fraction) {320, 640};
	c = fraction_reduce(a);
	assert(c.numerator == 1 && c.denominator == 2);

	a = (Fraction) {-16384, 32768};
	c = fraction_reduce(a);
	assert(c.numerator == -1 && c.denominator == 2);

	return EXIT_SUCCESS;
}

int test_addition(void){
	Fraction a = {1, 2};
	Fraction b = {1, 2};
	Fraction c = fraction_addition(a, b);
	assert(c.numerator == 1 && c.denominator == 1);

	a = (Fraction) {19, 31};
	b = (Fraction) {20, 10};
	c = fraction_addition(a, b);
	assert(c.numerator == 81 && c.denominator == 31);

	a = (Fraction) {-7548, 543};
	b = (Fraction) {93586, 1928};
	c = fraction_addition(a, b);
	assert(c.numerator == 6044109 && c.denominator == 174484);

	a = (Fraction) {1, 1};
	b = (Fraction) {1, 1};
	c = fraction_addition(a, b);
	assert(c.numerator == 2 && c.denominator == 1);

	return EXIT_SUCCESS;
}
int test_symmetric(void){
	Fraction a = {1, 2};
	Fraction c = fraction_symmetric(a);
	assert(c.numerator == -1 && c.denominator == 2);

	return EXIT_SUCCESS;
}
int test_substraction(void){
	Fraction a = {1, 2};
	Fraction b = {1, 2};
	Fraction c = fraction_substraction(a, b);
	assert(c.numerator == 0 && c.denominator == 1);

	a = (Fraction) {19, 31};
	b = (Fraction) {20, 10};
	c = fraction_substraction(a, b);
	assert(c.numerator == -43 && c.denominator == 31);

	a = (Fraction) {-7548, 543};
	b = (Fraction) {93586, 1928};
	c = fraction_substraction(a, b);
	assert(c.numerator == -10894957 && c.denominator == 174484);

	a = (Fraction) {1, 1};
	b = (Fraction) {1, 1};
	c = fraction_substraction(a, b);
	assert(c.numerator == 0 && c.denominator == 1);

	return EXIT_SUCCESS;
}

int test_multiplication(void){
	Fraction a = {1, 2};
	Fraction b = {1, 2};
	Fraction c = fraction_multiplication(a, b);
	assert(c.numerator == 1 && c.denominator == 4);

	a = (Fraction) {19, 31};
	b = (Fraction) {20, 10};
	c = fraction_multiplication(a, b);
	assert(c.numerator == 38 && c.denominator == 31);

	a = (Fraction) {-7548, 543};
	b = (Fraction) {93586, 1928};
	c = fraction_multiplication(a, b);
	assert(c.numerator == -29432797 && c.denominator == 43621);

	a = (Fraction) {-1, 1};
	b = (Fraction) {-1, 1};
	c = fraction_multiplication(a, b);
	assert(c.numerator == 1 && c.denominator == 1);

	return EXIT_SUCCESS;
}
int test_inverse(void){
	Fraction a = {1, 2};
	Fraction c = fraction_inverse(a);
	assert(c.numerator == 2 && c.denominator == 1);

	a = (Fraction) {-3, 1};
	c = fraction_inverse(a);
	assert(c.numerator == -1 && c.denominator == 3);

	return EXIT_SUCCESS;
}
int test_division(void){
	Fraction a = {1, 2};
	Fraction b = {1, 2};
	Fraction c = fraction_division(a, b);
	assert(c.numerator == 1 && c.denominator == 1);

	a = (Fraction) {19, 31};
	b = (Fraction) {20, 10};
	c = fraction_division(a, b);
	assert(c.numerator == 19 && c.denominator == 62);

	a = (Fraction) {-7548, 543};
	b = (Fraction) {93586, 1928};
	c = fraction_division(a, b);
	assert(c.numerator == -2425424 && c.denominator == 8469533);

	a = (Fraction) {-1, 1};
	b = (Fraction) {-1, 1};
	c = fraction_division(a, b);
	assert(c.numerator == 1 && c.denominator == 1);

	return EXIT_SUCCESS;
}