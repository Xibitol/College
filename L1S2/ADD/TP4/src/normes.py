from typing import Literal, TypeAlias, TypeVar
from numpy import ndarray, dtype, uint, float_

Dim = TypeVar("Dim", bound=uint)

Vectorf: TypeAlias = ndarray[tuple[Dim, Literal[1]], dtype[float_]]
Vector2f: TypeAlias = ndarray[tuple[Literal[2], Literal[1]], dtype[float_]]

# --- #
import numpy as np

def norme1(u: Vectorf) -> float_:
	return np.sum(np.abs(u.T))

def norme2(u: Vectorf) -> float_:
	return np.sqrt(np.sum(u**2))

def normeinf(u: Vectorf) -> float_:
	return np.max(np.abs(u.T))

if __name__ == "__main__":
	from matplotlib import pyplot as plt

	N = 1000

	vectors: list[Vector2f] = [np.random.rand(2, 1)*4 - 2 for n in range(N)]

	# -- Figures --
	fig, plots = plt.subplots(1, 3)

	for axis, name, func in (
		(plots[0], "Norm 1", norme1),
		(plots[1], "Norm 2", norme2),
		(plots[2], "Norm inf", normeinf)
	):
		normalizedVectors: list[Vector2f] = []
		notNormalizedVectors: list[Vector2f] = []

		for v in vectors:
			if func(v) > 1: notNormalizedVectors.append(v)
			else: normalizedVectors.append(v)
	
		axis.scatter(
			[v[0, 0] for v in normalizedVectors],
			[v[1, 0] for v in normalizedVectors],
			color="orange"
		)
		axis.scatter(
			[v[0, 0] for v in notNormalizedVectors],
			[v[1, 0] for v in notNormalizedVectors],
			color="green"
		)

		axis.set_title(name)
		axis.axis("scaled")

	if(figManager := plt.get_current_fig_manager()):
		figManager.set_window_title("Norms")
	plt.show()