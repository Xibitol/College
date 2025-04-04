/**
 * @file reverse.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdlib.h>
#include <assert.h>

#include "list.h"

#include "list.inc.h"

int test_list_reverse(void){
	char a = 31;
	char b = 1;
	char c = 20;

	list_init();

	List* l = NULL;

	{
		assert(list_reverse(NULL) == NULL);
	}

	l = list_append(list_append(list_append(NULL, &a), &b), &c);
	{
		l = list_reverse(l);
		assert(list_index(l, &c) == 0);
		assert(list_index(l, &b) == 1);
		assert(list_index(l, &a) == 2);
		assert(list_length(l) == 3);
	}
	list_destroy(l);

	list_finish();

	return EXIT_SUCCESS;
}