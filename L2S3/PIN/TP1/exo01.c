#include <stdio.h>

int main(void){
	printf(
		"Hello world: I'm here now! Let's go for when i was compiled %s.\n",
		__DATE__
	);

	return 0;
}