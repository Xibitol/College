#!/bin/bash

gnuplot -p -e "
	set xlabel 'Size';
	set ylabel 'Comp count';
	set title 'Comparison count of functions';

	plot \
		'out/ex8.dat' using 1:2 with lines title 'contains',
		'out/ex8.dat' using 1:3 with lines title 'bubbleSort',
		'out/ex8.dat' using 1:4 with lines title 'sortedContains'
"