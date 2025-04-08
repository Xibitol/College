L = [1, 2, 3, 4, 5]

print(L[0], L[-1])

L[1] = 0
L[3] = L[2] + L[4]

print(L)

oldL = L
aux = L[0]
L[0] = L[1]
L[1] = aux

print(L)

L[0], L[1] = L[1], L[0]

try:
	print(L, end=" : ")
	assert L == oldL, "Something is wrong above that line !"
	print("Good.")
except AssertionError as e:
	print(e.args[0])

L.remove(0)