#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <assert.h>

#ifdef NDEBUG
#undef NDEBUG
#endif

#include "../src/fraction.h"

// Initialization
static int test_init(void);

// Creation and Destruction
static int test_fill(void);
static int test_mem(void);

// Cloning
static int test_copy(void);
static int test_clone(void);

// Utils
static int test_sgn(void);
static int test_reduce(void);

// Arithmetic operations
static int test_addition(void);
static int test_symmetric(void);
static int test_substraction(void);

static int test_multiplication(void);
static int test_inverse(void);
static int test_division(void);

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

	char name = argv[1][0];
	switch(name){
		case 'z': return test_init();

		case 'f': return test_fill();
		case 'e': return test_mem();

		case 'c': return test_copy();
		case 'o': return test_clone();

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
// Initialization
static int test_init(void){
	assert(fraction_init());
	assert(fraction_init());
	assert(fraction_init());

	assert(fraction_finish());
	assert(fraction_finish());
	assert(fraction_finish());
	assert(!fraction_finish());

	return EXIT_SUCCESS;
}

// Creation and Destruction
static int test_fill(void){
	fraction_init();

	Fraction a;
	Fraction *c = fraction_fill_full(&a, 0, 0);
	assert(c->numerator == 0 && c->denominator == 0 && &a == c);

	c = fraction_fill_full(&a, 30, 370);
	assert(
		a.numerator == 3 && a.denominator == 37 &&
		c->numerator == 3 && c->denominator == 37
	);

	c = fraction_fill_full(&a, 28, 4);
	assert(
		a.numerator == 7 && a.denominator == 1 &&
		c->numerator == 7 && c->denominator == 1
	);

	fraction_finish();
	return EXIT_SUCCESS;
}
static int test_mem(void){
	fraction_init();

	Fraction *a = fraction_create_full(2, 4);
	Fraction *b = fraction_create_full(6, 8);
	assert(
		a->numerator == 1 && a->denominator == 2
		&& b->numerator == 3 && b->denominator == 4
	);

	fraction_destroy(a);
	fraction_destroy(b);

	a = fraction_create_default();
	assert(
		a->numerator == fraction_numerator_default
		&& a->denominator == fraction_denominator_default
	);

	fraction_destroy(a);

	fraction_finish();
	return EXIT_SUCCESS;
}

// Cloning
static int test_copy(void){
	fraction_init();

	Fraction *a = fraction_create_full(1, 2);
	Fraction *b = fraction_create_full(3, 4);
	Fraction *c = fraction_copy(b, a);
	assert(b->numerator == 1 && b->denominator == 2 && a != b && b == c);

	fraction_destroy(a);
	fraction_destroy(b);

	fraction_finish();
	return EXIT_SUCCESS;
}
static int test_clone(void){
	fraction_init();

	Fraction *a = fraction_create_full(1, 2);
	Fraction *b = fraction_clone(a);
	assert(b->numerator == 1 && b->denominator == 2 && a != b);

	fraction_destroy(a);
	fraction_destroy(b);

	fraction_finish();
	return EXIT_SUCCESS;
}

// Utils
static int test_sgn(void){
	assert(_sgn(1) == 1);
	assert(_sgn(-2) == -1);
	assert(_sgn(6) == 1);
	assert(_sgn(0) == 1);
	assert(_sgn(-1) == -1);

	return EXIT_SUCCESS;
}
static int test_reduce(void){
	fraction_init();

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

	fraction_finish();
	return EXIT_SUCCESS;
}

// Arithmetic operations
static int test_addition(void){
	fraction_init();

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

	fraction_finish();
	return EXIT_SUCCESS;
}
static int test_symmetric(void){
	fraction_init();

	Fraction a = {1, 2};
	Fraction c = fraction_symmetric(a);
	assert(c.numerator == -1 && c.denominator == 2);

	fraction_finish();
	return EXIT_SUCCESS;
}
static int test_substraction(void){
	fraction_init();

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

	fraction_finish();
	return EXIT_SUCCESS;
}

static int test_multiplication(void){
	fraction_init();

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

	fraction_finish();
	return EXIT_SUCCESS;
}
static int test_inverse(void){
	fraction_init();

	Fraction a = {1, 2};
	Fraction c = fraction_inverse(a);
	assert(c.numerator == 2 && c.denominator == 1);

	a = (Fraction) {-3, 1};
	c = fraction_inverse(a);
	assert(c.numerator == -1 && c.denominator == 3);

	fraction_finish();
	return EXIT_SUCCESS;
}
static int test_division(void){
	fraction_init();

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

	fraction_finish();
	return EXIT_SUCCESS;
}