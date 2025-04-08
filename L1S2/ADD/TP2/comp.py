if __name__ == "__main__":
	import math
	import numpy as np
	import trans as tr
	import matplotlib.pyplot as plt

	fig = plt.figure(label="Compositions", layout="constrained")

	# ---- Shapes ----
	axe = fig.add_subplot(131, title="Base")

	SQUARE = np.array([[1, 1, -1, -1, 1], [-1, 1, 1, -1, -1]])
	axe.plot(SQUARE[0], SQUARE[1], label="I(2)")

	RECTANGULAR_TRIANGLE = np.array([[1,0,0,1],[0,1,0,0]])
	axe.plot(*RECTANGULAR_TRIANGLE)

	TRIANGLE = np.array([
		[1,         -1/2,          -1/2, 1],
		[0, np.sqrt(3)/2, -np.sqrt(3)/2, 0]
	])
	axe.plot(*TRIANGLE)

	PARALLELEPIPED = np.array([[0,2,3,1,0],[0,0,1,1,0]])
	axe.plot(*PARALLELEPIPED)

	n = 60
	t = np.linspace(0,2*math.pi, n)
	CIRCLE = np.array([np.cos(t),np.sin(t)])
	axe.plot(*CIRCLE)

	axe.axis("scaled")

	# ---- House ----
	axe = fig.add_subplot(132, title="The house")

	axe.plot(*tr.T(CIRCLE, np.array([[-1.5], [1]])))
	axe.plot(*tr.T(CIRCLE, np.array([[1.5], [1]])))
	axe.plot(
		*tr.T(tr.R(math.pi/2) @ tr.H(3.45) @ TRIANGLE, np.array([[0], [4.725]]))
	)
	axe.plot(*(tr.H(3) @ SQUARE))
	axe.plot(*tr.T(SQUARE, np.array([[0], [-2]])))

	axe.axis("scaled")

	# ---- Tangram ----
	axe = fig.add_subplot(133, title="The tangram")

	axe.fill(
		*tr.T(tr.R(math.pi/4) @ tr.H(0.25) @ SQUARE, np.array([[0.35], [0]]))
	)
	axe.fill(*tr.T( # From rectangular triangle positions
		tr.H(0.35) @ PARALLELEPIPED, np.array([[-0.70710678], [-0.70710678]])
	))
	axe.fill(*tr.T(
		tr.R(-math.pi/4) @ tr.H(0.5) @ RECTANGULAR_TRIANGLE,
		np.array([[0.70710678/2], [0.70710678/2]])
	))
	axe.fill(*tr.T(
		tr.R(math.pi/2) @ tr.H(0.70710678) @ RECTANGULAR_TRIANGLE,
		np.array([[0.70710678], [-0.70710678]])
	))
	axe.fill(*(tr.R(math.pi*3/4) @ RECTANGULAR_TRIANGLE))
	axe.fill(*(tr.R(math.pi/4) @ RECTANGULAR_TRIANGLE))
	axe.fill(*(tr.R(math.pi*5/4) @ tr.H(0.5) @ RECTANGULAR_TRIANGLE))
	
	axe.axis("scaled")

	# ---
	plt.show()