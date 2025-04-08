/**
 * @file getters.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdbool.h>
#include <stdlib.h>
#include <assert.h>

#include "array.h"

#include "array.inc.h"

int test_iter_getters(void){
	unsigned int v = 310120;
	unsigned int w = 310124;
	unsigned int* a;
	ArrayIterator* iter;

	array_init();
	a = array_create_default(sizeof v);

	a = array_append_value(a, &v);
	iter = array_iterator_create(a);
	{
		assert(*((unsigned int*) array_iterator_get(iter)) == v);
		assert(array_iterator_valid(iter) == false);
	}
	array_iterator_destroy(iter);

	a = array_append_value(a, &w);
	iter = array_iterator_create(a);
	{
		assert(*((unsigned int*) array_iterator_get(iter)) == v);
		assert(array_iterator_valid(iter) == true);

	}
	array_iterator_destroy(iter);

	array_destroy(a);
	array_finish();

	return EXIT_SUCCESS;
}