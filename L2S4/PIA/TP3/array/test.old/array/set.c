/**
 * @file set.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdlib.h>
#include <assert.h>

#include "array.h"

#include "array.inc.h"

int test_array_set(void){
	unsigned int length = 5;

	array_init();
	char* a = array_create_full(sizeof(char), length, array_default_ratio);
	{
		a = array_set_length(a, length += 5);
		for(unsigned int i = 0; i < length; i++) a[i] = i*i;
	}
	array_destroy(a);
	array_finish();

	return EXIT_SUCCESS;
}