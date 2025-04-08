/**
 * @file array.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include "array.h"

#include <stdbool.h>
#include <stdlib.h>
#include <stdio.h>

#include "array.inc.h"

// PRIVATE
#define LIB_NAME "array"

static unsigned char initCount;

// Getters
static size_t array_getAllocSize(
	const unsigned int size, const unsigned int max
){
	return sizeof(Array) + max*size;
}

static Array* array_getArray(const void* array){
	return (Array*) array - 1;
}
static void* array_getElements(const Array* arrStruct){
	return (Array*) arrStruct + 1;
}

// PROTECTED
// Functions
unsigned int _array_calculate_max(
	const unsigned int length, const float ratio
){
	return length*2*ratio/(ratio + 1);
}

// PUBLIC
unsigned int array_default_length = 0;
float array_default_ratio = 2.0;

// Initializers / Finishers
bool array_init(void){
	if(initCount++ > 0) return true;

	printf("%s: initialized\n", LIB_NAME);

	return true;
}

bool array_finish(void){
	if(initCount == 0) return false;
	if(--initCount > 0) return true;

	printf("%s: finished\n", LIB_NAME);

	return true;
}

// Constructors
void* array_create_full(
	const unsigned int size, const unsigned int length, float ratio
){
	if(ratio < 1) ratio = 1;

	const unsigned int max = _array_calculate_max(length, ratio);
	Array* arrStruct = malloc(array_getAllocSize(size, max));

	if(arrStruct == NULL) return NULL;

	arrStruct->size = size;
	arrStruct->length = length;
	arrStruct->max = max;
	arrStruct->ratio = ratio;

	return array_getElements(arrStruct);
}
void* array_create_default(const unsigned int size){
	return array_create_full(size, array_default_length, array_default_ratio);
}

// Destructors
void array_destroy(void* array){
	free(array_getArray(array));
}

// Getters
unsigned int array_get_length(const void *array){
	return array_getArray(array)->length;
}
unsigned int array_get_size(const void *array){
	return array_getArray(array)->size;
}
double array_get_ratio(const void *array){
	return array_getArray(array)->ratio;
}

// Setters
void *array_set_length(void *array, const unsigned int length){
	Array* arrStruct = array_getArray(array);
	const unsigned int max = _array_calculate_max(length, arrStruct->ratio);

	arrStruct->length = length;
	arrStruct->max = max;

	return array_getElements(realloc(arrStruct,
		array_getAllocSize(arrStruct->size, max)
	));
}