/**
 * @file sort.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdlib.h>
#include <assert.h>

#include "list.h"

#include "list.inc.h"

int compareChars(const void* a, const void* b){
	char ca = *((char*) a), cb = *((char*) b);
	char r = ca - cb;

	return r < 0 ? -1 : (r == 0 ? 0 : 1);
}

int test_list_sort(void){
	char b = 1;
	char d = 20;
	char c = 24;
	char e = 25;
	char a = 31;

	list_init();

	List* l = list_append(list_append(list_append(list_append(list_append(NULL,
		&a), &b), &c), &d), &e
	);
	List* sortedL;

	{
		sortedL = list_bubble_sort(list_clone(l), &compareChars);

		assert(list_index(sortedL, &b) == 0);
		assert(list_index(sortedL, &d) == 1);
		assert(list_index(sortedL, &c) == 2);
		assert(list_index(sortedL, &e) == 3);
		assert(list_index(sortedL, &a) == 4);
		assert(list_length(sortedL) == 5);

		list_destroy(sortedL);
	}

	{
		// sortedL = list_quick_sort(list_clone(l), &compareChars);

		// assert(list_index(sortedL, &b) == 0);
		// assert(list_index(sortedL, &d) == 1);
		// assert(list_index(sortedL, &c) == 2);
		// assert(list_index(sortedL, &a) == 3);
		// assert(list_length(sortedL) == 4);

		// list_destroy(sortedL);
	}

	{
		sortedL = list_merge_sort(list_clone(l), &compareChars);

		assert(list_index(sortedL, &b) == 0);
		assert(list_index(sortedL, &d) == 1);
		assert(list_index(sortedL, &c) == 2);
		assert(list_index(sortedL, &e) == 3);
		assert(list_index(sortedL, &a) == 4);
		assert(list_length(sortedL) == 5);

		list_destroy(sortedL);
	}

	list_destroy(l);

	list_finish();

	return EXIT_SUCCESS;
}