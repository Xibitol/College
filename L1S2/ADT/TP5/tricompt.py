def tri_comptage(tab: list[int]) -> list[int]:
	counts: list[int] = [0 for i in range(1000)]

	for n in tab:
		if 0 <= n < 1000: counts[n] += 1
		else: 
			raise ValueError(
				"The table must only has numbers between 0 and 1000, " +
				f"not included. There is {n} at index {tab.index(n)}."
			)

	i = 0
	for j in range(len(tab)): 
		while counts[i] <= 0:
			i += 1

		tab[j] = i
		counts[i] -= 1

	return tab

def tri_comptage_char(tab: list[str]) -> list[str]:
	tabInts = [0 for i in range(len(tab))]

	for i, c in enumerate(tab):
		if len(c) == 1 and  0 <= ord(c) < 128: tabInts[i] = ord(c)
		else:
			raise ValueError(
				"The table must only has characters that're in the ascii " +
				f"table. There is {c} ({ord(c)}) at index {i}"
			)

	return [chr(n) for n in tri_comptage(tabInts)]

if __name__ == "__main__":
	import typing
	import random, time
	import multiprocessing as mp
	import multiprocessing.connection as mpc
	import matplotlib.pyplot as plt

	from aglorithms import selectionSort, insertionSort
	from tri_rapide import tri_rapide, tri_rapide_bis

	class FuncTest():
		_result_cache: list[float] | None = None

		_algorithm: typing.Callable[[list[int]], list[int]]
		_process: mp.Process | None = None
		_pipe: tuple[mpc.Connection, mpc.Connection] | None = None

		def __init__(self, algorithm: typing.Callable[[list[int]], list[int]]):
			self._algorithm = algorithm

		def _doTests(self, connection: mpc.Connection, arrayLengths: list[int]):
			"""Works within a separate process."""
			self._result_cache = []

			for length in arrayLengths:
				#pb.set_postfix({
				#	"PID": os.getpid(),
				#	"algorithm": func.__name__,
				#	"arrayLength": length
				#})
				tab = [random.randint(0, 999) for i in range(length)]

				st = time.perf_counter()
				self._algorithm(tab)
				t = time.perf_counter() - st

				self._result_cache.append(t)

			connection.send(self._result_cache)
			connection.close()

		def countPerf(self, arrayLengths: list[int]):
			self._pipe = mp.Pipe()
			self._process = mp.Process(
				name=self._algorithm.__name__,
				target=self._doTests,
				args=(self._pipe[1], arrayLengths),
			)
			self._process.start()

		def get(self) -> list[float] | None:
			if self._process:
				self._process.join()
				self._process.close()
				self._process = None

				if self._pipe:
					self._result_cache = self._pipe[0].recv()
					self._pipe[0].close()

			return self._result_cache

	# Testing
	#print(tri_comptage([random.randint(0, 999) for i in range(100)]))
	#print(tri_comptage_char([chr(random.randint(32, 126)) for i in range(100)]))

	sizes: list[int] = [n for n in range(0, 10000, 10)]
	tests = [
		FuncTest(selectionSort),
		FuncTest(insertionSort),
		FuncTest(tri_rapide),
		FuncTest(tri_rapide_bis),
		FuncTest(tri_comptage),
		FuncTest(sorted)
	]

	for test in tests:
		test.countPerf(sizes)

	for test in tests:
		plt.plot(sizes, test.get() or [-1 for n in range(0, 10000, 10)],
		   label=f"{test._algorithm.__name__}(T)"
		)

	plt.legend()
	plt.title("Some sorting algorithms speed.")
	plt.xlabel("Random numbers list size.")
	plt.ylabel("Execution time (in seconds).")
	plt.show()