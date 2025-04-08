#include <stdlib.h>

int main(void){
	int a, b;

	a %= b;
	b %= a;

	return EXIT_SUCCESS;
}