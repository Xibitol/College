#include <stdlib.h>
#include <assert.h>

#include "../src/fraction.h"

static int mallocCallCount = 0;
static int reallocCallCount = 0;
static int freeCallCount = 0;

void *malloc2(size_t size){
	mallocCallCount++;
	return malloc(size);
}
void *realloc2(void *ptr, size_t size){
	reallocCallCount++;
	return realloc(ptr, size);
}
void free2(void *ptr){
	freeCallCount++;
	free(ptr);
}

void (*fraction_free)(void *ptr) = free2;

int test_malloc(void){
	fraction_malloc = malloc2;
	fraction_realloc = realloc2;

	fraction_init();

	Fraction *a = fraction_create_default();
	Fraction *b = fraction_create_default();
	fraction_to_string(a);
	fraction_to_string(b);
	fraction_destroy(a);
	fraction_destroy(b);

	fraction_finish();

	assert(mallocCallCount == 5);
	assert(reallocCallCount == 1);
	assert(freeCallCount == 5);
	return EXIT_SUCCESS;
}