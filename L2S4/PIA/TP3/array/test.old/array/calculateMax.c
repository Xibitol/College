/**
 * @file calculateMax.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdbool.h>
#include <math.h>
#include <time.h>
#include <stdlib.h>
#include <assert.h>

#include "array.h"

int test_array_calculateMax(void){
	const unsigned int a = 15;
	const unsigned int b = 15;
	float ratio;
	int max;

	srand(time(NULL));

	array_init();
	{
		ratio = (float) rand()/RAND_MAX + 1;
		max = _array_calculate_max(a, ratio);
		assert((unsigned int) ceilf((max/ratio + max)/2) == a);

		ratio = (float) rand()/RAND_MAX + 1;
		max = _array_calculate_max(b, ratio);
		assert((unsigned int) ceilf((max/ratio + max)/2) == b);
	}
	array_finish();

	return EXIT_SUCCESS;
}