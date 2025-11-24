import sys

try:
	n = int(sys.argv[1])

	if n < 1:
		raise ValueError()
except:
	print("Invalid value sir")
	exit(1)

for i in range(1, n + 1, 2):
	print(' '*((n - i)//2), '*'*i)