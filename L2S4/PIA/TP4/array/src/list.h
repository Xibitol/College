/**
 * @file list.h
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#pragma once

#include <stdbool.h>

// TYPE DEFS
typedef struct _List List;

// INITIALIZERS / FINISHERS
extern bool list_init(void);
extern bool list_finish(void);

// DESTRUCTORS
extern void list_destroy(List* list);

// GETTERS
extern unsigned int list_length(const List* list);
extern const void* list_nth(const List* list, const unsigned int nth);
extern int list_index(const List* list, const void* data);

// SETTERS
extern List* list_prepend(List* list, const void* data);
extern List* list_append(List* list, const void* data);
extern List* list_concat(List* dest, const List* src);

extern List* list_insert_before(
	List* list, const void *before, const void* data
);
extern List* list_insert_after(
	List* list, const void *after, const void* data
);
extern List* list_insert_nth(
	List* list, const unsigned int nth, const void* data
);

extern List* list_remove_data(List* list, const void* data);
extern List* list_remove_nth(List* list, const unsigned int nth);

// FUNCTIONS
extern List* list_clone(List* list);

extern List* list_reverse(List* list);
extern List* list_quick_sort(
	List* list, int (*compare)(const void* a, const void* b)
);
extern List* list_merge_sort(
	List* list, int (*compare)(const void* a, const void* b)
);
extern List* list_bubble_sort(
	List* list, int (*compare)(const void* a, const void* b)
);