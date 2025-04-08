from typing import Literal, TypeVar
from numpy.typing import NDArray

import math
import numpy as np

_Columns = TypeVar("_Columns", bound=np.uint)

def R(theta: float) -> np.ndarray[Literal[2], np.dtype[np.float_]]:
	"""Creates a rotation matrix of `theta` radians."""
	return np.array([
		[math.cos(theta), -math.sin(theta)],
		[math.sin(theta), math.cos(theta)]
	])

def H(k: float) -> np.ndarray[Literal[2], np.dtype[np.float_]]:
	"""Creates a homothety matrix of `k`."""
	return np.array([[k, 0], [0, k]])

def T(
	u: np.ndarray[tuple[Literal[2], Literal[1]], np.dtype[np.float_]],
	f: np.ndarray[tuple[Literal[2], _Columns], np.dtype[np.float_]]
	) -> np.ndarray[tuple[Literal[2], _Columns], np.dtype[np.float_]]:
	"""Translates the figure `f` by the vector `u`."""
	return f + u

def Pr(a: float) -> np.ndarray[Literal[2], np.dtype[np.float_]]:
	"""Creates a projection matrix on `y=ax`."""
	b = 1/(a**2 + 1)
	c = a/(a**2 + 1)
	return np.array([[b, c], [c, b*a**2]])

def S(a: float) -> np.ndarray[Literal[2], np.dtype[np.float_]]:
	"""Creates a symetry matrix on `y=ax`."""
	return 2*Pr(a) - np.identity(2)

if __name__ == "__main__":
	import matplotlib.pyplot as plt

	fig = plt.figure(label="Transformations", layout="constrained")

	# --- Rotation and Homothety ---
	axe = fig.add_subplot(131, title="Rotation and Homothety")

	SQUARE = np.array([[-1, 1, 1, -1, -1], [1, 1, -1, -1, 1]])
	axe.plot(*SQUARE, label="I(2)")
	axe.plot(*(R(math.pi/4) @ SQUARE), label="R(PI/4)")
	axe.plot(*(H(2) @ SQUARE), label="H(2)")

	axe.axis("scaled")
	axe.legend(loc="upper right")

	# --- Translations ---
	axe = fig.add_subplot(132, title="Translation")

	points = np.linspace(0,2*math.pi, 60)
	CIRCLE = np.array([np.cos(points),np.sin(points)])
	axe.plot(*CIRCLE, label="I(2)")
	axe.plot(*T(np.array([[1], [3]]), CIRCLE), label="T((1;2))")

	axe.axis("scaled")
	axe.legend(loc="upper right")

	# ---- Projection and Symetry ----
	axe = fig.add_subplot(133, title="Projection and Symetry")

	SHAPE = np.array([[-1, 0, 1, 1, -1, -1], [1, .5, 0.75, -.25, -1, 1]])
	axe.plot(*SHAPE, label="I(2)")

	projMatrix = Pr(-1/2)
	axe.plot(*(projMatrix @ SHAPE), label="Pr(-1/2)", linestyle="", marker=".")
	assert np.allclose(np.linalg.matrix_power(projMatrix, 2), projMatrix), (
		"\033[31mFAILED\033[0m"
	)

	symMatrix = S(-1/2)
	axe.plot(*(symMatrix @ SHAPE), label="S(-1/2)")
	assert np.allclose(
		np.linalg.matrix_power(symMatrix, 2), np.identity(2)
	), "\033[31mFAILED\033[0m"

	axe.axis("scaled")
	axe.legend(loc="upper right")

	# ---
	plt.show()