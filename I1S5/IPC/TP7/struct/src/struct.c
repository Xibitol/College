/**
 * @file struct.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdbool.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>

#include <assert.h>

#define eprintf(...) fprintf(stderr, __VA_ARGS__)

static void printExerciseTitle(const unsigned int number);

/** Exercise 1.1 */
struct node{
	int value;
	struct node* next;
};
/** Exercise 1.1 */
struct list{
	struct node* head;
};
/** Exercise 1.2 */
static void insert_head(struct list* l, struct node* n);
/** Exercise 1.3 */
static int length(struct list* l);
/** Exercise 1.4 */
static void print_list(struct list* l);
/** Exercise 1.5 */
static struct node* remove_head(struct list* l);

/** Exercise 2.1 */
void insert_end(struct list* l, struct node* n);
void remove_by_value(struct list* l, int value);

int main(void){
	// Exercise 1
	{
		struct node n1 = {31, NULL};
		struct node n2 = {1, NULL};

		struct list l = {&n2};

		insert_head(NULL, &n1);
		insert_head(&l, &n1);
		assert(l.head == &n1
			&& l.head->value == 31 && l.head->next->value == 1
		);
		l.head = NULL;
		insert_head(&l, NULL);
		assert(l.head == NULL);

		assert(length(&l) == 0);
		l.head = &n1;
		assert(length(&l) == 2);

		print_list(NULL);
		print_list(&l);
		l.head = NULL;
		print_list(&l);

		remove_head(NULL);
		assert(remove_head(&l) == NULL);
		l.head = &n1;
		assert(remove_head(&l) == &n1 && n1.next == NULL);
	}

	// Exercise 2
	{
		struct node n1 = {31, NULL};
		struct node n2 = {1, &n1};
		struct node n3 = {20, NULL};

		struct list l = {NULL};

		insert_end(NULL, &n3);
		insert_end(&l, &n3);
		assert(l.head == &n3);
		l.head = &n2;
		insert_end(&l, NULL);
		assert(n1.next == NULL);
		insert_end(&l, &n3);
		assert(n1.next == &n3);

		remove_by_value(NULL, 1);
		remove_by_value(&l, 24);
		assert(length(&l) == 3);
		remove_by_value(&l, 20);
		assert(length(&l) == 2);
		l.head = NULL;
		remove_by_value(&l, 31);
		assert(length(&l) == 0);
	}

	return EXIT_SUCCESS;
}

void printExerciseTitle(const unsigned int number){
	printf("EX %d ---\n", number);
}

// Exercise 1
void insert_head(struct list* l, struct node* n){
	if(l == NULL) return;
	else if(n == NULL) return;

	n->next = l->head;
	l->head = n;
}
int length(struct list* l){
	unsigned int size = 0;

	struct node* n = l->head;
	while(n != NULL){
		size++;
		n = n->next;
	}

	return size;
}
void print_list(struct list* l){
	if(l == NULL){
		printf("list(undef)\n");
		return;
	}
	printf("list(){");

	struct node* n = l->head;
	while(n != NULL){
		printf(n->next != NULL ? "%d, " : "%d", n->value);
		n = n->next;
	}

	printf("}\n");
}
struct node* remove_head(struct list* l){
	if(l == NULL) return NULL;

	struct node* n = l->head;
	if(n != NULL){
		l->head = n->next;
		n->next = NULL;
	}

	return n;
}

// Exercise 2
void insert_end(struct list* l, struct node* n){
	if(l == NULL) return;
	else if(l->head == NULL){
		l->head = n;
		return;
	}

	struct node* node = l->head;
	while(node->next != NULL) node = node->next;

	node->next = n;
}
void remove_by_value(struct list* l, int value){
	if(l == NULL) return;

	struct node* nBefore = NULL;
	struct node* node = l->head;
	while(node != NULL && node->value != value){
		nBefore = node;
		node = node->next;
	}

	if(nBefore == NULL) l->head = NULL;
	else nBefore->next = node != NULL ? node->next : NULL;
}