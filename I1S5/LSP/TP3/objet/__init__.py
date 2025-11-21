"""
"""
__author__ = "Xibitol"

import numpy

class Rational:

	REPR_FORMAT = "Rational({0}, {1})"
	STR_FORMAT = "{0}/{1}"

	def __init__(self, n: int = 0, d: int = 0):
		# `n = n` associe à n sa valeur mais ne déclare pas d'attribut pour
		# l'objet en initialisation.
		self.__n = n
		self.__d = d

	# Functions
	def simplify(self) -> "Rational":
		gcd = 0
		while gcd != 1:
			if gcd != 0:
				self.__n = self.__n//gcd
				self.__d = self.__d//gcd

			gcd = numpy.gcd(self.__n, self.__d)

		return self

	# Functions
	def __add__(self, other: int) -> "Rational":
		return Rational(self.__n + other*self.__d, self.__d)
	def __sub__(self, other: int) -> "Rational":
		return self.__add__(-other)
	def __mul__(self, other: int) -> "Rational":
		return Rational(self.__n*other, self.__d)
	def __truediv__(self, other: "Rational") -> "Rational":
		return Rational(self.__n, self.__d*other)
	def __floordiv__(self, other: "Rational") -> "Rational":
		return Rational(self.__n, self.__d*other).simplify()

	def __lt__(self, other: "Rational"):
		return self.__n < other.__n or self.__d > other.__d

	def __float__(self) -> float:
		return self.__n/self.__d
	def __repr__(self):
		return Rational.REPR_FORMAT.format(self.__n, self.__d)
	def __str__(self):
		return Rational.STR_FORMAT.format(self.__n, self.__d)