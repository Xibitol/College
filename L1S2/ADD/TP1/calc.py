if __name__ == "__main__":
	from typing import Callable
	from numpy.typing import NDArray

	import time
	import numpy as np
	import matrices as mat

	def countPerf(func: Callable, args: tuple) -> float:
		st = time.perf_counter()
		func(*args)
		t = (time.perf_counter() - st)*1000

		print(f"{func.__qualname__}(A, B) ->", t, "microseconds")
		return t
	
	matT = countPerf(mat.produit,
		(np.random.random((50, 100)), np.random.random((100, 50)))
	)
	npT = countPerf(np.ndarray.__matmul__,
		(np.random.random((50, 100)), np.random.random((100, 50)))
	)

	print("Difference :", abs(matT - npT), "microseconds")