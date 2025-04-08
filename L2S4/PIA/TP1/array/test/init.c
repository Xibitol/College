/**
 * @file init.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdbool.h>
#include <stdlib.h>
#include <assert.h>

#include "array.h"

int test_init(void){
	{
		assert(array_init());
		assert(array_init());
		assert(array_init());

		assert(array_finish());
		assert(array_finish());
		assert(array_finish());
		assert(array_finish() == false);
	}

	return EXIT_SUCCESS;
}