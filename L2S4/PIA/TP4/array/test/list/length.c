/**
 * @file length.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdlib.h>
#include <assert.h>

#include "list.h"

#include "list.inc.h"

int test_list_length(void){
	char a = 31;
	char b = 1;
	char c = 20;

	list_init();
	List* l = list_append(list_append(NULL, &a), &b);
	{
		assert(list_length(NULL) == 0);

		assert(list_length(l) == 2);

		l = list_append(l, &c);
		assert(list_length(l) == 3);
	}
	list_destroy(l);
	list_finish();

	return EXIT_SUCCESS;
}