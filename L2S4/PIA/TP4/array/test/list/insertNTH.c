/**
 * @file insertNTH.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdlib.h>
#include <assert.h>

#include "list.h"

#include "list.inc.h"

int test_list_insertNTH(void){
	char a = 31;
	char b = 1;
	char c = 20;
	char d = 24;
	char e = 34;

	list_init();

	List* l = NULL;

	{
		l = list_insert_nth(NULL, 0, &a);

		assert(list_index(l, &a) == 0);

		list_destroy(l);
	}

	l = list_append(list_append(list_append(NULL, &a), &b), &c);
	{
		l = list_insert_nth(l, 1, &d);
		assert(list_index(l, &d) == 1);
		assert(list_index(l, &b) == 2);
		assert(list_length(l) == 4);

		l = list_insert_nth(l, 6, &e);
		assert(list_index(l, &e) == 4);
		assert(list_length(l) == 5);
	}
	list_destroy(l);

	list_finish();

	return EXIT_SUCCESS;
}