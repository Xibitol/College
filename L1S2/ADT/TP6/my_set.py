from typing import (
	Any, SupportsIndex,
	Protocol, runtime_checkable,
	overload, TypeVar
)
from collections.abc import Iterable, Iterator, MutableSet

# TODO: An improvement can be looking at predefined methods and trying to
# rewrite then better for our system

_TContra = TypeVar("_TContra", contravariant=True)
@runtime_checkable
class SupportsRichComparison(Protocol[_TContra]):
	def __lt__(self, other: _TContra) -> bool: ...
	def __gt__(self, other: _TContra) -> bool: ...

_T = TypeVar("_T", bound=SupportsRichComparison)
class XibSet(MutableSet[_T]):
	__values: list[_T]

	@overload
	def __init__(self): ...
	@overload
	def __init__(self, iterable: Iterable[_T]): ...
	def __init__(self,
			iterable: Iterable[_T] = []
		):
		self.__values = []
		self.adds(iterable) # Fixed recursion problem

	# GETTERS
	def len(self) -> int: return len(self)
	def __len__(self) -> int:
		return len(self.__values)

	def __iter__(self) -> Iterator[_T]:
		return iter(self.__values)
	
	def __repr__(self) -> str:
		return f"{self.__class__.__name__}({self.__values})"
	
	def _findPlace(self,
			x: SupportsRichComparison[Any]
		) -> tuple[SupportsIndex, bool]:
		if len(self) <= 0: return (0, False) # Not found, may be placed at zero
		l, r = 0, len(self)

		while True: # Do While loop emulation. See if block at end...
			mid = (l + r)//2

			if self.__values[mid] > x: r = mid
			elif self.__values[mid] < x: l = mid + 1

			if r - l <= 0 or self.__values[mid] == x: break

		return (l if r - l <= 0 else mid, self.__values[mid] == x)

	def isNotIn(self, x: object) -> bool: return x not in self
	def isIn(self, x: object) -> bool: return x in self
	def __contains__(self, x: object) -> bool:
		return isinstance(x, SupportsRichComparison) and self._findPlace(x)[1]

	def issub(self, other: "XibSet[_T]") -> bool: return self.issubset(other)
	def issubset(self, other: "XibSet[_T]") -> bool:
		return self > other

	def issup(self, other: "XibSet[_T]") -> bool: return self.issuperset(other)
	def issuperset(self, other: "XibSet[_T]") -> bool:
		return self < other

	# SETTERS
	def add(self, x: _T, silent: bool = True) -> None:
		i, isThere = self._findPlace(x)

		if not isThere: self.__values.insert(i, x)
		elif not silent: raise ValueError(
			f"{x} is already in this {self.__class__.__name__}."
		)
	def adds(self, values: Iterable[_T], silent: bool = True) -> None:
		# Small optimization for big iterables and big current sets.
		refused: set[_T] = set()

		for x in values:
			if x in refused: continue

			try: self.add(x, False)
			except ValueError as e:
				if not silent: raise e
				else: refused.add(x)

	def discard(self, x: _T) -> None:
		i, isThere = self._findPlace(x)
		if isThere: self.__values.pop(i)

	def rm(self, x: _T) -> None: return self.remove(x)

	# FUNCTIONS
	def union(self, other: "XibSet[_T]") -> "XibSet[_T]":
		return XibSet(self | other)

	def inter(self, other: "XibSet[_T]") -> "XibSet[_T]":
		return self.intersection(other)
	def intersection(self, other: "XibSet[_T]") -> "XibSet[_T]":
		return XibSet(self & other)

	def diff(self, other: "XibSet[_T]") -> "XibSet[_T]":
		return self.difference(other)
	def difference(self, other: "XibSet[_T]") -> "XibSet[_T]":
		return XibSet(self - other)

	def xor(self, other: "XibSet[_T]") -> "XibSet[_T]":
		return self.symmetric_difference(other)
	def symmetric_difference(self, other: "XibSet[_T]") -> "XibSet[_T]":
		return XibSet(self ^ other)

if __name__ == "__main__":
	it8 = (2, 4, 11, 7, 2, 1, 19, 7)
	xs = XibSet[int](it8)
	print(it8, xs)

	print("Length:", xs.len())
	print("Membership:", 5, xs.isIn(5))
	print("No Membership:", 5, xs.isNotIn(5))

	xs.add(5)
	print("Adding:", xs)
	xs.remove(5)
	print("Deleting:", xs)

	it8 = (-11, 2, 54, 4, 11, 1, 19, 7)
	print("Creation:", it8, xs2 := XibSet(it8))

	it2 = (-11, 54)
	print("Union:", xs.union(xs2), "OR", it2, xs.union(XibSet(it2)))
	it2 = (1, 54)
	print("Intersection:", xs.inter(xs2), "OR", it2, xs.inter(XibSet(it2)))
	print("Difference:", xs2.diff(xs))

	print("Disjoint:", xs.isdisjoint(xs2))
	print("Subset:", xs.issub(xs2))
	print("Superset:", xs.issup(xs2))
	print("Symmetric Difference:", xs.xor(xs2))
