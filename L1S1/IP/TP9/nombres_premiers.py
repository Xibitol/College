import math, time, typing, sys

TOP = 1000

def isPrime(number: int, numbers: list[int] | None = None, silent: bool = True) -> bool:
	for d in numbers or list(range(2, int(math.sqrt(number)) + 1)):
		if number%d == 0:
			if not silent: print(f"{number} isn't prime (sorry).")
			return False
	
	if not silent: print(f"{number} is prime (Yeeee !).")
	return True

def primes(max: int = TOP) -> list[int]:
	# return [n for n in range(2, max) if isPrime(n)]
	P = []
	for n in range(2, max):
		if isPrime(n): P.append(n)
	return P

def primesWithReinjection(max: int = TOP, silent: bool = True) -> list[int]:
	print(f"Determining all primes between {2} and {max - 1}:")

	P = []
	for n in range(2, max):
		if isPrime(n, P): P.append(n)
		if not silent: print("\tCalculating ...", n, end="\r")
	
	if not silent: print(f"\n{len(P)} primes numbers found.")
	return P

def primesWithoutMultiples(max: int = TOP) -> list[int]:
	P, i = list(range(2, max)), 0
	while i < len(P):
		for j in range(len(P) - 1, i + 1, -1):
			if P[j]%P[i] == 0: P.pop(j)
		i += 1
	return sorted(P)

if __name__ == "__main__":
	isPrime(97, False)

	print(f"There is {len(primes())} primes numbers smaller than {TOP}.")

	def executiontimeMcS(func: typing.Callable[[int], list[int]]) -> float:
		s = time.perf_counter_ns()
		func()
		return (time.perf_counter_ns() - s)/1e3
	funcExeTimes = {f.__name__:executiontimeMcS(f) for f in [primes, primesWithReinjection]}
	bestFunc = min(list(funcExeTimes.items()), key=lambda v: v[1])
	print(f"The fastest function is {bestFunc[0]}")

	pwmExeTime = executiontimeMcS(primesWithoutMultiples)
	print(f"Deletion of multiples is{' not' if pwmExeTime > bestFunc[1] else ''} making the fuction faster than {bestFunc[0]} !")
	# primesWithoutMultiples is slower because of its complexity : For each prime numbers, it has to delete its multiples
	#	and there much more multiples to delete than numbers to check (Especially when these are only prime numbers).