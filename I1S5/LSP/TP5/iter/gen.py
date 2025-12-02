from typing import Callable, Iterable, Tuple
from io import TextIOWrapper

import itertools as it
import functools as ft

def lines(f: TextIOWrapper) -> Iterable[str]:
	for l in f:
		yield l.rstrip("\n")

def iter_fibonacci(n: int) -> Iterable[int]:
	numbers = []

	for i in range(0, n):
		if i <= 1:
			numbers.append(i)
		else:
			numbers.append(numbers[i - 2] + numbers[i - 1])

		yield numbers[i]

def iter_IP() -> Iterable[str]:
	for x1 in range(255):
		for x2 in range(255):
			for x3 in range(255):
				for x4 in range(255):
					yield "{}.{}.{}.{}".format(x1, x2, x3, x4)

class interval:
	first = 0
	last = 0

	n = 0

	def __init__(self, first, last):
		self.first = first
		self.last = last

	def __iter__(self):
		self.n = 0
		return self
	def __next__(self):
		if self.n > self.last - self.first:
			raise StopIteration()
		else:
			self.n += 1
			return self.first + self.n - 1

def iter_sequence[T, U](s1: Iterable[T], s2: Iterable[U]) -> Iterable[T | U]:
	for e in s1:
		yield e
	for e in s2:
		yield e

def iter_cartesian[T](s1: Iterable[T], s2: Iterable[T]) -> Iterable[Tuple[T]]:
	for x in s1:
		for y in s2:
			yield (x, y)

def myenumerate[T](i: Iterable[T]) -> int | None:
	j = 0
	for e in i:
		yield (j, e)
		j += 1
def find[T](x: T, s: Iterable[T]) -> int | None:
	for i, y in myenumerate(s):
		if y == x:
			return i
	return None

def mymap[T, U](func: Callable[[T], U], i: Iterable[T]) -> Iterable[U]:
	for e in i:
		yield func(e)
def to_int(s: Iterable[str]) -> Iterable[int]:
	return mymap(int, s)

def myreduce[T, U](func: Callable[[U, T], U], i: Iterable[T], initial: U) -> U:
	acc: U = initial
	for e in i:
		acc = func(acc, e)
	return acc

def myfilter[T](func: Callable[[T], bool], i: Iterable[T]) -> Iterable[T]:
	for e in i:
		if func(e):
			yield e
def included[T](i1: Iterable[T], i2: Iterable[T]) -> Iterable[T]:
	return myfilter(lambda e: e in s2, s1)

def histogramme(s: str) -> dict[int, int]:
	def filt(d: dict[int, int], x: int) -> dict[int, int]:
		if x in d: d[x] += 1
		else: d[x] = 1
		return d

	return myreduce(filt, map(lambda x: len(x), s.split(" ")), {})

if __name__ == '__main__':
	secret = '#[_'
	with open('resource/db.txt') as f:
		for line in lines(f):
			if line == secret:
				print('Trouvé')
				break

	print("iter_fibonacci:", end=" ")
	for n in iter_fibonacci(10):
		print(n, end=" ")
	print()

	print("iter_IP:", end=" ")
	for ip in it.islice(iter_IP(), 20, 30
		# 84*2**24 + 234*2**16 + 17*2**8,
		# 84*2**24 + 234*2**16 + 18*2**8
	):
		print(ip, end = " ")
	print()

	print("interval:", end=" ")
	for n in interval(10, 20):
		print(n, end = " ")
	print()

	print("iter_sequence:", end=" ")
	for n in iter_sequence(
		[3, 6, 9],
		["Nous irons aux bois", "Cueillir des saucisses", "Dans mon panier neuf"]
	):
		print(n, end = " ")
	print()

	print("iter_cartesian:", end=" ")
	for n in iter_cartesian([1, 2, 3], [4, 5, 6]):
		print(n, end = " ")
	print()

	print("find:", find(5, [4, 5, 6]))
	print("to_int:", list(to_int(["4", "-5", "23456765432"])))
	print("ft.reduce:", ft.reduce(lambda acc, e: e*acc, [4, 5, 6], 1))
	print("myreduce:", myreduce(lambda acc, e: e*acc, [4, 5, 6], 1))
	print("myfilter:", list(myfilter(lambda e: e%5 != 0, [4, 5, 6])))

	hist = histogramme(
		"Nous irons aux bois Cueillir des saucisses Dans mon panier neuf"
		+ "Mathématiquement, il est probablement sûr d utiliser des fonctions "
		+ "informatiques."
	)
	print("histogram:", hist)
	def groupby(
		t: tuple[int, int, int, int], d: dict[int, int]
	) -> tuple[int, int, int, int]:
		t[min(d[0]//5, 3)] += d[1]
		return t
	print("histogram reduced:", myreduce(groupby, hist.items(), [0, 0, 0, 0]))