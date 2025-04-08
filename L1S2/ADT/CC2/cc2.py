import collections.abc as cabc, typing as t
from bisect import bisect_left

V = t.TypeVar("V")

def occrec(T: cabc.Sequence, element: V | None = None, left: int = 0) -> int:
	return (
		(1 if left < len(T) and T[left] == element else 0)
		+ (occrec(T, left=left + 1) if left - 1 < len(T) else 0)
	)

def getNearestDicho(T: cabc.Sequence, ref: V) -> V | None:
	if len(T) <= 0: return None
	iPlace = bisect_left(T, ref)

	if iPlace == len(T): return T[iPlace - 1]
	elif T[iPlace] == ref or iPlace == 0: return T[iPlace]
	elif abs(T[iPlace] - ref) > abs(T[iPlace - 1] - ref): return T[iPlace - 1]
	else: return T[iPlace]

if __name__ == "__main__":
	print(occrec([1, 0, 3, 6, 0, 2], 0, 0))
	
	for n in range(0, 50):
		print(f"{n} -> {getNearestDicho([5, 10, 20, 40], n)};", end=" ")