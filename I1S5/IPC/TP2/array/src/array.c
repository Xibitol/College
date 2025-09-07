/**
 * @file array.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdbool.h>
#include <time.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <assert.h>

/** Exercise 1.1 */
static unsigned int pgcd_rec(const unsigned int a, const unsigned int b);
/** Exercise 1.3 */
static unsigned int pgcd_iter(unsigned int a, unsigned int b);

/** Exercise 2.1 */
static void printTable(const int table[], const unsigned int size);
/** Exercise 2.2 */
static int* fillTableWithEven(int table[], const unsigned int size);

/** Exercise 3.1 */
static unsigned int sum(const int table[], const unsigned int size);
/** Exercise 3.2 */
static unsigned int scalarProduct(
	const int a[], const int b[], const unsigned int size
);
/** Exercise 3.3 */
static int* sumMembers(
	const int a[], const int b[], int c[], const unsigned int size
);

/** Exercise 4.1 */
static int isSorted(const int a[], const unsigned int size);
/** Exercise 4.2 */
static int contains(
	const int haystack[], const unsigned int size, const int needle,
	unsigned int* const compCount
);
/** Exercise 4.3 */
static int sortedContains(
	const int haystack[], const unsigned int size, const int needle,
	unsigned int* const compCount
);
/** Exercise 4.4 */
static int sortedContainsRecur(
	const int haystack[], const int needle,
	const unsigned int start, const unsigned int end
);

/** Exercise 5.1 */
static void pascal_ligne(int n, int valeurs[]);
/** Exercise 5.2 */
static void affiche_pascal(const unsigned int n);

/** Exercise 6.1 */
static unsigned int fibo_rec(const unsigned int x);
/** Exercise 6.3 */
static unsigned int fibo_iter(const unsigned int x);
/** Exercise 6.4 */
static double speedOf(
	unsigned int (*x)(const unsigned int), const unsigned int value,
	const unsigned int rep
);

/** Exercise 7.1 */
static unsigned int checkPrimes(bool table[], const unsigned int size);
/** Exercise 7.2 */
static unsigned int getTable(bool table[], const unsigned int index,
	unsigned int* const accesses
);
/** Exercise 7.2 */
#define SETTABLE(table, index, value, accesses) \
	{ *accesses = *accesses + 1; table[index] = value; }

/** Exercise 8 */
static int* bubbleSort(int table[], const unsigned int size,
	unsigned int* const compCount
);

