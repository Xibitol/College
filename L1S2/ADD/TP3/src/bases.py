from typing import Literal, TypeAlias
from numpy import ndarray, dtype, float_

Vector2f: TypeAlias = ndarray[tuple[Literal[2], Literal[1]], dtype[float_]]
Matrix2f: TypeAlias = ndarray[Literal[2], dtype[float_]]

# ---- #
import numpy.linalg as la

def passage(v1: Vector2f, v2: Vector2f) -> Matrix2f:
	return np.concatenate((v1, v2), axis=1)

if __name__ == "__main__":
	import math
	import matplotlib.pyplot as plt
	from trans import S

	vectors = (
		# u
		np.array([[1], [0]]),
		np.array([[0], [1]]),
		# v
		np.array([[1], [1]]),
		np.array([[1], [-1]])
	)

	# -- Figures --
	fig, plots = plt.subplots(2, 2)

	# Basis
	VECTORS_PER_GROUP = 2
	RADIUS = 2
	PRESISION = 30

	figures = (
		# Rectangle
		np.array([
			[-1,  1,  1, -1],
			[-4, -4,  1,  1]
		]),
		# Circle
		np.array([
			[origin + RADIUS*func(t)
				for t in np.linspace(0, math.pi*2, PRESISION)
			] for origin, func in zip([0, 2], [math.cos, math.sin])
		]),
		# Rose
		np.array([[
			origin + 3*math.cos(5*t)*func(t)
				for t in np.linspace(0, math.pi*2, PRESISION*3)
			] for origin, func in zip([2, 2], [math.cos, math.sin])
		])
	)

	invP = la.inv(passage(vectors[2], vectors[3]))

	for axis, name, func in (
		(plots[0, 0], "Old basis", lambda m: m),
		(plots[0, 1], "New basis", lambda m: invP@m)
	):
		for offset, symbol in enumerate(("u", "v")):
			for i in range(VECTORS_PER_GROUP):
				v = func(vectors[offset*VECTORS_PER_GROUP + i])
				x, y = v[0, 0], v[1, 0]

				axis.plot((0, x), (0, y), 'o-')
				axis.text(x + .05, y + .1, f"{symbol}{i}")

		for figMatrix, color in zip(figures, ["green", "brown", "pink"]):
			axis.fill(*func(figMatrix), color=color)

		axis.set_title(name)

	# Linear maps from old basis
	MATRIX = np.array([
		[0.6, -0.8],
		[-0.8, -0.6]
	])

	eqVecs = (
		np.array([[1], [-0.5]]),
		np.array([[-1], [-2]])
	)

	nbp = passage(*eqVecs) # New basis p
	invNbp = la.inv(nbp)

	for axis, name, func, matrix in (
		(plots[1, 0], "Old basis (Linear map)",
   			lambda m: m, MATRIX
		),
		(plots[1, 1], "New basis (Linear map)",
   			lambda m: invNbp@m, invNbp@MATRIX@nbp
		)
	):
		for i, (symbol, color) in enumerate((
			("u", "pink"),
			("w", "purple"),
			("a", "blue"),
			("b", "orange"),
			("v1", "black"),
			("v2", "black")
		)):
			v = vectors[i] if i < len(vectors) else eqVecs[i - len(vectors)]
			iv = func(v)
			tv = matrix@func(v)

			axis.plot((0, iv[0, 0]), (0, iv[1, 0]), "o-", color=color)
			axis.text(iv[0, 0] + .05, iv[1, 0] + .1, symbol)

			if(i < len(vectors)):
				axis.plot((0, tv[0, 0]), (0, tv[1, 0]), "o-", color=color)
				axis.text(tv[0, 0] + .05, tv[1, 0] + .1, f"{symbol}'")
		
		axis.set_title(name)
	
	# Linear maps from new basis (y = -x)
	direcVec = np.array([[1], [-1]])
	orthVec = np.array([[1], [1]])
	MATRIX = np.array([
		[1, 0],
		[0, -1]
	])

	nbp = passage(direcVec, orthVec)
	nbMatrix = nbp@MATRIX@la.inv(nbp)
	print(
		"A linear map matrix from adapted basis to initial one is:\n",
		S(-1) == nbMatrix
	)

	# -- Draw --
	for axis in [plot for row in plots for plot in row]:
		axis.axis("scaled")
		axis.set(xlim=(-4, 4), ylim=(-4, 4))

		axis.spines['left'].set_position('zero')
		axis.spines['right'].set_color('none')
		axis.spines['top'].set_color('none')
		axis.spines['bottom'].set_position('zero')

	if(figManager := fig.canvas.manager):
		figManager.set_window_title("Change of basis")
		figManager.full_screen_toggle()
	plt.show()