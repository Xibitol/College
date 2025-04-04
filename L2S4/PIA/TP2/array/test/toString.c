/**
 * @file toString.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include <assert.h>

#include "array.h"

static const char* uitc(const void* element){
	static char string[sizeof "4294967295" + 1];
	const unsigned int* integer = element;

	snprintf(string, sizeof "4294967295" + 1, "%u", *integer);
	return string;
}
static const char* linesToString(const void* element){
	static char string[__UINT8_MAX__ + 2 + 1];
	const unsigned char* size = element;

	string[0] = '\'';

	for(unsigned char i = 0; i < *size; i++) string[i + 1] = '-';

	string[*size + 1] = '\'';
	string[*size + 2] = '\0';

	return string;
}

int test_toString(void){
	unsigned int length;

	array_init();
	{
		length = 10;

		unsigned int* a = array_create_full(sizeof(unsigned int), length,
			array_default_ratio
		);
		for(unsigned int i = 0; i < length; i++) a[i] = i*i;

		assert(strcmp(
			array_to_string(a, &uitc),
			"[0, 1, 4, 9, 16, 25, 36, 49, 64, 81]"
		) == 0);

		array_destroy(a);
	}
	{
		length = 20;

		unsigned int* a = array_create_full(sizeof(unsigned int), length,
			array_default_ratio
		);
		for(unsigned int i = 0; i < length; i++) a[i] = i*i*i;

		assert(strcmp(
			array_to_string(a, &uitc),
			"[0, 1, 8, 27, 64, 125, 216, 343, 512, 729, 1000, 1331, 1728, 2197, 2744, 3375, 4096, 4913, 5832, 6859]"
		) == 0);

		array_destroy(a);
	}
	{
		length = 15;

		unsigned char* a = array_create_full(sizeof(unsigned char), length,
			array_default_ratio
		);
		for(unsigned int i = 0; i < length; i++) a[i] = i;

		assert(strcmp(
			array_to_string(a, &linesToString),
			"['', '-', '--', '---', '----', '-----', '------', '-------', '--------', '---------', '----------', '-----------', ...]"
		) == 0);

		array_destroy(a);
	}
	array_finish();

	return EXIT_SUCCESS;
}