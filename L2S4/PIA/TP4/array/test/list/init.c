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

#include "list.h"

int test_list_init(void){
	{
		assert(list_init());
		assert(list_init());
		assert(list_init());

		assert(list_finish());
		assert(list_finish());
		assert(list_finish());
		assert(list_finish() == false);
	}

	return EXIT_SUCCESS;
}