/**
 * @file array.h
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#pragma once

#include <stdbool.h>

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

// DESTRUCTORS
extern void array_destroy(void* array);

// GETTERS
extern unsigned int array_get_length(const void *array);
extern unsigned int array_get_size(const void *array);
extern double array_get_ratio(const void *array);

// SETTERS
extern void *array_set_length(void *array, const unsigned int new_length);

// FUNCTIONS
extern unsigned int _array_calculate_max(
	const unsigned int length, const float ratio
);