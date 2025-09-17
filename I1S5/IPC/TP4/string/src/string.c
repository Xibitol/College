/**
 * @file string.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include "stdbool.h"
#include "string.h"
#include "stdlib.h"
#include "stdio.h"
#include "math.h"

#define EX1_ARG_COUNT 2
#define EX2_ARG_COUNT 4
#define EX3_ARG_COUNT 2
#define EX4_ARG_COUNT 2
#define TITLE_PRINT_BUFFER 1024

#define eprintf(...) fprintf(stderr, __VA_ARGS__)
#define printArgcError(number) \
	eprintf("Not enough arguments to execute exercise %d;\n", number)

static void printExerciseTitle(const unsigned int number,
	const unsigned int argc, const char* const* argv
);

/** Exercise 1.1 */
static unsigned int getLength(const char* string);
/** Exercise 1.2 */
static int lengthcompare(const char* s1, const char* s2);
/** Exercise 1.3 */
static unsigned int countVowels(const char* string);

/** Exercise 2.1 */
static bool isPalindrome(const char* string);
/** Exercise 2.2 */
static bool isAlpha(const char c);
/** Exercise 2.3 */
static char toLower(char c);
/** Exercise 2.4 */
static bool isPalindromeEnhanced(const char* string);
/** Exercise 2.4 */
static bool isNumeric(const char c);

/** Exercise 3.1 */
static char* chaine_miroir(char* string);
/** Exercise 3.2 */
static char* chaine_miroir_to(const char* string,
	char* out, const unsigned int length
);

/** Exercise 4.1 */
static char* itoa(const unsigned int number,
	char* out, const unsigned int size
);
/** Exercise 4.2 */
static char* itoaEnhanced(const int number, char* out, const unsigned int size);

int main(const int argc, const char* const* argv){
	unsigned int minArgCount = 1;
	const char* const* exArgv;

	// Exercise 1
	if(argc < (int)(minArgCount + EX1_ARG_COUNT)){
		printArgcError(1);
		return EXIT_FAILURE;
	}else{
		exArgv = &argv[minArgCount];
		printExerciseTitle(1, EX1_ARG_COUNT, exArgv);

		printf("Length: %lu and %lu\n", strlen(exArgv[0]), strlen(exArgv[1]));
		printf("Length: %u and %u\n",
			getLength(exArgv[1]), getLength(exArgv[1])
		);
		printf("Comparison: %d\n", lengthcompare(exArgv[0], exArgv[1]));
		printf("Vowels: %u and %u\n",
			countVowels(exArgv[1]), countVowels(exArgv[1])
		);
	}
	minArgCount += EX1_ARG_COUNT;

	if(argc < (int)(minArgCount + EX2_ARG_COUNT)){
		printArgcError(2);
		return EXIT_FAILURE;
	}else{
		exArgv = &argv[minArgCount];
		printExerciseTitle(2, EX2_ARG_COUNT, exArgv);

		printf("Palindrome: %s\n", isPalindrome(exArgv[0]) ? "true" : "false");
		printf("Alpha: %s\n", isAlpha(exArgv[1][0]) ? "true" : "false");
		printf("Lowered: %c\n", toLower(exArgv[2][0]));
		printf("Palindrome Enhanced: %s\n",
			isPalindromeEnhanced(exArgv[3]) ? "true" : "false"
		);
	}
	minArgCount += EX2_ARG_COUNT;

	if(argc < (int)(minArgCount + EX3_ARG_COUNT)){
		printArgcError(2);
		return EXIT_FAILURE;
	}else{
		exArgv = &argv[minArgCount];
		printExerciseTitle(3, EX3_ARG_COUNT, exArgv);

		char out[1024];
		printf("Miroir: %s\n", chaine_miroir((char*) exArgv[0]));
		printf("Safe miroir: %s\n",
			chaine_miroir_to(exArgv[1], out, sizeof out - 1)
		);
	}
	minArgCount += EX3_ARG_COUNT;

	if(argc < (int)(minArgCount + EX4_ARG_COUNT)){
		printArgcError(2);
		return EXIT_FAILURE;
	}else{
		exArgv = &argv[minArgCount];
		printExerciseTitle(4, EX4_ARG_COUNT, exArgv);

		char out[32];
		printf("Unsigned integer: %s\n",
			itoa(atoi(exArgv[0]), out, sizeof out - 1)
		);
		printf("Integer: %s\n",
			itoaEnhanced(atoi(exArgv[1]), out, sizeof out)
		);
	}
	minArgCount += EX4_ARG_COUNT;
}

