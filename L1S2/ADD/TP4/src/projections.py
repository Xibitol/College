from typing import Literal, TypeAlias, TypeVar
from numpy import ndarray, dtype, uint, float_

Dim = TypeVar("Dim", bound=uint)

Vectorf: TypeAlias = ndarray[tuple[Dim, Literal[1]], dtype[float_]]
Vector2f: TypeAlias = ndarray[tuple[Literal[2], Literal[1]], dtype[float_]]
Matrixf: TypeAlias = ndarray[Dim, dtype[float_]]

# --- #
import numpy as np
import numpy.linalg as la

def estPS(A: Matrixf) -> bool:
	return np.array_equal(A, A.T) and (
		all([ev > 0 for ev in la.eigvals(A)])
	)

def ps(A: Matrixf, u: Vectorf, v: Vectorf) -> float_:
	# A must define a dot product.
	return (u.T @ A @ v)[0, 0]

def unit(v: Vectorf) -> Vectorf:
	return v/np.sqrt(ps(np.identity(v.shape[0]), v, v))

def proj(u: Vectorf, v: Vectorf) -> Vectorf:
	return ps(np.identity(v.shape[0]), u, v)*u/ps(np.identity(v.shape[0]), u, u)

def proj2(u: Vectorf, v: Vectorf) -> Vectorf:
	n = np.array([[u[1, 0]], [-u[0, 0]]])
	newBasisP = np.concatenate((u, n), axis=1)
	projM = np.array([[1, 0], [0, 0]])

	return newBasisP @ projM @ la.inv(newBasisP) @ v

def GS(v: Matrixf) -> Matrixf:
	iMat = np.identity(v.shape[0])
	u = v[:,[0]]

	for k in range(1, v.shape[1]):
		uk = np.array(v[:,[k]], dtype=float_)

		for j in range(0, k):
			uj = u[:,[j]]
			uk -= ps(iMat, uj, v[:,[k]])*uj/ps(iMat, uj, uj)

		u = np.concatenate((u, uk), axis=1)

	for col in range(u.shape[1]):
		u[:,[col]] /= np.sqrt(np.sum(u[:,[col]]**2))

	return u

if __name__ == "__main__":
	import matplotlib.pyplot as plt

	from normes import norme2

	LENGTH = 4
	MATRIX_PS = np.array([[2, 1, 0], [1, 2, 1], [0, 1, 1]])
	VECTORS = [
		np.array([[1], [2]]), np.array([[-3], [1]]), np.array([[2], [1]]),
		np.array([[2], [-2]]), np.array([[3], [3]])
	]
	BASIS_3F = np.concatenate((
		[[1], [2], [3]],
		[[1], [1], [1]],
		[[-1], [2], [4]]
	), axis=1)
	ON_BASIS_3F = np.concatenate((
		np.array([[1.], [2.], [3.]])/np.sqrt(14),
		np.array([[4], [1], [-2]])/np.sqrt(21),
		np.array([[-1], [2], [-1]])/np.sqrt(6)
	), axis=1)

	print(f"estPS({MATRIX_PS}) -> {estPS(MATRIX_PS)}")

	r = ps(MATRIX_PS, np.array([[2], [4], [5]]), np.array([[2], [4], [5]]))
	print(f"ps({r}) -> {2**2 + (2 + 4)**2 + (4 + 5)**2}")

	for v in np.array([[3], [6]]), np.array([[3], [2]]):
		print(f"unit({v}) ->\n{unit(v)} of length {norme2(unit(v))}")

	print(f"GS({BASIS_3F}) ->\n{GS(BASIS_3F)} =\n{ON_BASIS_3F}")

	# -- Figures --
	fig, plots = plt.subplots(1, 2)

	for axis, name, func in (
		(plots[0], "With scalar products", proj),
		(plots[1], "With adapted basis", proj2)
	):
		projDest = np.array([[1], [1]])
		axis.plot(
			[projDest[0,0]*-LENGTH, projDest[0,0]*LENGTH],
			[projDest[1,0]*-LENGTH, projDest[1,0]*LENGTH]
		)

		for v in VECTORS:
			vProj = func(projDest, v)
			axis.plot((v[0,0], vProj[0, 0]), (v[1,0], vProj[1, 0]))

		axis.set_title(name)
		axis.axis("scaled")

	if(figManager := plt.get_current_fig_manager()):
		figManager.set_window_title("Projections")
	plt.show()