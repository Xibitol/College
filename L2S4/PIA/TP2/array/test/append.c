/**
 * @file append.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdlib.h>
#include <assert.h>

#include "array.h"

int test_append(void){
	unsigned int v = 310120;
	unsigned int vs[20] = {
		0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
		11, 12, 13, 14, 15, 16, 17, 18, 19
	};

	array_init();
	unsigned int* a = array_create_default(sizeof *vs);
	{
		a = array_append_value(a, &v);
		a = array_append_values(a, vs, sizeof vs/sizeof *vs);

		assert(array_get_length(a) == sizeof vs/sizeof *vs + 1);
		assert(a[0] == v);
		for(unsigned int i = 1; i < array_get_length(a); i++)
			assert(a[i] == i - 1);
	}
	array_destroy(a);
	array_finish();

	return EXIT_SUCCESS;
}