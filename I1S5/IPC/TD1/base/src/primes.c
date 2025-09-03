/**
 * @file primes.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <stdbool.h>
#include <stdlib.h>
#include <stdio.h>

static bool isPrime(const unsigned int n){
	unsigned int m = 2;
	while(m < n && n%m != 0) m += 1;

	if(m >= n && n != 0 && n != 1) return true;
	else return false;
}

int main(void){
	unsigned int n = 100;

	unsigned int i = 0;
	while(i <= n){
		if(i != 0 && i != 1 && isPrime(i)) printf("%d ", i);
		i = i + 1;
	}

	printf("\n");
	return 0;
}