import numpy as np

POLYNOMIAL_EQUATION_REPR = "3x⁵ - 5x³ + 1"
def polyEq(x):
	return 3*x**5 - 5*x**3 + 1

COSINUS_EQUATION_REPR = "cos(x) - x"
def cosEq(x):
	return np.cos(x) - x

SQUARE_EQUATION_REPR = "x²"
def sqrEq(x):
	return x**2

SOME_POLYNOMIAL_EQUATION_REPR = "-0.12x² + x - 2"
def somePolyEq(x):
	return -0.12*x**2 + x - 2
def somePolyEqDer(x):
	return -0.24*x + 1

# ------------------------------------------------------------------------------

def dicho(f, a, b, e=1e-9, callback=None):
	if f(a)*f(b) > 0: return None
	c = a

	while f(c) != 0 and abs(a - b) > e:
		if f(a)*f(c) < 0: a, b = a, c
		elif f(b)*f(c) < 0: a, b = c, b

		c = (a + b)/2
		if callback is not None: callback(c)

	return c

def nbderive(f, a, h=1e-9):
	return (f(a + h) - f(a - h))/(2*h)

def derivee(f):
	return lambda x: nbderive(f, x)

def critique(f, a, b, e=1e-9):
	return dicho(derivee(f), a, b, e)

def tangent(f, df, x):
	return lambda y: df(x)*(y - x) + f(x)

def newton(f, df, x_init, e=1e-9, callback=None):
	previous = None
	next = x_init

	while df(next) != 0 and (previous is None or abs(next - previous) > e):
		if callback is not None: callback(next)
		previous, next = next, next - f(next)/df(next)

	return next

# ------------------------------------------------------------------------------

if __name__ == "__main__":
	import scipy.optimize as opti
	import matplotlib.pyplot as plt
	import numdifftools as ndt

	fig = plt.figure(figsize=(10, 8))
	fig.canvas.manager.set_window_title("ADO - TP1")
	gridspec = fig.add_gridspec(3, 2)

	# Plot polyEq(x)
	abscissas = np.linspace(-1.5, 1.5)
	roots = opti.fsolve(polyEq, [-1.5, 0.5, 1.5])

	axe = fig.add_subplot(gridspec[0, 0])
	axe.set_title("Base")
	axe.plot(abscissas, polyEq(abscissas),
		label=f"Df: {POLYNOMIAL_EQUATION_REPR}"
	)
	axe.plot(roots, polyEq(roots), 'o',
		label=f"f(x) = 0"
	)
	axe.plot(1, opti.fsolve(polyEq, [1]), 'o',
		color="green"
	)

	# Plot dicho(polyEq(x), a, b)
	abscissas = np.linspace(0, 1)
	a, b = 0, 1

	axe = fig.add_subplot(gridspec[0, 1])
	axe.set_title("Dichotomy")
	axe.axvline(a, color="red", linewidth=0.75)
	axe.axvline(b, color="red", linewidth=0.75)
	axe.plot(abscissas, cosEq(abscissas),
		label=f"Df: {COSINUS_EQUATION_REPR}"
	)
	dicho(cosEq, a, b, e=0.05, callback=lambda c:
	   axe.axvline(c, color="green", linewidth=0.5)
	)

	# Plot derivee(f)
	abscissas = np.linspace(-1.5, 1.5)

	axe = fig.add_subplot(gridspec[1, 0])
	axe.set_title("Derivative")
	axe.plot(abscissas, sqrEq(abscissas),
		label=f"Df: {SQUARE_EQUATION_REPR}"
	)
	axe.plot(abscissas, derivee(sqrEq)(abscissas),
		label=f"Df': 2x"
	)
	axe.plot(abscissas, ndt.Derivative(sqrEq)(abscissas) + 0.05,
		label=f"Df': 2x (real) + 0.05"
	)

	# Plot critique(f, a, b)
	a, b = -1.5, 0.5
	value = critique(polyEq, a, b)

	axe = fig.add_subplot(gridspec[1, 1])
	axe.set_title("Critical")
	axe.plot(abscissas, polyEq(abscissas),
		label=f"Df: {POLYNOMIAL_EQUATION_REPR}"
	)
	axe.axvline(a, color="red", linewidth=0.75)
	axe.axvline(b, color="red", linewidth=0.75)
	axe.plot(value, polyEq(value), 'o',
		label=f"Critical point"
	)

	# Plot newton(f, df, x_init)
	abscissas = np.linspace(-1, 4)
	f = somePolyEq
	df = somePolyEqDer
	value = newton(f, df, 0)

	axe = fig.add_subplot(gridspec[2, :])

	def drawPoint(x):
		axe.plot((x, x), (0, f(x)), '--',
			linewidth=0.75
		)
		axe.plot(x, 0, 'o')
		axe.plot(abscissas, tangent(f, df, x)(abscissas),
			linewidth=0.75
		)

	axe.set_title("Newton method")
	axe.plot(abscissas, f(abscissas),
		label=f"Df: {SOME_POLYNOMIAL_EQUATION_REPR}"
	)
	newton(f, df, 0.5, callback=drawPoint)

	for axe in fig.axes:
		axe.grid()
		axe.axhline(0, color="black", linewidth=0.5)
		axe.legend()
	fig.tight_layout()
	plt.show()