/**
 * @file pointer.c
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
static void swap(int* a, int* b);
/** Exercise 1.2 */
static void order(int* a, int* b);

/** Exercise 2 */
static void min_max(const int t[], const unsigned int size, int* min, int* max);

/** Exercise 3.1 */
static char* stpcpy2(char* dst, const char* src);
/** Exercise 3.2 */
static size_t strlen2(const char* s);
/** Exercise 3.3 */
static int strcmp2(const char* s1, const char* s2);
/** Exercise 3.4 */
static char* strcat2(char* s1, const char* s2);

int main(void){
	// Exercise 1
	{
		printExerciseTitle(1);

		int a = 1, b = 31;

		swap(&a, &b);
		assert(a == 31 && b == 1);
		order(&a, &b);
		assert(a == 1 && b == 31);
	}

	// Exercise 2
	{
		printExerciseTitle(2);

		int tab[] = {31,1,20,-6,24};
		int min = -1, max = -1;

		min_max(tab, 0, &min, &max);
		assert(min == -1 && max == -1);
		min_max(tab, sizeof tab/sizeof(int), NULL, NULL);
		min_max(tab, sizeof tab/sizeof(int), &min, &max);
		assert(min == -6 && max == 31);
		min_max(&tab[2], 2, &min, &max);
		assert(min == -6 && max == 20);
	}

	// Exercise 3
	{
		printExerciseTitle(3);

		char dst[32];
		char* result;

		result = stpcpy2(dst, "Je suis moi.");
		assert(result == &dst[12] && strcmp("Je suis moi.", dst) == 0);

		assert(strlen2("") == 0);
		assert(strlen2(dst) == 12);

		assert(strcmp2("", "") == 0);
		assert(strcmp2("bidule", "loupette") < 0);
		assert(strcmp2("loupette", "bidule") > 0);
		assert(strcmp2(dst, dst) == 0);

		result = strcat2(dst, "Non, c'est pas vrai.");
		assert(result == dst && strlen2(dst) == 12 + 20);
	}

	return EXIT_SUCCESS;
}

void printExerciseTitle(const unsigned int number){
	printf("EX %d ---\n", number);
}

// Exercise 1
void swap(int* a, int* b){
	int t = *a;
	*a = *b;
	*b = t;
}
void order(int* a, int* b){
	if(a > b) swap(a, b);
}

// Exercise 2
void min_max(const int t[], const unsigned int size, int* min, int* max){
	if(size <= 0) return;
	int localMin = t[0];
	int localMax = t[0];

	for(unsigned int i = 1; i < size; i++){
		if(localMin > t[i]) localMin = t[i];
		else if(localMax < t[i]) localMax = t[i];
	}

	if(min != NULL) *min = localMin;
	if(max != NULL) *max = localMax;
}

// Exercise 3
char* stpcpy2(char* dst, const char* src){
	size_t i = 0;

	do
		dst[i] = src[i];
	while(src[i++] != '\0');

	return dst + i - 1;
}
size_t strlen2(const char* s){
	size_t length = 0;

	while(s[length] != '\0') length++;

	return length;
}
int strcmp2(const char* s1, const char* s2){
	int cmp = 0;

	for(unsigned int i = 0, j = 0; s1[i] != '\0' || s2[j] != '\0';
		(s1[i] == '\0' ? i : i++), (s2[j] == '\0' ? j : j++)
	)
		cmp = s1[i] - s2[j];

	return cmp;
}
char* strcat2(char* s1, const char* s2){
	size_t i = 0, j = 0;

	while(s1[i] != '\0') i++;
	do
		s1[i++] = s2[j];
	while(s2[j++] != '\0');

	return s1;
}