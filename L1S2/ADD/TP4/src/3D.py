import numpy as np
import matplotlib.pyplot as plt

"""
La structure de Fig est la suivante :
+ Il s'agit d'une liste [...] contenant les sommets
+ Chaque sommet est une liste [...] dont
	* Le premier élément est le numéro du sommet
	* Le deuxième élément est un numpy.array contenant les coordonnées
	* Le troisième élément est la couleur
Le numéro ne sert à rien car c'est la position dans la liste,
si ce n'est à "voir" la correspondance numéro -> point
La stucture de Arr est la suivant :
+ Il s'agit d'une liste [...] contenant les arrêtes
+ Chaque arrête est une liste [n,m] contenant les deux numéros
de sommets reliés par l'arrête.
"""

def afficher(vertices, vecs, is3D=False):
	if is3D:
		ax = plt.figure().add_subplot(projection='3d')

		# Affichage des sommets :
		for i in range(len(vertices)):
			ax.scatter(
				vertices[i][1][0], vertices[i][1][1], vertices[i][1][2],
				color=vertices[i][2]
			)

		# Affichage des arrêtes :
		for i in range(len(vecs)):
			ax.plot(
				[vertices[vecs[i][0]][1][0, 0], vertices[vecs[i][1]][1][0, 0]],
				[vertices[vecs[i][0]][1][1, 0], vertices[vecs[i][1]][1][1, 0]],
				[vertices[vecs[i][0]][1][2, 0], vertices[vecs[i][1]][1][2, 0]],
				color="black"
			)
	else:
		ax = plt.figure().add_subplot()

		# Affichage des sommets :
		for i in range(len(vertices)):
			ax.scatter(
				vertices[i][1][0], vertices[i][1][1],
				color=vertices[i][2]
			)

		# Affichage des arrêtes :
		for i in range(len(vecs)):
			ax.plot(
				[vertices[vecs[i][0]][1][0, 0], vertices[vecs[i][1]][1][0, 0]],
				[vertices[vecs[i][0]][1][1, 0], vertices[vecs[i][1]][1][1, 0]],
				color="black"
			)

	plt.axis("scaled")
	plt.show()

CUBE = [
	[0, np.array([[1], [1], [1]]), 'red'],
	[1, np.array([[1], [1], [-1]]), 'orange'],
	[2, np.array([[1], [-1], [-1]]), 'yellow'],
	[3, np.array([[1], [-1], [1]]), 'green'],
	[4, np.array([[-1], [1], [1]]), 'blue'],
	[5, np.array([[-1], [1], [-1]]), 'purple'],
	[6, np.array([[-1], [-1], [-1]]), 'brown'],
	[7, np.array([[-1], [-1], [1]]), 'pink']
]
VEC_CUBE = [
	[0, 1],
	[0, 3],
	[0, 4],
	[1, 2],
	[1, 5],
	[2, 3],
	[2, 6],
	[3, 7],
	[4, 5],
	[4, 7],
	[5, 6],
	[6, 7]
]


OCTAHEDRON = [
	[0, np.array([[0], [0], [1.5]]), 'red'],

	[1, np.array([[1], [1], [0]]), 'orange'],
	[2, np.array([[-1], [1], [0]]), 'blue'],
	[3, np.array([[-1], [-1], [0]]), 'pink'],
	[4, np.array([[1], [-1], [0]]), 'green'],

	[5, np.array([[0], [0], [-1.5]]), 'purple'],
]
VEC_OCTAHEDRON = [
	[0, 1],
	[0, 2],
	[0, 3],
	[0, 4],

	[1, 2],
	[2, 3],
	[3, 4],
	[4, 1],

	[5, 1],
	[5, 2],
	[5, 3],
	[5, 4]
]

if __name__ == "__main__":
	import numpy.linalg as la

	import projections as proj

	if(len(input("Do you want to see rendering tests ? (Yes|No) ")) > 0):
		afficher(CUBE, VEC_CUBE)
		afficher(CUBE, VEC_CUBE, True)
		afficher(OCTAHEDRON, VEC_OCTAHEDRON)
		afficher(OCTAHEDRON, VEC_OCTAHEDRON, True)

	# ---- Plan: x + y + z = 0 ----
	n = np.array([[1], [1], [1]]) # Normal vector
	# Basis
	u = np.array([[1], [-1], [0]])
	v = np.array([[-1], [-1], [2]])
	
	basis = proj.GS(np.concatenate((u, v, n), axis=1))
	projMat = basis @ np.array([
		[1, 0, 0],
		[0, 1, 0],
		[0, 0, 0]
	]) @ la.inv(basis)

	
	afficher([(n, projMat @ v, c) for n, v, c in CUBE] + CUBE, VEC_CUBE, True)