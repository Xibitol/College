#!/bin/bash

gnuplot -p -e "
	set xlabel 'Size';
	set ylabel 'Table accesses count';
	set title 'Table accesses of function';

	plot 'out/ex7.dat' with lines title 'checkPrimes'
"