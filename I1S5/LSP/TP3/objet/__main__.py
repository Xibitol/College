import objet.test as test
from objet import *

tests = [
	"Exercise 1",
]

r = Rational(124, 326)
print("Stringification:", r)
print("Evaluation:", eval(repr(r), locals={"Rational": Rational}))
print("Simplification:", r.simplify(), "modified in place", r)
print("Cast in float:", float(r))

print("Addition:", r + 15)
print("Substraction:", r - 15)
print("Multiplication:", r*2)
print("True Division:", r/2)
print("Floor Division:", r//2)

l = [Rational(4, 5), Rational(2, 3), Rational(3, 3), Rational(7, 16)]
print(l, "was sorted to", sorted(l))
print([float(r) for r in l], "was sorted to", [float(r) for r in sorted(l)])