/**
 * @file prepend.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdlib.h>
#include <assert.h>

#include "list.h"

#include "list.inc.h"

int test_list_prepend(void){
	char a = 31;
	char b = 1;

	list_init();
	List* l;
	{
		l = list_prepend(NULL, &a);
		assert(*((char*) l->data) == a);
		assert(l->next == NULL);

		l = list_prepend(l, &b);
		assert(*((char*) l->data) == b);
		assert(*((char*) l->next->data) == a);
		assert(l->next->next == NULL);
	}
	list_destroy(l);
	list_finish();

	return EXIT_SUCCESS;
}