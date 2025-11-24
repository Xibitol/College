n = None
while n is None:
	try:
		n = int(input("Please give a positive integer sir: "))

		if n <= 0:
			raise ValueError("Number must be a positive integer;")
	except Exception as e:
		print("Come on! ({})".format(e))
		n = None

print(n)
while n != 1:
	n = n/2 if n%2 == 0 else 3*n + 1
	print(int(n))