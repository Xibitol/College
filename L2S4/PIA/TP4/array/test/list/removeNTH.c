/**
 * @file removeNTH.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdlib.h>
#include <assert.h>

#include "list.h"

#include "list.inc.h"

int test_list_removeNTH(void){
	char a = 31;
	char b = 1;
	char c = 20;
	char d = 24;

	list_init();

	List* l = NULL;

	{
		assert(list_remove_nth(NULL, 1) == NULL);
	}

	l = list_append(
		list_append(list_append(list_append(NULL, &a), &b), &c), &d
	);
	{
		l = list_remove_nth(l, 2);
		assert(list_index(l, &b) == 1);
		assert(list_index(l, &c) == -1);
		assert(list_index(l, &d) == 2);
		assert(list_length(l) == 3);

		l = list_remove_nth(l, 3);
		assert(list_length(l) == 3);
	}
	list_destroy(l);

	list_finish();

	return EXIT_SUCCESS;
}