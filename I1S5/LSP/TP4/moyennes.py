import sys

import moyenne

grades = {}

with (open(sys.argv[1], "r") if len(sys.argv) > 1 else sys.stdin) as f:
	for l in f:
		(k, g) = l.strip().split(",")

		if k in grades: grades[k].append(int(g))
		else: grades[k] = [int(g)]

for (n, gs) in grades.items():
	print("{} -> {}".format(n, moyenne.avg(gs)))