void printExerciseTitle(const unsigned int number,
	const unsigned int argc, const char* const* argv
){
	char* title = malloc(TITLE_PRINT_BUFFER);
	unsigned int i = 0;

	i += snprintf(&title[i], TITLE_PRINT_BUFFER - i, "EX %d ---", number);
	for(unsigned int j = 0; j < argc; j++)
		i += snprintf(&title[i], TITLE_PRINT_BUFFER - i, " %s", argv[j]);

	i += snprintf(&title[i], TITLE_PRINT_BUFFER - i, "\n");
	printf("%d\n", i);
	printf(title);
}

// Exercise 1
unsigned int getLength(const char* string){
	unsigned int length = 0;

	while(string[length] != '\0') length++;

	return length;
}
int lengthcompare(const char* s1, const char* s2){
	unsigned int i = 0;
	while(s1[i] != '\0' && s2[i] != '\0') i++;

	int length = 0;
	if(s1[i] == '\0') length++;
	if(s2[i] == '\0') length--;
	return length;
}
unsigned int countVowels(const char* string){
	static const char vowels[12] = {
		'A', 'a', 'E', 'e', 'I', 'i', 'O', 'o', 'U', 'u', 'Y', 'y'
	};
	unsigned int count = 0, j;

	for(unsigned int i = 0; string[i] != '\0'; i++){
		j = 0;
		while(j < sizeof vowels && string[i] != vowels[j]) j++;

		if(j < sizeof vowels) count++;
	}

	return count;
}

// Exercise 2
bool isPalindrome(const char* string){
	const size_t length = strlen(string);

	unsigned int a = 0, b = length - 1;
	while(a < b && string[a++] == string[b--]);

	return a >= b;
}
bool isAlpha(const char c){
	return (c >= 0x41 && c <= 0x5A) || (c >= 0x61 && c <= 0x7A);
}
bool isNumeric(const char c){
	return c >= 0x31 && c <= 0x39;
}
char toLower(char c){
	return isAlpha(c) && c < 0x61 ? c + 0x20 : c;
}
bool isPalindromeEnhanced(const char* string){
	const size_t length = strlen(string);

	unsigned int a = 0, b = length - 1;
	while(a < b){
		while(!isAlpha(string[a]) && !isNumeric(string[a])) a++;
		while(!isAlpha(string[b]) && !isNumeric(string[b])) b--;

		if(toLower(string[a++]) != toLower(string[b--]))
			break;
	}

	return a >= b;
}

// Exercise 3
char* chaine_miroir(char* string){
	const size_t length = strlen(string);
	char tmp;

	for(unsigned int i = 0, ri; i < (ri = length - 1 - i); i++){
		tmp = string[i];
		string[i] = string[ri];
		string[ri] = tmp;
	}

	return string;
}
char* chaine_miroir_to(const char* string,
	char* out, const unsigned int size
){
	const size_t length = strlen(string);

	for(unsigned int i = 0; i < length && i < size; i++)
		out[i] = string[length - 1 - i];

	out[length < size ? length : size - 1] = string[length];
	return out;
}

// Exercise 4
char* itoa(const unsigned int number, char* out, const unsigned int size){
	static char digits[] = {
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
	};
	const unsigned int length = ((int) log10f(number)) + 1;
	unsigned int tmpNum = number;

	for(unsigned i = 0; i < length && i < size; i++){
		out[length - 1 - i] = digits[tmpNum%10];
		tmpNum /= 10;
	}

	out[length < size ? length : size - 1] = '\0';
	return out;
}
char* itoaEnhanced(const int number, char* out, const unsigned int size){
	static char digits[] = {
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
	};
	int tmpNum = abs(number);
	unsigned int length = ((unsigned int) log10f(tmpNum)) + 1 + (number < 0);

	for(unsigned int i = 0; i < length && i < size; i++){
		out[length - 1 - i] = digits[tmpNum%10];
		tmpNum /= 10;
	}

	if(number < 0) out[0] = '-';
	out[length < size ? length : size - 1] = '\0';

	return out;
}