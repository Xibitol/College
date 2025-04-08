def selectionSort(tab: list) -> list:
	for i in range(len(tab) - 1):
		imin = i
		for j in range(i + 1, len(tab)):          # Or with the min() builtin
			if tab[imin] > tab[j]:                  # function of Python
				imin = j                              #
		tab[i], tab[imin] = tab[imin], tab[i]
	return tab

def insertionSort(tab: list) -> list:
	for i in range(1, len(tab)):
		element = tab[i]
		j = i - 1
		while tab[j] > element and j >= 0:
			tab[j + 1] = tab[j]
			j -= 1
		else:
			tab[j + 1] = element
	return tab

if __name__ == "__main__":
	import random as r

	l = [r.randint(0, 100) for i in range(100)]

	print(l)
	print(selectionSort(l.copy()))
	print(insertionSort(l.copy()))