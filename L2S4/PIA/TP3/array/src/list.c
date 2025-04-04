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
static void checkInitialized(void){
	assert(initCount > 0);
}
static void checkFinished(void){
	assert(initCount == 0);
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

	if(list == NULL) return 0;

	int count = 1;
	while(list->next != NULL){
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
	return list != NULL ? i : -1;
}

// Setters
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

List* list_prepend(List* list, const void* data){
	checkInitialized();

	return list_create(data, list);
}
List* list_append(List* list, const void* data){
	checkInitialized();

	return list_concat(list, list_create(data, NULL));
}