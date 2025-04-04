/**
 * @file nth.c
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

int test_list_nth(void){
	char a = 31;
	char b = 1;
	char c = 20;

	list_init();
	List* l = list_append(list_append(list_append(NULL, &a), &b), &c);
	{
		assert(list_nth(NULL, 1) == NULL);
		assert(*((char*) list_nth(l, 0)) == a);
		assert(*((char*) list_nth(l, 2)) == c);
		assert(list_nth(l, 3) == NULL);
	}
	list_destroy(l);
	list_finish();

	return EXIT_SUCCESS;
}