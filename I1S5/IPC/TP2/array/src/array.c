/**
 * @file array.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdio.h>
#include <assert.h>

/** Exercise 2 */
static void printTable(const int table[], const unsigned int size);
/** Exercise 2 */
static int* fillTableWithEven(int table[], const unsigned int size);

/** Exercise 3 */
static unsigned int sum(const int table[], const unsigned int size);
/** Exercise 3 */
static unsigned int scalarProduct(
	const int a[], const int b[], const unsigned int size
);
/** Exercise 3 */
static int* sumMembers(
	const int a[], const int b[], int c[], const unsigned int size
);

int main(void){
	{ // Exercise 2
		printf("EX2 ---\n");

		const int tab1[1] = {10},
			tab2[4] = {31, 1, 20, 24};
		//	tab3[0] = {}; // FORBIDDEN BY ISO C
		printTable(tab1, 1);
		printTable(tab2, 4);
		printTable(tab2, 0);
		printTable(tab2, 2);

		int tab4[1], tab5[4], tab6[20];
		printTable(fillTableWithEven(tab4, 1), 1);
		printTable(fillTableWithEven(tab5, 4), 4);
		printTable(fillTableWithEven(tab6, 20), 20);
	}

	{ // Exercise 3
		printf("EX3 ---\n");

		const int tab1[1] = {10},
			tab2[4] = {31, 1, 20, 24};
		//	tab3[0] = {}; // FORBIDDEN BY ISO C

		assert(sum(tab1, 1) == 10);
		assert(sum(tab2, 4) == 76);
		assert(sum(tab2, 0) == 0);
		assert(sum(tab2, 2) == 32);

		assert(scalarProduct(tab1, tab2, 1) == 310);
		assert(scalarProduct(tab2, tab2, 4) == 1938);
		assert(scalarProduct(tab2, tab1, 0) == 0);
		assert(scalarProduct(tab2, tab2, 2) == 962);

		int tab3[1], tab4[4];
		printTable(sumMembers(tab1, tab2, tab3, 1), 1);
		printTable(sumMembers(tab2, tab2, tab4, 4), 4);
		printTable(sumMembers(tab2, tab1, tab3, 0), 0);
		printTable(sumMembers(tab2, tab2, tab4, 2), 2);
	}
}

void printTable(const int table[], const unsigned int size){
	printf("array(%d)[", size);
	unsigned int i = 0;
	while(i < size){
		printf("%d", table[i]);

		if(i + 1 != size)
			printf(", ");

		i = i + 1;
	}
	printf("]\n");
}
int* fillTableWithEven(int table[], const unsigned int size){
	unsigned int i = 0;
	while(i < size){
		table[i] = i*2;
		i = i + 1;
	}

	return table;
}

unsigned int sum(const int table[], const unsigned int size){
	int s = 0;

	unsigned int i = 0;
	while(i < size){
		s = s + table[i];
		i = i + 1;
	}

	return s;
}
unsigned int scalarProduct(
	const int a[], const int b[], const unsigned int size
){
	unsigned int p = 0;

	unsigned int i = 0;
	while(i < size){
		p = p + a[i]*b[i];
		i = i + 1;
	}

	return p;
}
int* sumMembers(const int a[], const int b[], int c[], const unsigned int size){
	unsigned int i = 0;
	while(i < size){
		c[i] = a[i] + b[i];
		i = i + 1;
	}

	return c;
}