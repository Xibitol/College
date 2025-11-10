/**
 * @file enum.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdbool.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>

#include "list.h"

#include <assert.h>

#define GENERATE_ENUM(ENUM) ENUM,
#define GENERATE_STRING(STRING) #STRING,

#define FOREACH_OBJTYPE(OBJTYPE) \
	OBJTYPE(SHOES)  \
	OBJTYPE(TEESHIRT) \
	OBJTYPE(JACKET)  \
	OBJTYPE(PANTS)
#define FOREACH_COLOR(COLOR) \
	COLOR(BALCK)  \
	COLOR(RED) \
	COLOR(GREEN)  \
	COLOR(WHITE)

#define eprintf(...) fprintf(stderr, __VA_ARGS__)

enum ObjType{ FOREACH_OBJTYPE(GENERATE_ENUM) };
enum Color{ FOREACH_COLOR(GENERATE_ENUM) };

struct object{
	enum ObjType type;
	enum Color color;
	float price;
};
struct store{
	const char* name;
	List* objects;
};

static const char *OBJTYPE_REPR[] = { FOREACH_OBJTYPE(GENERATE_STRING) };
static const char *COLOR_REPR[] = { FOREACH_COLOR(GENERATE_STRING) };

static struct object* new_object(
	const enum ObjType type,
	const enum Color color,
	const float price
);
static void free_object(struct object*);
static void print_object(const struct object* obj);

static struct store* new_store(const char* name);
static void print_store(const struct store* sto);
static struct store* add_store(struct store* sto, const struct object* obj);
static void free_store(struct store* sto);

int main(void){
	list_init();

	struct object* obj = new_object(SHOES, GREEN, 64.99);
	print_object(obj);

	struct object* obj2 = new_object(JACKET, WHITE, 125.65);
	
	struct store* sto = new_store("Xib's store");
	print_store(sto);
	add_store(sto, obj);
	add_store(sto, obj2);
	print_store(sto);
	free_store(sto);
	sto = NULL;
	print_store(sto);

	free_object(obj);
	free_object(obj2);
	obj = NULL;
	print_object(obj);

	list_finish();
	return EXIT_SUCCESS;
}

struct object* new_object(
	const enum ObjType type,
	const enum Color color,
	const float price
){
	struct object* obj = malloc(sizeof(struct object));
	if(obj == NULL) return NULL;

	obj->type = type;
	obj->color = color;
	obj->price = price;

	return obj;
}
void free_object(struct object* obj){
	if(obj != NULL) free(obj);
}
void print_object(const struct object* obj){
	if(obj == NULL){
		printf("struct object(nil)\n");
		return;
	}

	printf("struct object{type=%s,color=%s,price=%f}\n",
		OBJTYPE_REPR[obj->type],
		COLOR_REPR[obj->color],
		obj->price
	);
}

struct store* new_store(const char* name){
	struct store* sto = malloc(sizeof(struct store));
	if(sto == NULL) return NULL;

	sto->name = name;
	sto->objects = NULL;

	return sto;
}
void free_store(struct store* sto){
	if(sto != NULL){
		if(sto->objects != NULL) list_destroy(sto->objects);

		free(sto);
	}
}
void print_store(const struct store* sto){
	if(sto == NULL){
		printf("struct store(nil)\n");
		return;
	}
	const unsigned int size = list_length(sto->objects);

	printf("struct store(%d){name=%s,objects=[\n", size, sto->name);
	for(unsigned int i = 0; i < size; i++){
		printf("\t");
		print_object(list_nth(sto->objects, i));
	}
	printf("]}\n");
}
struct store* add_store(struct store* sto, const struct object* obj){
	if(sto == NULL) return sto;

	sto->objects = list_append(sto->objects, obj);

	return sto;
}