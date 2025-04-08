if __name__ == "__main__":
	import numpy as np
	import numpy.linalg as la
	import numpy.random as npr

	SIZE = 3

	randMat = npr.rand(SIZE, SIZE)
	symMat = randMat.T@randMat

	eigenvalues, eigenvectors = la.eig(symMat)
	for i, (val, vec) in enumerate(zip(eigenvalues, eigenvectors.T)):
		print(
			f"N°{i + 1} is valid {symMat@vec} = {vec*val}?",
			"\033[32m" if np.allclose(symMat@vec, vec*val) else "\033[31mNOT",
			"EQUAL\033[0m"
		)

	pasMat = eigenvectors.T
	diagMat = np.diag(eigenvalues)
	nbSymMat = pasMat@symMat@la.inv(pasMat)
	print(
		"Is symetric matrix in new basis of eigenvectors is equal to diagonal",
		"matrix of eigenvalues?",
		"\033[32m" if np.allclose(diagMat, nbSymMat) else "\033[31mNOT",
		"EQUAL\033[0m"
	)