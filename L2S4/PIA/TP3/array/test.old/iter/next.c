/**
 * @file next.c
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

int test_iter_next(void){
	unsigned int vs[] = {310120, 310124};
	ArrayIterator* iter;

	array_init();

	unsigned int* a = array_create_default(sizeof *vs);
	a = array_append_values(a, vs, sizeof vs/sizeof *vs);
	iter = array_iterator_create(a);

	{
		assert(*((unsigned int*) array_iterator_get(iter)) == vs[0]);
		assert(array_iterator_valid(iter) == true);

		array_iterator_next(iter);

		assert(*((unsigned int*) array_iterator_get(iter)) == vs[1]);
		assert(array_iterator_valid(iter) == false);

		array_iterator_next(iter);

		assert(*((unsigned int*) array_iterator_get(iter)) == vs[1]);
		assert(array_iterator_valid(iter) == false);
	}

	array_iterator_destroy(iter);
	array_destroy(a);

	array_finish();

	return EXIT_SUCCESS;
}