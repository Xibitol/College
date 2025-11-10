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