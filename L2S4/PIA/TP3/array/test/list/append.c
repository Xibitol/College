/**
 * @file append.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdbool.h>
#include <stdlib.h>
#include <assert.h>
#include <stdio.h>

#include "list.h"

#include "list.inc.h"

int test_list_append(void){
	char a = 31;
	char b = 1;

	list_init();
	List* l;
	{
		l = list_append(NULL, &a);
		assert(*((char*) l->data) == a);
		assert(l->next == NULL);

		l = list_append(l, &b);
		assert(*((char*) l->data) == a);
		assert(*((char*) l->next->data) == b);
		assert(l->next->next == NULL);
	}
	list_destroy(l);
	list_finish();

	return EXIT_SUCCESS;
}