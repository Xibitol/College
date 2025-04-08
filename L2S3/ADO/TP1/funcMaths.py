def doudouble(f, a):
	return 2*f(2*a)

def puissance(n):
	return lambda x: x**n

def operateur(f, n):
	return lambda x: f(x)**n

def compose(f, iter):
	def call(x, f, iter):
		return x if iter == 0 else f(call(x, f, iter - 1))

	return lambda x: call(x, f, iter)

if __name__ == "__main__":
	# Test puissance(n)
	assert puissance(0)(2) == 1
	assert puissance(1)(2) == 2
	assert puissance(6)(2) == 64

	# Test doudouble(f, a)
	assert doudouble(puissance(0), 2) == 2
	assert doudouble(puissance(1), 2) == 8
	assert doudouble(puissance(6), 2) == 4**6*2

	# Test operateur(f, n)
	assert operateur(puissance(0), 2)(2) == 1
	assert operateur(puissance(1), 2)(2) == 4
	assert operateur(puissance(3), 0)(2) == 1

	# Test compose(f, iter)
	assert compose(puissance(0), 2)(2) == 1
	assert compose(puissance(1), 3)(2) == 2
	assert compose(puissance(2), 3)(2) == 256

	print("SUCCESSFUL TESTS.")
