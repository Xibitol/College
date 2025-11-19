/* ... */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* ... */

#define MAX_INGREDIENTS 10

struct product {
    int id;           // ...
    const char *name; // ...
    int quantity;     // ...
    int unit_price;   // ...
};

struct ingredient {
    int product_id; // ...
    int quantity;   // ...
                    // ...
};

struct recipe {
    const char *name;     // ...
    int price;            // ...
    int ingredient_count; // ...
    struct ingredient ingredients[MAX_INGREDIENTS]; // ...
};

// ...
#define STOCK_LENGTH    12
extern struct product stock[];

// ...
#define MENU_LENGTH     5
extern struct recipe menu[];

/* ... */
struct product *product_by_name(const char *name){
	unsigned int i = 0;
	while(i < STOCK_LENGTH && strcmp(stock[i].name, name)) i++;

	return i < STOCK_LENGTH ? &stock[i] : NULL;
}

/* ... */
int cost(struct recipe cocktail){
	static struct product p;

	int price = 0;

	for(unsigned int i = 0; i < (unsigned int) cocktail.ingredient_count; i++){
		p = stock[cocktail.ingredients[i].product_id];
		price += p.unit_price*cocktail.ingredients[i].quantity;
	}

	return price;
}

/* ... */
const struct recipe *most_profitable(int size, const struct recipe list[]){
	static int diff;
	if(size == 0) return NULL;

	const struct recipe *best = &list[0];
	int lastDiff = list[0].price - cost(list[0]);

	for(unsigned int i = 0; i < (unsigned int) size; i++){
		diff = list[i].price - cost(list[i]);

		if(lastDiff < diff){
			best = &list[i];
			lastDiff = diff;
		}
	}

	return best;
}

/* ... */
void print_recipe(struct recipe cocktail){
	printf("%s (%de):", cocktail.name, cocktail.price);

	for(unsigned int i = 0; i < (unsigned int) cocktail.ingredient_count; i++){
		printf(" %s (%d)",
			stock[cocktail.ingredients[i].product_id].name,
			cocktail.ingredients[i].quantity
		);

		if(i + 1 < (unsigned int) cocktail.ingredient_count)
			printf(",");
	}
}

/* ... */
struct ingredient *recipe_contains(struct recipe *cocktail, int product_id){
	unsigned int i = 0;
	while(i < (unsigned int) cocktail->ingredient_count
		&& cocktail->ingredients[i].product_id != product_id
	) i++;

	return i < (unsigned int) cocktail->ingredient_count ?
		&(cocktail->ingredients[i]) : NULL;
}

/* ... */
void merge_ingredient(
	struct recipe *cocktail, const struct ingredient ingredient
){
	struct ingredient * i = recipe_contains(cocktail, ingredient.product_id);

	if(i != NULL){
		i->quantity += ingredient.quantity;
	}else{
		cocktail->ingredients[cocktail->ingredient_count] = ingredient;
		cocktail->ingredient_count++;
	}
}
    ;
/* ... */
int can_mix_cocktail(struct recipe cocktail){
	unsigned int i = 0;
	while(i < (unsigned int) cocktail.ingredient_count
		&& cocktail.ingredients[i].quantity
			<= stock[cocktail.ingredients[i].product_id].quantity
	) i++;

	return i >= (unsigned int) cocktail.ingredient_count;
}

/* ... */
void mix_cocktail(struct recipe cocktail){
	if(!can_mix_cocktail(cocktail)) return;

	for(unsigned int i = 0; i < (unsigned int) cocktail.ingredient_count; i++){
		stock[cocktail.ingredients[i].product_id].quantity -= (
			cocktail.ingredients[i].quantity
		);
	}
}

/* ... */
int recipes_available(int size, const struct recipe cocktails[],
    struct recipe cocktails_left[]
){
	unsigned int count = 0;

	for(unsigned int i = 0; i < (unsigned int) size; i++)
		if(can_mix_cocktail(cocktails[i]))
			cocktails_left[count++] = cocktails[i];

	return count;
}

/* ... */
int cocktails_with(int products_count, const int products_ids[],
    int cocktails_count, struct recipe cocktails[],
    struct recipe proposals[]
){
	static unsigned int j;

	unsigned int count = 0;

	for(unsigned int i = 0; i < (unsigned int) cocktails_count; i++){
		j = 0;
		while(j < (unsigned int) products_count
			&& recipe_contains(&cocktails[i], products_ids[j])
		) j++;

		if(j >= (unsigned int) products_count)
			proposals[count++] = cocktails[i];
	}

	return count;
}

/* ... */
const char *sneaky_bartender(
    int products_count, const int products_ids[],
    int cocktails_count, struct recipe cocktails[]
// ...
){
	struct recipe cocktailsWith[cocktails_count];
	int size = cocktails_with(
		products_count, products_ids,
		cocktails_count, cocktails,
		cocktailsWith
	);
	fprintf(stderr, "sneaky_bartender: %d\n", size);

	const struct recipe* best = most_profitable(size, cocktailsWith);
	return best != NULL ? best->name : NULL;
}

/* ... */
int concat(int n, const char *strs[], char *result){
	unsigned int length = 0;

	for(unsigned int i = 0; i < (unsigned int) n; i++){
		sprintf(&result[length], strs[i]);
		length += strlen(strs[i]);
	}

	result[length] = '\0';
	return length;
}

/* ... */
void name_recipe(const struct recipe *cocktail, char name[]){
	static unsigned int length;
	static char* suffix;

	unsigned int count = 0;
	char* strs[cocktail->ingredient_count];

	for(unsigned int i = 0; i < (unsigned int) cocktail->ingredient_count; i++){
		length = strlen(stock[cocktail->ingredients[i].product_id].name);

		suffix = i + 1 < (unsigned int) cocktail->ingredient_count ?
			"-" : "";

		strs[count++] = malloc(length + 2);
		snprintf(strs[count - 1], length + 2, "%s%s",
			stock[cocktail->ingredients[i].product_id].name, suffix
		);
	}

	concat(count, (const char**) strs, name);

	for(unsigned int i = 0; i < count; i++)
		free(strs[i]);
}

/* ... */
int program(int argc, const char *argv[]){
	static char* star;

	unsigned int count = 0;
	int products_ids[argc];
	for(unsigned int i = 1; i < (unsigned int) argc; i++)
		products_ids[count++] = product_by_name(argv[i])->id;

	struct recipe proposals[MENU_LENGTH];
	const int proposalsCount = cocktails_with(
		count, products_ids, MENU_LENGTH, menu, proposals
	);

	fprintf(stderr, "program: %d\n", proposalsCount);
	const char* nameBest = sneaky_bartender(
		count, products_ids, MENU_LENGTH, menu
	);
	fprintf(stderr, "program: %s\n", nameBest);

	for(unsigned int i = 0; i < (unsigned int) proposalsCount; i++){
		if(nameBest != NULL && strcmp(proposals[i].name, nameBest) == 0)
			star = "*";
		else
			star = "";

		printf("%s", star);
		print_recipe(proposals[i]);
		printf("\n");
	}

	return proposalsCount;
}
