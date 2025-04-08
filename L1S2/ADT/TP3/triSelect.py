from typing import Callable

def selectionSort[T](tab: list[T]) -> list[T]:
	for i in range(len(tab) - 1):
		imin = i
		for j in range(i + 1, len(tab)):
			if tab[imin] > tab[j]:
				imin = j
		tab[i], tab[imin] = tab[imin], tab[i]
	return tab

def selectionSortMax[T](tab: list[T]) -> list[T]:
	for i in range(len(tab) - 1, 0, -1):
		imax = i
		for j in range(0, i):
			if tab[imax] < tab[j]:
				imax = j
		tab[i], tab[imax] = tab[imax], tab[i]
	return tab

def selectionSortRev[T](tab: list[T]) -> list[T]:
	for i in range(len(tab) - 1):
		imax = i
		for j in range(i + 1, len(tab)):
			if tab[imax] <= tab[j]: # Keeping identical elements order based on
				imax = j			# the list ordering mode
		tab[i], tab[imax] = tab[imax], tab[i]
	return tab

def funcSelectionSort[T](
		tab: list[T],
		func: Callable[[int, int], bool] = lambda a, b: a > b
	) -> list[T]:
	for i in range(len(tab) - 1):
		imin = i
		for j in range(i + 1, len(tab)):
			if func(tab[imin], tab[j]):
				imin = j
		tab[i], tab[imin] = tab[imin], tab[i]
	return tab

if __name__ == "__main__":
	import random
	import time

	MAX, MIN = 100, -100

	def previewTab[T](tab: list[T], size: int = 10):
		return f"[{", ".join([str(el) for el in tab[:size]] + ["..."])}]"

	def test_funcSelectionSort[T](tab: list[T]) -> list[T]:
		return funcSelectionSort(tab)

	tab = [random.randint(MIN, MAX) for i in range(10**3)]
	print(f"Table initiale (tab) : {previewTab(tab)}")
	for func in [
		selectionSort,
		selectionSortMax,
		selectionSortRev,
		test_funcSelectionSort
	]:
		st = time.perf_counter()
		r = func(tab.copy())
		t = round((time.perf_counter() - st)*10**3, 2)
		print(f"  | {func.__name__}(tab) -> {previewTab(r)} in {t}s")