#include <stdlib.h>
#include <stdio.h>
#include <assert.h>

#include "../src/fraction.h"

int test_file(void){
	fraction_init();

	Fraction a = (Fraction) {1, 2};
	Fraction b = (Fraction) {3, 4};
	Fraction c = (Fraction) {0x80000000, 0xffffffff};

	// Write
	FILE *fp = fopen("test.dat", "w");
	assert(fp);

	assert(fraction_fwrite(&a, fp) == &a);
	assert(fraction_fwrite(&b, fp) == &b);
	assert(fraction_fwrite(&c, fp) == &c);
	assert(fraction_fwrite(&a, 0) == NULL);

	a = (Fraction) {0, 0};
	b = (Fraction) {0, 0};
	c = (Fraction) {0, 0};

	// Read
	fp = freopen("test.dat", "r", fp);
	
	Fraction *d = fraction_fread(&a, fp);
	Fraction *e = fraction_fread(&b, fp);
	Fraction *f = fraction_fread(&c, fp);

	assert(d->numerator == 1 && d->denominator == 2 && d == &a);
	assert(e->numerator == 3 && e->denominator == 4 && e == &b);
	assert(f->numerator == (int) 0x80000000 && f->denominator == 0xffffffff
		&& f == &c
	);
	assert(fraction_fread(&a, 0) == NULL);

	fclose(fp); // Valgrind don't see it when it missing!!!!

	fraction_finish();
	return EXIT_SUCCESS;
}