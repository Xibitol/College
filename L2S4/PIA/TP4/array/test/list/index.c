/**
 * @file index.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdlib.h>
#include <assert.h>

#include "list.h"

#include "list.inc.h"

int test_list_index(void){
	char a = 31;
	char b = 1;
	char c = 20;
	char d = 24;

	list_init();
	List* l = list_append(list_append(list_append(NULL, &a), &b), &c);
	{
		assert(list_index(NULL, &a) == -1);
		assert(list_index(l, &a) == 0);
		assert(list_index(l, &c) == 2);
		assert(list_index(l, &d) == -1);
		assert(list_index(l, NULL) == -1);
	}
	list_destroy(l);
	list_finish();

	return EXIT_SUCCESS;
}