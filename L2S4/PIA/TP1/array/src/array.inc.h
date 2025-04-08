/**
 * @file array.inc.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#pragma once

struct _Array{
	unsigned int size;
	unsigned int length;
	unsigned int max;
	float ratio;
};
typedef struct _Array Array;