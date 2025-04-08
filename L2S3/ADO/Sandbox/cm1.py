import numpy as np
import matplotlib.pyplot as plt

x = np.arange(-3, 4, 1)
y = np.arange(-3, 4, 1)

X, Y = np.meshgrid(x, y)

print(X, Y, sep="\n")

def f(x,y):
	return x**2 + y**2

Z = f(X, Y)

print(Z)

fig, ax = plt.subplots()
ax.contour(Z, levels = [0.1, 2, 2.5])
plt.show()