/**
 * @file list.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include "list.h"

#include <stdbool.h>
#include <stdlib.h>
#include <assert.h>
#include <stdio.h>

#include "list.inc.h"

// PRIVATE
#define LIB_NAME "list"

static unsigned char initCount;

// Constructors
static List* list_create(const void* data, List* next){
	List* list = malloc(sizeof(List));

	if(list == NULL) return NULL;

	list->data = data;
	list->next = next;

	return list;
}

// Assertions
#define checkInitialized() assert(initCount > 0)

static void checkFinished(void){
	assert(initCount == 0);
}

// Setters
static List* list_remove_second(List* list){
	checkInitialized();

	if(list != NULL && list->next != NULL){
		List* rList = list->next;

		list->next = rList->next;

		rList->next = NULL;
		list_destroy(rList);
	}

	return list;
}

// PUBLIC
// Initializers / Finishers
bool list_init(void){
	if(initCount++ > 0) return true;

	atexit(&checkFinished);

	printf("%s: initialized\n", LIB_NAME);

	return true;
}

bool list_finish(void){
	if(initCount == 0) return false;
	if(--initCount > 0) return true;

	printf("%s: finished\n", LIB_NAME);

	return true;
}

// Destructors
void list_destroy(List* list){
	List* tmp;

	while(list != NULL){
		tmp = list->next;
		free(list);
		list = tmp;
	}
}

// Getters
unsigned int list_length(const List* list){
	checkInitialized();

	int count = 0;
	while(list != NULL){
		count++;
		list = list->next;
	}
	return count;
}
const void* list_nth(const List* list, const unsigned int nth){
	checkInitialized();

	unsigned int i = 0;
	while(list != NULL && nth > i){
		i++;
		list = list->next;
	}

	return list != NULL ? list->data : NULL;
}
int list_index(const List* list, const void* data){
	checkInitialized();

	unsigned int i = 0;
	while(list != NULL && list->data != data){
		i++;
		list = list->next;
	}
	return list != NULL ? (int) i : -1;
}

// Setters
List* list_prepend(List* list, const void* data){
	checkInitialized();

	return list_create(data, list);
}
List* list_append(List* list, const void* data){
	checkInitialized();

	return list_concat(list, list_create(data, NULL));
}
List* list_concat(List* dest, const List* src){
	checkInitialized();

	if(dest == NULL) return (List*) src;
	else if(src != NULL){
		List* destEnd = dest;
		while(destEnd->next != NULL) destEnd = destEnd->next;

		destEnd->next = (List*) src;
	}

	return dest;
}

List* list_insert_before(
	List* list, const void* before, const void* data
){
	checkInitialized();

	if(list == NULL) return list_create(data, NULL);

	List* bList = list;
	while(bList->next != NULL && bList->next->data != before)
		bList = bList->next;

	if(bList->next == NULL) list = list_prepend(list, data);
	else bList->next = list_prepend(bList->next, data);

	return list;
}
List* list_insert_after(
	List* list, const void* after, const void* data
){
	checkInitialized();

	if(list == NULL) return list_create(data, NULL);

	List* bList = list;
	while(bList->next != NULL && bList->data != after)
		bList = bList->next;

	bList->next = list_prepend(bList->next, data);

	return list;
}
List* list_insert_nth(
	List* list, const unsigned int nth, const void* data
){
	checkInitialized();

	if(list == NULL) return list_create(data, NULL);

	List* bList = list;
	unsigned int i = 0;
	while(bList->next != NULL && i + 1 < nth){
		bList = bList->next;
		i++;
	}

	bList->next = list_prepend(bList->next, data);

	return list;
}

List* list_remove_data(List* list, const void* data){
	checkInitialized();

	if(list != NULL){
		List* bList = list;
		while(bList->next != NULL && bList->next->data != data)
			bList = bList->next;

		list_remove_second(bList);
	}

	return list;
}
List* list_remove_nth(List* list, const unsigned int nth){
	checkInitialized();

	if(list != NULL){
		List* bList = list;
		unsigned int i = 0;
		while(bList->next != NULL && i + 1 < nth){
			bList = bList->next;
			i++;
		}

		list_remove_second(bList);
	}

	return list;
}

// Functions
List* list_clone(List* list){
	checkInitialized();

	if(list == NULL) return list;
	List* newList = list_create(list->data, NULL);

	List* bList = list;
	List* bNewList = newList;
	while(bList->next != NULL){
		bList = bList->next;

		bNewList->next = list_create(bList->data, NULL);
		bNewList = bNewList->next;
	}

	return newList;
}

List* list_reverse(List* list){
	checkInitialized();

	if(list != NULL){
		List* bList = list, *aList = NULL;

		while(bList->next != NULL){
			list = bList->next;

			bList->next = aList;
			aList = bList;

			bList = list;
		}

		list->next = aList;
	}

	return list;
}
List* list_quick_sort(
	List* list, int (*compare)(const void* a, const void* b)
){
	checkInitialized();

	if(list == NULL) return list;

	List pivots;

	List pList;
	List* lList,* rList;

	lList = NULL, rList = NULL;
	pList = list;
	while

	return list;
}
List* list_merge_sort(
	List* list, int (*compare)(const void* a, const void* b)
){
	checkInitialized();

	if(list == NULL) return list;

	List* bList = NULL,* aList;
	List* lList,* rList;
	List* smList;

	int cmpResult;
	unsigned int size = 1, count;
	unsigned int lIdx, rIdx;

	do{
		bList = NULL;
		count = 0;

		while(bList == NULL || bList->next != NULL){
			count++;

			// Partitionates
			lList = bList == NULL ? list : bList->next;

			rList = lList;
			for(unsigned int i = 0; i < size && rList != NULL; i++)
				rList = rList->next;

			// Merges
			aList = NULL;
			lIdx = 0, rIdx = 0;

			while((lIdx < size || rIdx < size)
				&& (lList != NULL || rList != NULL)
			){
				// Checks if right list finished earlier
				if(rList == NULL && rIdx < size) rIdx = size;

				// Compares
				if(lIdx < size && rIdx < size){
					cmpResult = compare(lList->data, rList->data);
					smList = cmpResult != 1 ? lList : rList;
				}else{
					cmpResult = lIdx < size ? -1 : 1;
					smList = lIdx < size ? lList : rList;
				}

				// Merges to list
				if(bList == NULL){
					list = smList;
					bList = list;
				}else{
					bList->next = smList;
					bList = bList->next;
				}

				// Keeps next group if needed
				if(aList == NULL && rIdx + 1 == size)
					aList = rList->next;

				// Reduces buffers
				if(cmpResult != 1){
					lList = smList->next;
					lIdx++;
				}else{
					rList = smList->next;
					rIdx++;
				}
			}

			bList->next = aList;
		}

		size *= 2;
	}while(count != 1);

	return list;
}
List* list_bubble_sort(
	List* list, int (*compare)(const void* a, const void* b)
){
	checkInitialized();

	if(list == NULL) return list;

	bool sorted = false;
	List* bbList,* bList,* aList;
	while(!sorted){
		sorted = true;

		bList = NULL, aList = list;
		while(aList->next != NULL && sorted){
			bbList = bList;
			bList = aList;
			aList = aList->next;

			if(compare(bList->data, aList->data) == 1)
				sorted = false;
		}

		if(!sorted){
			if(bbList != NULL) bbList->next = aList;
			else list = aList;

			bList->next = aList->next;
			aList->next = bList;
		}
	}

	return list;
}