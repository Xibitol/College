/**
 * @file get.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdlib.h>
#include <assert.h>

#include "array.h"

#include "array.inc.h"

int test_get(void){
	array_init();
	const size_t size = sizeof(char);
	char* a = array_create_default(size);

	{
		assert(array_get_size(a) == size);
		assert(array_get_length(a) == array_default_length);
		assert(array_get_ratio(a) == array_default_ratio);

	}

	array_destroy(a);
	array_finish();

	return EXIT_SUCCESS;
}