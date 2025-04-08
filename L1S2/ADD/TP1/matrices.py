from typing import Optional
from numpy.typing import NDArray

import numpy as np
import numpy.linalg as LA

# Analysis
def estCarree(a: NDArray) -> bool:
	return a.shape[0] == a.shape[1]

def estTriangulaireSuperieure(a: NDArray) -> bool:
	return estCarree(a) and (
		all([
			a[i,j] == 0
			for i in range(1, a.shape[0]) for j in range(i)
		])
	)

def estOrthogonale(a: NDArray) -> bool:
	return np.array_equal(np.transpose(a) @ a, np.identity(a.shape[0]))

def egales(a: NDArray, b: NDArray) -> bool:
	if a.shape != b.shape: return False

	i, j = 0, 0
	while i < a.shape[0] and a[i, j] == b[i, j]:
		if j < a.shape[1] - 1: j += 1
		else:
			i += 1
			j = 0

	return i >= a.shape[0]

def sontInversesV1(a: NDArray, b: NDArray) -> bool:
	return (mp := produit(a, b)) is not None and (
		egales(mp, np.identity(a.shape[0]))
	)
def sontInversesV2(a: NDArray, b: NDArray) -> bool:
	return (mp := produit(a, b)) is not None and (
		np.allclose(mp, np.identity(a.shape[0]))
	)
def sontInversesV3(a: NDArray, b: NDArray) -> bool:
	return egales(LA.inv(a), b)
def sontInversesV4(a: NDArray, b: NDArray) -> bool:
	return np.allclose(LA.inv(a), b)

# Operations
def addition(a: NDArray, b: NDArray) -> Optional[NDArray]:
	return (
		np.array([
			[a[i,j] + b[i,j] for j in range(a.shape[1])]
			for i in range(a.shape[0])
		])
	) if a.shape == b.shape else None

def produit(a: NDArray, b: NDArray) -> Optional[NDArray]:
	return np.array([
		[
			sum([a[i,k]*b[k,j] for k in range(a.shape[1])])
			for j in range(b.shape[1])
		]
		for i in range(a.shape[0])
	]) if a.shape[1] == b.shape[0] else None

if __name__ == "__main__":
	from test import TestFunc, TestCase

	sontInverses_useProduit = [
		TestCase("Incompatible matrices (M(3,2)@M(3,2))", (
				np.array([[1, 1], [0, 1], [2, 0]]),
				np.array([[1, 1], [0, 1], [2, 0]])
		), False)
	]

	sontInversesTS = [
		TestCase("Isn't", (
			np.array([[1, 1], [2, 1]]),
			np.array([[1, 1], [2, 1]])
		), False),
		TestCase("Is", (
			np.array([[1, 1], [2, 1]]),
			LA.inv(np.array([[1, 1], [2, 1]]))
		), True)
	]

	tests = (
		# Analysis functions
		TestFunc(egales, [
			TestCase("Different shapes", (
				np.array([[1, 1, 1], [0, 1, 1], [2, 0, 1]]),
				np.array([[1, 1], [0, 1], [2, 0]])
			), False),
			TestCase("Same shape and not equal", (
				np.array([[1, 1, 1], [0, 1, 1], [2, 0, 1]]),
				np.array([[1, 1, 0], [0, 1, -1], [2, 0, 1]])
			), False),
			TestCase("Same shape and equals", (
				np.array([[1, 1, 1], [0, 1, 1], [2, 0, 1]]),
				np.array([[1, 1, 1], [0, 1, 1], [2, 0, 1]])
			), True)
		]),

		TestFunc(estCarree, [
			TestCase("Isn't", (
				np.array([[0, 0], [0, 0], [0, 0]]),
			), False),
			TestCase("Is", (
				np.array([[0, 0], [0, 0]]),
			), True)
		]),

		TestFunc(estTriangulaireSuperieure, [
			TestCase("Not a square", (
				np.array([[0, 0], [0, 0], [0, 0]]),
			), False),
			TestCase("Isn't", (
				np.array([[1, 1], [1, 1]]),
			), False),
			TestCase("Is", (
				np.array([[1, 1], [0, 1]]),
			), True)
		]),

		TestFunc(sontInversesV1, sontInverses_useProduit + sontInversesTS),
		TestFunc(sontInversesV2, sontInverses_useProduit + sontInversesTS),
		TestFunc(sontInversesV3, sontInversesTS),
		TestFunc(sontInversesV4, sontInversesTS),

		TestFunc(estOrthogonale, [
			TestCase("Isn't", (
				np.array([[0, 0], [0, 0]]),
			), False),
			TestCase("Is", (
				np.array([[1, 0], [0, 1]]),
			), True)
		]),

		# Operation functions
		TestFunc(addition, [
			TestCase("Different shapes", (
				np.array([[1, 1, 1], [0, 1, 1], [2, 0, 1]]),
				np.array([[1, 1], [0, 1], [2, 0]])
			), None),
			TestCase("Same shape", (
				np.array([[1, 1, 1], [0, 1, 1], [2, 0, 1]]),
				np.array([[1, 1, 0], [0, 1, -1], [2, 0, 1]])
			), np.ndarray.__add__)
		]),

		TestFunc(produit, [
			TestCase("Incompatible matrices (M(3,2)@M(3,2))", (
					np.array([[1, 1], [0, 1], [2, 0]]),
					np.array([[1, 1], [0, 1], [2, 0]])
			), None),
			TestCase("Valid shapes (M(3,2)@M(2,3))", (
				np.array([[1, 1], [0, 1], [2, 0]]),
				np.array([[1, 1, 0], [0, 1, -1]])
			), np.ndarray.__matmul__)
		])
	)

	for tf in tests:
		tf.exec()