int main(void){
	{ // Exercise 1
		printf("EX 1 ---\n");

		assert(pgcd_rec(4, 4) == 4);
		assert(pgcd_rec(12, 18) == 6);
		assert(pgcd_rec(0, 0) == 0);
		assert(pgcd_rec(52, 0) == 52);
		assert(pgcd_rec(0, 52) == 52);

		assert(pgcd_iter(4, 4) == 4);
		assert(pgcd_iter(12, 18) == 6);
		assert(pgcd_iter(0, 0) == 0);
		assert(pgcd_iter(52, 0) == 52);
		assert(pgcd_iter(0, 52) == 52);
	}

	{ // Exercise 2
		printf("EX 2 ---\n");

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
		printf("EX 3 ---\n");

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

	{ // Exercise 4
		printf("EX 4 ---\n");
		const int uns[4] = {1, 31, 20, 24}, s[4] = {1, 20, 24, 31};
		unsigned int compCount;

		assert(isSorted(uns, 4) == 0);
		assert(isSorted(uns, 2) == 1);
		assert(isSorted(uns, 0) == 1);
		assert(isSorted(s, 4) == 1);

		assert(contains(uns, 4, 31, &compCount) == 1 && compCount == 2);
		assert(contains(uns, 3, 24, &compCount) == 0 && compCount == 3);
		assert(contains(uns, 0, 31, &compCount) == 0 && compCount == 0);

		assert(sortedContains(s, 4, 31, &compCount) == 1 && compCount == 4);
		assert(sortedContains(s, 4, 24, &compCount) == 1 && compCount == 3);
		assert(sortedContains(s, 3, 31, &compCount) == 0 && compCount == 2);
		assert(sortedContains(s, 0, 1, &compCount) == 0 && compCount == 0);
		printf("sortedContains(uns, 4, 20) -> %d\n",
			sortedContains(uns, 4, 20, NULL)
		);

		assert(sortedContainsRecur(s, 31, 0, 4) == 1);
		assert(sortedContainsRecur(s, 24, 0, 4) == 1);
		assert(sortedContainsRecur(s, 31, 0, 3) == 0);
		assert(sortedContainsRecur(s, 1, 0, 0) == 0);
	}

	{ // Exercise 5
		printf("EX 5 ---\n");

		int values[4] = {1, 2, 1, 0};
		pascal_ligne(4, values);
		printTable(values, 4);
		pascal_ligne(3, values);
		printTable(values, 4);
		pascal_ligne(0, values);
		printTable(values, 4);

		affiche_pascal(0);
		affiche_pascal(5);
	}

	{ // Exercise 6
		printf("EX 6 ---\n");

		assert(fibo_rec(0) == 0);
		assert(fibo_rec(1) == 1);
		assert(fibo_rec(2) == 1);
		assert(fibo_rec(5) == 5);

		assert(fibo_iter(0) == 0);
		assert(fibo_iter(1) == 1);
		assert(fibo_iter(2) == 1);
		assert(fibo_iter(5) == 5);

		printf("For fibo_rec(1000)*10: %.6f secs\n",
			speedOf(fibo_rec, 40, 10)
		);
		printf("For fibo_iter(1000)*10: %.6f secs\n",
			speedOf(fibo_iter, 40, 10)
		);
	}

	{ // Exercise 7
		printf("EX 7 ---\n");

		bool arePrime[101];
		assert(checkPrimes(arePrime, 101) == 346);
		assert(checkPrimes(arePrime, 0) == 0);
		assert(checkPrimes(arePrime, 5) == 9);

		printf("Primes(%d): ", checkPrimes(arePrime, 101));
		unsigned int i = 0;
		while(i < 101){
			if(arePrime[i])
				printf("%d, ", i);

			i = i + 1;
		}
		printf("\n");

		FILE* f = fopen("ex7.dat", "w");
		bool ap[100000];
		unsigned int j = 0;
		while(j < 10){
			fprintf(f, "%u\t%u\n",
				(j + 1)*10000, checkPrimes(ap, (j + 1)*10000)
			);
			j = j + 1;
		}
		fclose(f);
	}

	{ // Exercise 8
		printf("EX 8 ---\n");
		srand(getpid());

		int table[10] = {31, 1, 20, 24, 31, 1, 20, 24, 6, 5};
		unsigned int compCount = 0;
		assert(bubbleSort(table, 4, &compCount) == table
			&& !isSorted(table, 10)
		);
		printTable(table, 10);
		assert(bubbleSort(table, 10, &compCount) == table
			&& isSorted(table, 10)
		);
		printTable(table, 10);

		FILE* f = fopen("ex8.dat", "w");
		unsigned int compCounts[3] = {0, 0, 0};
		int tab[50], searched;
		unsigned int i = 0, j;
		while(i < 50){
			j = 0;
			while(j < i){
				tab[j] = rand();
				j = j + 1;
			}

			searched = rand();
			contains(tab, i, searched, &(compCounts[0]));
			bubbleSort(tab, i, &(compCounts[1]));
			sortedContains(tab, i, searched, &(compCounts[2]));

			fprintf(f, "%u\t%u\t%u\t%u\n",
				i, compCounts[0], compCounts[1], compCounts[2]
			);

			i = i + 1;
		}
		fclose(f);
	}
}

// Exercise 1
unsigned int pgcd_rec(const unsigned int a, const unsigned int b){
	if(b != 0)
		return pgcd_rec(b, a%b);
	else
		return a;
}
unsigned int pgcd_iter(unsigned int a, unsigned int b){
	unsigned int c = 0;

	while(b != 0){
		c = b;
		b = a%b;
		a = c;
	}

	return a;
}

// Exercise 2
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

// Exercise 3
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


// Exercise 4
int isSorted(const int a[], const unsigned int size){
	if(size <= 0) return 1;
	int n = a[0];

	unsigned int i = 1;
	while(i < size && n <= a[i]){
		n = a[i];
		i = i + 1;
	}

	return i >= size;
}
int contains(const int haystack[], const unsigned int size, const int needle,
	unsigned int* const compCount
){
	if(compCount != NULL) *compCount = 0;
	if(size <= 0) return 0;

	unsigned int i = 0;
	while(i < size && haystack[i] != needle){
		if(compCount != NULL)
			*compCount = *compCount + 1;

		i = i + 1;
	}

	if(i < size){
		*compCount = *compCount + 1;
		return 1;
	}else
		return 0;
}
int sortedContains(
	const int haystack[], const unsigned int size, const int needle,
	unsigned int* const compCount
){
	if(compCount != NULL) *compCount = 0;
	if(size <= 0) return 0;

	unsigned int a = 1, b = size, c;
	do{
		c = (a + b)/2;

		if(compCount != NULL)
			*compCount = *compCount + 1;

		if(haystack[c - 1] > needle) b = c - 1;
		else a = c + 1;
	}while(a <= b && haystack[c - 1] != needle);

	if(haystack[c - 1] == needle){
		*compCount = *compCount + 1;
		return 1;
	}else
		return 0;
}
int sortedContainsRecur(
	const int haystack[], const int needle,
	const unsigned int start, const unsigned int end
){
	if(start == end) return 0;
	const unsigned int c = (start + end)/2;

	if(haystack[c] == needle) return 1;
	else if(haystack[c] > needle)
		return sortedContainsRecur(haystack, needle, start, c - 1);
	else
		return sortedContainsRecur(haystack, needle, c + 1, end);
}

// Exercise 5
void pascal_ligne(int n, int valeurs[]){
	unsigned int i = n - 1;
	while(i + 1 > 0){
		if(i > 0)
			valeurs[i] = valeurs[i] + valeurs[i - 1];

		i = i - 1;
	}
}
void affiche_pascal(const unsigned int n){
	int values[n];
	values[0] = 1;
	unsigned int k = 1;
	while(k < n){
		values[k] = 0;
		k = k + 1;
	}

	unsigned int i = 1, j = 0;
	while(i <= n){
		j = 0;
		while(j < i){
			printf("%u ", values[j]);
			j = j + 1;
		}
		printf("\n");

		i = i + 1;
		pascal_ligne(i, values);
	}
}

// Exercise 6
unsigned int fibo_rec(const unsigned int x){
	if(x <= 1) return x;
	return fibo_rec(x - 2) + fibo_rec(x - 1);
}
unsigned int fibo_iter(const unsigned int x){
	unsigned int values[x + 1];
	values[0] = 0;
	values[1] = 1;

	unsigned int i = 2;
	while(i <= x){
		values[i] = values[i - 2] + values[i - 1];
		i = i + 1;
	}

	return values[x];
}
double speedOf(
	unsigned int (*x)(const unsigned int), const unsigned int value,
	const unsigned int rep
){
	double t = 0;
	clock_t st = 0, et = 0;

	unsigned int i = 0;
	while(i < rep){
		st = clock();
		x(value);
		et = clock();

		t = t + ((double) (et - st))/CLOCKS_PER_SEC;
		i = i + 1;
	}

	return t/rep;
}

// Exercise 7
unsigned int checkPrimes(bool table[], const unsigned int size){
	unsigned int accesses = 0, i = 0, j;

	while(i < size){
		SETTABLE(table, i, true, &accesses);
		i = i + 1;
	}

	i = 2;
	while(i < size){
		if(getTable(table, i, &accesses)){
			j = i + 1;
			while(i > 1 && j < size){
				if(j%i == 0)
					SETTABLE(table, j, false, &accesses);

				j = j + 1;
			}
		}

		i = i + 1;
	}

	return accesses;
}
unsigned int getTable(bool table[], const unsigned int index,
	unsigned int* const accesses
){
	*accesses = *accesses + 1;
	return table[index];
}

// Exercise 8
int* bubbleSort(int table[], const unsigned int size,
	unsigned int* const compCount
){
	bool sorted = false;
	*compCount = 0;
	int temp;

	unsigned int i;
	while(!sorted){
		i = 1;
		sorted = true;

		while(i < size){
			*compCount = *compCount + 1;

			if(table[i - 1] > table[i]){
				temp = table[i];
				table[i] = table[i - 1];
				table[i - 1] = temp;

				sorted = false;
			}

			i = i + 1;
		}
	}

	return table;
}