/**
 * @file array.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include "array.h"

#include <stdbool.h>
#include <stdint.h>
#include <string.h>
#include <malloc.h>
#include <stdlib.h>
#include <assert.h>
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

// Assertions
static void checkInitialized(void){
	assert(initCount > 0);
}
static void checkFinished(void){
	assert(initCount == 0);
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

	atexit(&checkFinished);

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
	checkInitialized();

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

ArrayIterator* array_iterator_create(const void* array){
	checkInitialized();

	ArrayIterator* iter = malloc(sizeof(ArrayIterator));
	if(iter == NULL) return NULL;

	iter->array = array;
	iter->index = 0;

	return iter;
}

// Destructors
void array_destroy(void* array){
	checkInitialized();

	free(array_getArray(array));
}

void array_iterator_destroy(ArrayIterator* iterator){
	checkInitialized();

	free(iterator);
}

// Getters
unsigned int array_get_length(const void *array){
	checkInitialized();

	return array_getArray(array)->length;
}
unsigned int array_get_size(const void *array){
	checkInitialized();

	return array_getArray(array)->size;
}
double array_get_ratio(const void *array){
	checkInitialized();

	return array_getArray(array)->ratio;
}

void* array_iterator_get(const ArrayIterator* iterator){
	checkInitialized();

	return (int8_t*) iterator->array
		+ iterator->index*array_get_size(iterator->array);
}
bool array_iterator_valid(const ArrayIterator* iterator){
	checkInitialized();

	return iterator->index < array_get_length(iterator->array) - 1;
}

// Setters
void* array_set_length(void *array, const unsigned int length){
	checkInitialized();

	Array* arrStruct = array_getArray(array);
	arrStruct->length = length;

	if(length < arrStruct->max/arrStruct->ratio || length > arrStruct->max){
		const unsigned int max = _array_calculate_max(length, arrStruct->ratio);
		arrStruct->max = max;

		arrStruct = realloc(arrStruct,
			array_getAllocSize(arrStruct->size, max)
		);
	}

	return arrStruct == NULL ? NULL : array_getElements(arrStruct);
}

void* array_insert_values(void* dest, const void* src,
	unsigned int index, const unsigned int length
){
	checkInitialized();

	const unsigned int oldLength = array_get_length(dest);
	if(index > oldLength) index = oldLength; // FIXME: Throw an error.

	if((dest = array_set_length(dest, oldLength + length)) == NULL)
		return NULL;

	if(index < oldLength)
		memmove(
			(int8_t*) dest + (index + length)*array_get_size(dest),
			(int8_t*) dest + index*array_get_size(dest),
			(oldLength - index)*array_get_size(dest)
		);

	memcpy(
		(int8_t*) dest + index*array_get_size(dest),
		src,
		length*array_get_size(dest)
	);

	return dest;
}
void* array_insert_value(void* dest, const void* src, unsigned int index){
	return array_insert_values(dest, src, index, 1);
}

void* array_append_values(
	void* dest, const void* src, const unsigned int length
){
	return array_insert_values(dest, src, array_get_length(dest), length);
}
void* array_append_value(void* dest, const void* src){
	return array_append_values(dest, src, 1);
}

void* array_prepend_values(
	void* dest, const void* src, const unsigned int length
){
	return array_insert_values(dest, src, 0, length);
}
void* array_prepend_value(void* dest, const void* src){
	return array_prepend_values(dest, src, 1);
}

void* array_remove_values(
	void* array, const unsigned int index, unsigned int length
){
	checkInitialized();

	const unsigned int oldLength = array_get_length(array);
	if(index > oldLength) return array;
	else if(length > oldLength - index) length = oldLength - index;

	memmove(
		(int8_t*) array + index*array_get_size(array),
		(int8_t*) array + (index + length)*array_get_size(array),
		(oldLength - index - length)*array_get_size(array)
	);

	return array_set_length(array, oldLength - length);
}
void* array_remove_value(void* array, const unsigned int index){
	return array_remove_values(array, index, 1);
}

ArrayIterator* array_iterator_next(ArrayIterator* iterator){
	checkInitialized();

	if(array_iterator_valid(iterator)) iterator->index++;
	return iterator;
}
ArrayIterator* array_iterator_rewind(ArrayIterator* iterator){
	checkInitialized();

	iterator->index = 0;
	return iterator;
}

// Functions
const char* array_to_string(
	const void* array, const char* (*to_string)(const void*)
){
	checkInitialized();

	static char repr[_ARRAY_REPR_MAX_SIZE];
	const int8_t* eLast = (const int8_t*) array
		+ (array_get_length(array) - 1)*array_get_size(array);

	repr[0] = '\0';

	strcat(repr, _ARRAY_REPR_START);

	const char* eString;
	unsigned int eSize;
	for(int8_t* e = (int8_t*) array; e <= eLast; e += array_get_size(array)){
		eString = to_string(e);
		eSize = strlen(eString);

		if(strlen(repr) + eSize
			+ (e != eLast ? sizeof _ARRAY_REPR_SEPARATOR - 1 : 0)
				> _ARRAY_REPR_CONTENT_MAX_SIZE
		){
			strcat(repr, _ARRAY_REPR_LAST);
			break;
		}

		strncat(repr, eString, eSize);

		if(e != eLast) strcat(repr, _ARRAY_REPR_SEPARATOR);
	}

	strcat(repr, _ARRAY_REPR_END);

	return repr;
}