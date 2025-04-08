import matplotlib.pyplot as plt
import numpy as np
def f(x,y):
	return x**2 + y**2
x = np.arange(-5,5,0.05) # abscisses à représenter
y = np.arange(-5,5,0.05) # abscisses à représenter
X, Y = np.meshgrid(x, y) # préparation du maillage
Z = f(X,Y) # calcul des images
# représentation
fig, ax = plt.subplots()
ax.contour(X, Y, Z, levels = [-1, 0, 1, 4, 9, 16])
plt.show()