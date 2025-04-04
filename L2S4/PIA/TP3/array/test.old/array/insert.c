/**
 * @file insert.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdlib.h>
#include <stdio.h>
#include <assert.h>

#include "array.h"

int test_array_insert(void){
	unsigned int v = 310120;
	unsigned int vs[20] = {
		0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
		11, 12, 13, 14, 15, 16, 17, 18, 19
	};
	unsigned int pos = sizeof vs/sizeof *vs/2;

	array_init();
	unsigned int* a = array_create_default(sizeof *vs);
	{
		a = array_insert_values(a, vs, 1, sizeof vs/sizeof *vs);
		a = array_insert_value(a, &v, pos);

		assert(array_get_length(a) == sizeof vs/sizeof *vs + 1);
		assert(a[pos] == v);
		for(unsigned int i = 0; i < array_get_length(a); i++)
			if(i != pos)
				assert(a[i] == (i < pos ? i : i - 1));
	}
	array_destroy(a);
	array_finish();

	return EXIT_SUCCESS;
}