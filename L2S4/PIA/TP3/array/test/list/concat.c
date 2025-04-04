/**
 * @file concat.c
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

int test_list_concat(void){
	char a = 31;
	char b = 1;

	list_init();

	List* l;
	List* al = list_append(NULL, &a);
	List* bl = list_append(NULL, &b);

	{
		l = list_concat(NULL, NULL);
		assert(l == NULL);

		l = list_concat(NULL, al);
		assert(l == al);

		l = list_concat(bl, NULL);
		assert(l == bl);

		l = list_concat(al, bl);
		assert(*((char*) l->data) == a);
		assert(*((char*) l->next->data) == b);
		assert(l->next->next == NULL);
	}

	list_destroy(l);

	list_finish();

	return EXIT_SUCCESS;
}