#include <stdio.h>

unsigned int arithmetic_gcd(unsigned int a, unsigned int b){
	/*while(a != b && a != 0 && b != 0){
		if(a > b)
			a = a%b;
		else
			b = b%a;
	}

	return a == 0 ? b : a;*/

	unsigned int c;

	if(a == b) return b;

	while(a){
		c = a;
		a = b%a;
		b = c;
	}

	return b;
}