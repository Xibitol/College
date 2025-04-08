# A
n = 1
while n <= 5:
	print(n, end=" ")
	n += 1
print("")

print("-"*10)
# B
n = 0
while n <= 1:
	f = n + 2
	t = f*(6 + n)
	print(f"In [{f};{t}]")
	
	m = f
	while m <= t:
		if m%2 == 0:
			print(m, end=" ")
		m += 1
	
	n += 1
	print("")

print("-"*10)
# C
n = 130
while n >= 130 - 9*5:
	if n%5 == 0:
		print(n, end=" ")
	n -= 1
print("")