/**
 * @file prime.c
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
	unsigned int n = 11;

	if(isPrime(n)) printf("%d est premier, bravo!\n", n);
	else
		printf(
			"%d n'est pas premier, mais a bien mérité sa place sur le podium.\n",
			n
		);

	return 0;
}