import collections.abc as cabc, typing as t
from bisect import bisect_left

V = t.TypeVar("V")

def plus_proche_dicho(T: cabc.Sequence, ref: V) -> int | None:
	if len(T) <= 0: return None
	g, d = 0, len(T)
	iNearest = bisect_left(T[g:d], ref)
	nearestDiff = (T[iNearest] - ref)//2

	while len(T[g:d]) > 0:
		iMiddle = bisect_left(T[g:d], ref)
		diff = (T[iMiddle] - ref)//2

		if T[iMiddle] == ref: return T[iMiddle]
		elif diff < nearestDiff:
			iNearest, nearestDiff = iMiddle, diff
			d = iMiddle - 1
		else:
			g = iMiddle + 1

	return iNearest