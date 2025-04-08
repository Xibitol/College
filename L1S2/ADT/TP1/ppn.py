__version__ = "1.0.0"

from typing import Any, Callable, TypeVar
import random, time
import matplotlib.pyplot as plt
from tqdm import tqdm

def diss(a: int, b: int) -> int:
	return abs(a - b)

T = TypeVar('T')
def getNearest(
		tab: list[T],
		ref: T = 0,
		func: Callable[[T, T], int] = diss,
	) -> tuple[int, int]:
	if len(tab) == 0: return None
	nearest = (0, func(tab[0], ref))

	for i in range(1, len(tab)):
		f = func(tab[i], ref)
		if nearest[1] > f:
			nearest = (i, f)
	
	return nearest

if __name__ == "__main__":
	MIN, MAX = -50, 50
	N = [n for n in range(0, 10**5, 100)]

	# Testing diss()
	assert diss(5, 18) == abs(5 - 18), "diss() failed a test."
	assert diss(2, 2) == 0, "diss() failed a test."
	assert diss(-1, -2) == abs(-1 + 2), "diss() failed a test."

	# Speed test of getNearest()
	tps = []
	for n in tqdm(N):
		table = [random.randint(MIN, MAX) for i in range(n)]

		st = time.perf_counter()
		getNearest(table)
		et = time.perf_counter()

		tps.append(et - st)

	plt.plot(N, tps)
	plt.plot([0, N[-1]], [0, max(tps)])
	plt.show()