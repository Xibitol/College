if __name__ == "__main__":
	from numpy.typing import NDArray

	import numpy as np
	import matplotlib.pyplot as plt

	displacements = np.array([
		[0.9, 0.2, 0.35],
		[0.09, 0.75, 0.1],
		[0.01, 0.05, 0.55]
	])

	counts = np.array([
		[1000],
		[500],
		[2000]
	])

	years: tuple = tuple(range(21))
	finalCounts: list[NDArray] = []
	for y in years:
		finalCounts.append(np.linalg.matrix_power(displacements, y) @ counts)
	# Conjecture: City populations finish to be stable at around to 2411,
	# 931 and 157 people respectivly.

	dataMatrice: NDArray = np.concatenate(finalCounts, axis=1)
	for i, y in zip(range(counts.size), years):
		plt.plot(years, dataMatrice[i], label=chr(ord('A') + i))
	
	plt.title("Evolution de populations", loc="left")
	plt.xlabel("Years")
	plt.ylabel("Populations")
	plt.legend()
	plt.show()