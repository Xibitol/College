from collections.abc import Callable
from typing import Any, overload

import numpy as np

class TestCase():
	_label: str
	_args: tuple
	_result: Any

	@overload
	def __init__(self, label: str, args: tuple, result: Callable): ...
	@overload
	def __init__(self, label: str, args: tuple, result: Any): ...
	def __init__(self, label, args, result) -> None:
		self._label = label
		self._args = args
		self._result = result(*args) if callable(result) else result

class TestFunc():
	_func: Callable
	_cases: list[TestCase]

	def __init__(self,
		func: Callable,
		cases: list[TestCase]
	) -> None:
		self._func = func
		self._cases = cases

	def exec(self) -> None:
		print(f"Testing {self._func.__name__}(...) function :")

		for case in self._cases:
			print(f"\t{case._label} -> ", end="")

			r = self._func(*case._args)
			if (
				np.array_equal(r, case._result)
	   			if type(r) is np.ndarray else r == case._result
			):
				print("\033[0;32mPASSED\033[0m")
			else:
				print("\033[0;31mFAILED\033[0m",
		  			f"(Returned {r} but expected {case._result})"
				)