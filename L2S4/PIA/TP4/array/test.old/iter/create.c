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

int test_iter_create(void){
	unsigned int length = 10;
	ArrayIterator* iter;

	array_init();

	char* a = array_create_full(sizeof(char), length, array_default_ratio);
	for(unsigned int i = 0; i < length; i++) a[i] = i*i;

	{
		iter = array_iterator_create(a);
		array_iterator_destroy(iter);
	}

	array_destroy(a);

	array_finish();

	return EXIT_SUCCESS;
}