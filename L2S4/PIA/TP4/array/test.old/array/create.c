/**
 * @file create.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdlib.h>
#include <assert.h>

#include "array.h"

#include "array.inc.h"

int test_array_create(void){
	unsigned int length = 10;
	char* a;
	Array* arr;

	array_init();
	{
		a = array_create_full(sizeof *a, length, array_default_ratio);
		for(unsigned int i = 0; i < length; i++) a[i] = i*i;
		array_destroy(a);
	}
	{
		a = array_create_full(sizeof *a, length, -1);
		arr = (Array*) a - 1;

		assert(arr->ratio == 1);

		for(unsigned int i = 0; i < length; i++) a[i] = i*i;

		array_destroy(a);
	}
	{
		a = array_create_default(sizeof *a);
		arr = (Array*) a - 1;

		assert(arr->length == array_default_length);
		assert(arr->ratio == array_default_ratio);

		for(unsigned int i = 0; i < array_default_length; i++) a[i] = i*i;

		array_destroy(a);
	}
	array_finish();

	return EXIT_SUCCESS;
}