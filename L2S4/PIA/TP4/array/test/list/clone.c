/**
 * @file clone.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdlib.h>
#include <assert.h>

#include "list.h"

#include "list.inc.h"

int test_list_clone(void){
	char a = 31;
	char b = 1;
	char c = 20;

	list_init();

	List* l = NULL,* clonedL = NULL;

	{
		assert(list_clone(NULL) == NULL);
	}

	l = list_append(list_append(list_append(NULL, &a), &b), &c);
	{
		clonedL = list_clone(l);
		assert(list_index(clonedL, &a) == 0);
		assert(list_index(clonedL, &b) == 1);
		assert(list_index(clonedL, &c) == 2);
		assert(list_length(clonedL) == 3);
	}
	list_destroy(l);
	list_destroy(clonedL);

	list_finish();

	return EXIT_SUCCESS;
}