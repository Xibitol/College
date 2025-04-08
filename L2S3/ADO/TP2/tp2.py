import numpy as np
import numdifftools as ndt

def f(x, y):
	return 4*np.exp(-(x**2/2 + y**2/4))*np.sin(x*(y - 1/2))*np.cos(x/2 + y)

# ------------------------------------------------------------------------------
def ddx(f):
	return lambda x, y: ndt.Derivative(lambda z: f(z, y))(x)
def ddy(f):
	return lambda x, y: ndt.Derivative(lambda z: f(x, z))(y)

def grad(f):
	return lambda x, y: np.array([ddx(f)(x, y), ddy(f)(x, y)])

# ------------------------------------------------------------------------------

if __name__ == "__main__":
	import numpy.linalg as la
	import scipy.optimize as spo
	import matplotlib.pyplot as plt

	fig = plt.figure(figsize=(10, 8))
	fig.canvas.manager.set_window_title("ADO - TP2")
	gridspec = fig.add_gridspec(2, 2)

	# Plot surface of f(x, y)
	abscissas = np.linspace(-3.5, 3.5)
	ordinates = np.linspace(-3.5, 3.5)
	absMesh, ordMesh = np.meshgrid(abscissas, ordinates)
	results = f(absMesh, ordMesh)

	axe = fig.add_subplot(gridspec[0, 0], projection="3d")
	axe.set_title("Surface")
	axe.plot_surface(absMesh, ordMesh, results, label="P: f(x, y)")

	# Plot color map of f(x, y)
	axe = fig.add_subplot(gridspec[0, 1])
	axe.set_title("Gradient")
	fig.colorbar(axe.pcolormesh(abscissas, ordinates, results, cmap="plasma"))

	# Plot gradient vectors mesh of f(x, y)
	abscissas = np.arange(-3.5, 3.5, 0.2)
	ordinates = np.arange(-3.5, 3.5, 0.2)
	absMesh, ordMesh = np.meshgrid(abscissas, ordinates)

	axe.quiver(absMesh, ordMesh, *grad(f)(absMesh, ordMesh),
		label="∇f(x, y)"
	)

	# Plot gradient descent of f(x, y)
	g = grad(f)
	h = 1e-2
	u = None
	v = [0, 0]
	points = np.array([v])

	while u is None or la.norm(u - v) > 1e-4:
		u = v
		v = u - g(*u)*h

		points = np.concatenate((points, [v]), 0)

	axe.plot(points[:, 0], points[:, 1],
		color="red", label="Gradient descent of f(x, y)"
	)

	# Plot optimization of z = y - 5x
	def drawOptimization(ax,
		equation,
		systemValues, systemResults,
		xBounds=(0, 12), yBounds=(0, 12)
	):
		eqRepr = f"z = {equation[0]}x{equation[1]:+}y"

		axe = fig.add_subplot(ax)
		axe.set_title(f"Optimization of {eqRepr}")
		axe.set_xlim(xBounds)
		axe.set_ylim(yBounds)

		abscissas = np.arange(yBounds[0], yBounds[1] + 1)
		for (a, b), res in zip(systemValues, systemResults):
			ordinates = (
				lambda x: -a/b*x + res/b
			)(abscissas)

			axe.fill(
				[*abscissas, abscissas[-1], abscissas[0]],
				[*ordinates,
					(min if b > 0 else max)(
						ordinates[0 if b > 0 else -1],
						xBounds[0 if b > 0 else -1]
					),
					(min if b > 0 else max)(
						ordinates[0 if b > 0 else -1],
						yBounds[0 if b > 0 else -1]
					)
				],
				alpha=1/3,
				label = "{a}x{b:+}y ≤ {result}".format(
					a=a, b=b, result=res
				)
			)

		result = spo.linprog(equation, systemValues, systemResults,
			bounds=(xBounds, yBounds)
		)
		axe.plot(result.x[0], result.x[1], "o-",
			label="Minimisation: {equation}".format(
				equation=eqRepr.replace("x", f"*({result.x[0]:.1f})")
					.replace("y", f"*({result.x[1]:.1f})")
					.replace("z", f"{result.fun:.1f}")
			)
		)
		result = spo.linprog(-equation, systemValues, systemResults,
			bounds=(xBounds, yBounds)
		)
		axe.plot(result.x[0], result.x[1], "o-",
			label="Maximisation: {equation}".format(
				equation=eqRepr.replace("x", f"*({result.x[0]:.1f})")
					.replace("y", f"*({result.x[1]:.1f})")
					.replace("z", f"{-result.fun:.1f}")
			)
		)

	drawOptimization(gridspec[1, 0],
		np.array([-5, 1]),
		np.array([[-1, -4], [-1, 2], [3, -2]]),
		np.array([-24, 8, 10])
	)

	drawOptimization(gridspec[1, 1],
		np.array([-2, 3]),
		np.array([[-1, 2], [1, 1], [-1, -4]]),
		np.array([5, 13, -19])
	)

	for axe in fig.axes:
		if len(axe.get_legend_handles_labels()[0]) > 0:
			axe.legend()
	fig.tight_layout()
	plt.show()