"""
"""
__author__ = "Xibitol"

from typing import Iterator, Iterable

import math

INCHES_PER_FEETS = 12
CENTIMETERS_PER_INCHES = 2.54

NOTES = ['F', 'D', 'C', 'B', 'A']

def intsum(n: int) -> int:
	assert type(n) == int and n >= 0

	return (n*(n + 1))//2

def convert_to_m(f: float, i: float) -> float:
	return (f*INCHES_PER_FEETS + i)*CENTIMETERS_PER_INCHES/10**2

def aire(c: float, n: int) -> float:
	return (n*c**2)/(4*math.tan(math.pi/n))

def note(e: str) -> float:
	assert len(e) == 1

	return NOTES.index(e) if e in NOTES else -1

def cesar(m: str, d: int) -> str:
	def decalage(c: str) -> str:
		if not c.isalpha(): return c

		ref = ord('A') if c.isupper() else ord('a')
		return chr((ord(c) - ref + d)%26 + ref)

	return "".join(map(decalage, m))

def nzp(l: list[int]) -> tuple[list[int], list[int], list[int]]:
	lsorted = sorted(l)
	zeroIdx = lsorted.index(0)
	zeroLastIdx = -list(reversed(lsorted)).index(0)

	return (
		lsorted[:zeroIdx],
		lsorted[zeroIdx:zeroLastIdx],
		lsorted[zeroLastIdx:]
	)

def sous_ensembles(e: set[int]) -> list[set[int]]:
	if len(e) == 0:
		return [e]

	x = {e.pop()}
	subSets = sous_ensembles(e - x)
	return subSets + [n | x for n in subSets]

def max_occurrences(s: Iterable[int]) -> Iterator[int]:
	l = list(s)
	return map(lambda t: t[1],
		sorted([(l.count(n), n) for n in set(s)],
			key=lambda t: t[0],
			reverse=True
		)
	)