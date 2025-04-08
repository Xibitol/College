import numpy as np
import matplotlib.pyplot as plt

def f(x,y):
	return x**2 - y**2

# représentation en 3D
fig, axes = plt.subplots(2, 2)

# PLOT 1
# abscisses et ordonnées à représenter
y = np.arange(-3,3,0.01)
for x in np.arange(0,5,0.5):
	axes[0,0].plot(y,f(x,y))

# PLOT 2
# abscisses et ordonnées à représenter
x = np.arange(-3,3,0.01)
for y in np.arange(0,5,0.5):
	axes[0,1].plot(x,f(x,y))

# PLOT 3
x = np.arange(-5,5,0.05) # abscisses à représenter
y = np.arange(-5,5,0.05) # abscisses à représenter
X, Y = np.meshgrid(x, y) # préparation du maillage
Z = f(X,Y) # calcul des images
axes[1,0].contour(X, Y, Z, levels = [-1, 0, 1, 4, 9, 16])

# PLOT 2
x = np.arange(-3,3,0.05)
y = np.arange(-3,3,0.05)
X, Y = np.meshgrid(x, y) # préparation du maillage
Z = f(X,Y) # calcul des images
colormap = axes[1,1].pcolormesh(X, Y, Z, cmap='YlGnBu')
fig.colorbar(colormap)

# SHOW PLOTS
plt.show()

x = np.arange(-3,3,0.05)
y = np.arange(-3,3,0.05)
X, Y = np.meshgrid(x, y) # préparation du maillage
Z = f(X,Y) # calcul des images
# représentation en 3D
fig, ax = plt.subplots(subplot_kw={"projection": "3d"})
ax.plot_surface(X, Y, Z)
plt.show()