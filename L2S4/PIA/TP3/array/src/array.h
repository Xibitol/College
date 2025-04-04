/**
 * @file array.h
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#pragma once

#include <stdbool.h>

// MACROS
#define _ARRAY_REPR_START "["
#define _ARRAY_REPR_SEPARATOR ", "
#define _ARRAY_REPR_LAST "..."
#define _ARRAY_REPR_END "]"
#define _ARRAY_REPR_MAX_SIZE 128 + 1
#define _ARRAY_REPR_CONTENT_MAX_SIZE _ARRAY_REPR_MAX_SIZE \
	- sizeof _ARRAY_REPR_START - sizeof _ARRAY_REPR_END \
	- sizeof _ARRAY_REPR_SEPARATOR - sizeof _ARRAY_REPR_LAST

// TYPE DEFS
typedef struct _ArrayIterator ArrayIterator;

// VARIABLES
extern unsigned int array_default_length;
extern float array_default_ratio;

// INITIALIZERS / FINISHERS
extern bool array_init(void);
extern bool array_finish(void);

// CONSTRUCTORS
extern void* array_create_full(
	const unsigned int size, const unsigned int length, float ratio
);
extern void* array_create_default(const unsigned int size);

extern ArrayIterator* array_iterator_create(const void* array);

// DESTRUCTORS
extern void array_destroy(void* array);

extern void array_iterator_destroy(ArrayIterator* iterator);

// GETTERS
extern unsigned int array_get_length(const void *array);
extern unsigned int array_get_size(const void *array);
extern double array_get_ratio(const void *array);

extern void* array_iterator_get(const ArrayIterator* iterator);
extern bool array_iterator_valid(const ArrayIterator* iterator);

// SETTERS
extern void *array_set_length(void *array, const unsigned int new_length);

extern void* array_insert_values(
	void* dest, const void* src, unsigned int index, const unsigned int length
);
extern void* array_insert_value(
	void* dest, const void* src, unsigned int index
);

extern void* array_append_values(
	void* dest, const void* src, const unsigned int length
);
extern void* array_append_value(void* dest, const void* src);

extern void* array_prepend_values(
	void* dest, const void* src, const unsigned int length
);
extern void* array_prepend_value(void* dest, const void* src);

extern void* array_remove_values(
	void* array, const unsigned int index, unsigned int length
);
extern void* array_remove_value(void* array, const unsigned int index);

extern ArrayIterator* array_iterator_next(ArrayIterator* iterator);
extern ArrayIterator* array_iterator_rewind(ArrayIterator* iterator);

// FUNCTIONS
extern unsigned int _array_calculate_max(
	const unsigned int length, const float ratio
);

extern const char* array_to_string(
	const void* array, const char* (*to_string)(const void*)
);