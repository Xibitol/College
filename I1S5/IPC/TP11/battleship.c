#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <stdbool.h>
#include <assert.h>

#define MIN(a, b) (a < b ? a : b)

/* ... */
struct position {
    int x; // ...
    int y; // ...
};

/* ... */
struct dimension {
    int width;
    int height;
};

enum orientation { HORIZONTAL, VERTICAL };

/* ... */
enum ship_kind {
    DESTROYER, SUBMARINE, CRUISER, BATTLESHIP, CARRIER,
    LAST_SHIP
};

/* ... */
static const char* const ship_labels[] = {
    "Destroyer", "Submarine", "Cruiser", "Battleship", "Carrier"
};


/* ... */
struct rules {
    const int ships_count[LAST_SHIP];
    const int ships_size[LAST_SHIP];
};

// ...
// ...
#define LONGEST_SHIP    5

/* ... */
struct ship {
    // ...
    enum ship_kind kind;

    // ...
    struct position position;
    // ...
    enum orientation orientation;

    // ...
    // ...
    // ...
    int state[LONGEST_SHIP];
};

/* ... */
static struct rules basic_rules = {
    { 2, 2, 1, 1, 1 },
    { 2, 1, 3, 4, 5 },
};

/* ... */
extern void print_grid(struct rules rules,
        int fleet_size, const struct ship fleet[], struct dimension board);

/* ... */

/* ... */

/* ... */
int sum(int n, const int array[]){
	int sum = 0;

	for(unsigned int i = 0; i < (unsigned int) n; i++) sum += array[i];

	return sum;
}

/* ... */
int inside(struct position pos, struct position origin, struct dimension dim){
	return pos.x >= origin.x && pos.x < origin.x + dim.width
		&& pos.y >= origin.y && pos.y < origin.y + dim.height;
}

/* ... */
void constrain(struct position *pos, struct dimension dim){
	if(!inside(*pos, (struct position){0, 0}, dim)){
		pos->x = MIN(pos->x, dim.width - 1);
		pos->y = MIN(pos->y, dim.height - 1);
	}
}

/* ... */
int offset(struct position pos, struct position origin){
	return pos.y == origin.y ? pos.x - origin.x : pos.y - origin.y;
}

/* ... */
int ship_size(struct rules rules, struct ship ship){
	return rules.ships_size[ship.kind];
}

/* ... */
struct dimension ship_dimension(struct rules rules, struct ship ship){
	int size = ship_size(rules, ship);
	return (struct dimension) {
		ship.orientation == HORIZONTAL ? size : 1,
		ship.orientation == VERTICAL ? size : 1
	};
}

/* ... */
int remaining_life(struct rules rules, struct ship ship){
	int size = ship_size(rules, ship);
	return size - sum(size, ship.state);
}

/* ... */
void print_position(struct position pos){
	assert(pos.x >= 0 && pos.x < 10);
	assert(pos.y <= 'Z' - 'A');

	printf("%c%d", 'A' + pos.y, pos.x);
}

/* ... */
void print_ship(struct rules rules, struct ship ship){
	printf("%s (%d/%d) ",
		ship_labels[ship.kind],
		remaining_life(rules, ship),
		ship_size(rules, ship)
	);

	print_position(ship.position);
	printf("-");
	print_position((struct position) {
		ship.position.x + ship_dimension(rules, ship).width - 1,
		ship.position.y + ship_dimension(rules, ship).height - 1
	});

	printf("\n");
}

/* ... */
struct position parse_position(const char *str){
	static char tmp;
	unsigned int i;

	char lastDigit = '0';
	i = 0;
	while(str[i] != '\0'){
		if(isdigit(str[i])) lastDigit = str[i];
		i++;
	}

	char lastAlpha = 'A';
	i = 0;
	while(str[i] != '\0'){
		tmp = toupper(str[i]);
		if(isalpha(tmp)) lastAlpha = tmp;
		i++;
	}

	return (struct position){lastDigit - '0', lastAlpha - 'A'};
}

/* ... */
struct ship *find_target(
	struct rules rules, struct position pos, int fleet_size, struct ship fleet[]
){
	unsigned int i = 0;
	while(i < (unsigned int) fleet_size &&
		!inside(pos, fleet[i].position, ship_dimension(rules, fleet[i]))
	) i++;

	return i < (unsigned int) fleet_size ? &fleet[i] : NULL;
}

/* ... */
int hit(
	struct rules rules, struct position pos, int fleet_size, struct ship fleet[]
){
	struct ship* target = find_target(rules, pos, fleet_size, fleet);
	return target == NULL ? -1 : target->state[offset(pos, target->position)];
}

/* ... */
int fire(
	struct rules rules, struct position pos, int fleet_size, struct ship fleet[]
){
	struct ship* target = find_target(rules, pos, fleet_size, fleet);
	if(target == NULL) return -1;

	if(!target->state[offset(pos, target->position)])
		target->state[offset(pos, target->position)] = 1;

	if(remaining_life(rules, *target) >= 1) return 0;
	else return target->kind + 1;
}

/* ... */
int ships_remaining(
	struct rules rules, int fleet_size, const struct ship fleet[], int remains[]
){
	for(unsigned int i = 0; i < (unsigned int) fleet_size; i++){
		remains[fleet[i].kind]--;
	}

	bool tooMany = false;
	bool notEnough = false;
	for(unsigned int i = 0; i < LAST_SHIP; i++){
		remains[i] += rules.ships_count[i];

		if(remains[i] < 0) tooMany = true;
		if(remains[i] > 0) notEnough = true;
	}

	return tooMany ? -1 : notEnough;
}

/* ... */
int ship_overlap(
	struct rules rules, struct ship ship,
	int fleet_size, const struct ship fleet[]
){
	static struct dimension dim;
	static struct position pos;
	static int size;

	dim = ship_dimension(rules, ship);
	int overlaping = -1;

	unsigned int i = 0, k = 0;
	while(i < (unsigned int) fleet_size && overlaping == -1){
		size = ship_size(rules, fleet[i]);

		k = 0;
		while(k < (unsigned int) size && overlaping == -1){
			pos = (struct position){
				fleet[i].position.x + (
					fleet[i].orientation == HORIZONTAL ? k : 0
				),
				fleet[i].position.y + (
					fleet[i].orientation == VERTICAL ? k : 0
				)
			};
			if(inside(pos, ship.position, dim))
				overlaping = i;

			k++;
		}

		i++;
	}

	return overlaping; 
}
