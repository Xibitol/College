import random, typing
import nombres_premiers as np

def testPrimaliteFermat(N: int) -> bool:
	a = random.randint(0, N - 1)
	return a**(N - 1)%N == 1

def boostedFermatPrimeTesting(N: int, k: int = 1) -> bool:
	return all([a**(N - 1)%N == 1 for a in (random.randint(0, N - 1) for i in range(k))])

if __name__ == "__main__":
	START, END = 2, 10000

	numbers = list(range(START, END + 1))
	P = np.primesWithReinjection(END + 1, False)

	for k in [1, 2, 5, 20]:
		print(f"\nTesting Fermat algorithm with k = {k}:")

		missed = []
		for num in range(10):
			missed.append(0)

			for i in range(len(numbers)):
				prime = boostedFermatPrimeTesting(numbers[i], k)
				if prime != (numbers[i] in P):
					missed[num] += 1
				
				print(
					f"\t({num + 1}) Calculating with it ...",
		  			numbers[i],
					f"({round((i + 1)/len(numbers)*100, 2)}% completed).", end="\r"
				)
			print("")
		print(
			f"The algorithm has missed around to",
			round(sum(missed)/len(missed)),
			f"numbers, between {START} and {END}."
		)

		# This new algorithm upgraded test's precision because it tests more than one random number. But, by testing
		# more and more random numbers (incrementation of k), the algorithm become less and less precise (That
		# multiplies chances to have one or more falsy tests).

# Results :
###########################
# Determining all primes between 2 and 10000:
#         Calculating ... 10000
# 1229 primes numbers found.

# Testing algorithm with k = 1:
#         (0) Calculating with it ... 10000 (100.0% completed).
#         (1) Calculating with it ... 10000 (100.0% completed).
#         (2) Calculating with it ... 10000 (100.0% completed).
#         (3) Calculating with it ... 10000 (100.0% completed).
#         (4) Calculating with it ... 10000 (100.0% completed).
#         (5) Calculating with it ... 10000 (100.0% completed).
#         (6) Calculating with it ... 10000 (100.0% completed).
#         (7) Calculating with it ... 10000 (100.0% completed).
#         (8) Calculating with it ... 10000 (100.0% completed).
#         (9) Calculating with it ... 10000 (100.0% completed).
# The algorithm has missed around to 54 numbers, between 2 and 10000.

# Testing algorithm with k = 2:
#         (0) Calculating with it ... 10000 (100.0% completed).
#         (1) Calculating with it ... 10000 (100.0% completed).
#         (2) Calculating with it ... 10000 (100.0% completed).
#         (3) Calculating with it ... 10000 (100.0% completed).
#         (4) Calculating with it ... 10000 (100.0% completed).
#         (5) Calculating with it ... 10000 (100.0% completed).
#         (6) Calculating with it ... 10000 (100.0% completed).
#         (7) Calculating with it ... 10000 (100.0% completed).
#         (8) Calculating with it ... 10000 (100.0% completed).
#         (9) Calculating with it ... 10000 (100.0% completed).
# The algorithm has missed around to 14 numbers, between 2 and 10000.

# Testing algorithm with k = 5:
#         (0) Calculating with it ... 10000 (100.0% completed).
#         (1) Calculating with it ... 10000 (100.0% completed).
#         (2) Calculating with it ... 10000 (100.0% completed).
#         (3) Calculating with it ... 10000 (100.0% completed).
#         (4) Calculating with it ... 10000 (100.0% completed).
#         (5) Calculating with it ... 10000 (100.0% completed).
#         (6) Calculating with it ... 10000 (100.0% completed).
#         (7) Calculating with it ... 10000 (100.0% completed).
#         (8) Calculating with it ... 10000 (100.0% completed).
#         (9) Calculating with it ... 10000 (100.0% completed).
# The algorithm has missed around to 12 numbers, between 2 and 10000.

# Testing algorithm with k = 20:
#         (0) Calculating with it ... 10000 (100.0% completed).
#         (1) Calculating with it ... 10000 (100.0% completed).
#         (2) Calculating with it ... 10000 (100.0% completed).
#         (3) Calculating with it ... 10000 (100.0% completed).
#         (4) Calculating with it ... 10000 (100.0% completed).
#         (5) Calculating with it ... 10000 (100.0% completed).
#         (6) Calculating with it ... 10000 (100.0% completed).
#         (7) Calculating with it ... 10000 (100.0% completed).
#         (8) Calculating with it ... 10000 (100.0% completed).
#         (9) Calculating with it ... 10000 (100.0% completed).
# The algorithm has missed around to 28 numbers, between 2 and 10000.