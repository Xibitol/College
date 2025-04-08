/**
 * @file insertAfter.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdlib.h>
#include <assert.h>

#include "list.h"

#include "list.inc.h"

int test_list_insertAfter(void){
	char a = 31;
	char b = 1;
	char c = 20;
	char d = 24;
	char e = 34;

	list_init();

	List* l = NULL;

	{
		l = list_insert_after(NULL, NULL, &a);

		assert(list_index(l, &a) == 0);

		list_destroy(l);
	}

	l = list_append(list_append(list_append(NULL, &a), &b), &c);
	{
		l = list_insert_after(l, &b, &d);
		assert(list_index(l, &d) == 2);
		assert(list_index(l, &c) == 3);
		assert(list_length(l) == 4);

		l = list_insert_after(l, &e, &e);
		assert(list_index(l, &e) == 4);
		assert(list_length(l) == 5);
	}
	list_destroy(l);

	list_finish();

	return EXIT_SUCCESS;
}