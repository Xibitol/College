"""
Désormais, vos supérieurs hiérarchiques souhaiterais un cas plus concrets. Pour
ce faire, vous décidez d'utiliser la méthode CAH sur le jeu de données Iris
(Issue de sklearn.datasets). Dans le cas du code Python, vous décidez d'utiliser
la fonction linkage et la fonctio dendogram du module SciPy.
--------------------------------------------------------------------------------
Très bien, pour rendre le cas plus concret, nous allons utiliser la méthode de
Classification Ascendante Hiérarchique (CAH) sur le célèbre jeu de données Iris.
Nous allons utiliser les fonctions `linkage` et `dendrogram` du module SciPy
pour effectuer cette analyse. Voici un exemple de code Python pour illustrer ce
processus :

### Jeu de Données Iris et CAH avec SciPy
```python
import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
from scipy.cluster.hierarchy import linkage, dendrogram
from sklearn.datasets import load_iris

# Charger le jeu de données Iris
iris = load_iris()
X = iris.data
y = iris.target
feature_names = iris.feature_names

# Créer un DataFrame pour une meilleure visualisation
df = pd.DataFrame(X, columns=feature_names)
df['Target'] = y

# Calculer la matrice de liaison
Z = linkage(X, method='ward')

# Tracer le dendrogramme
plt.figure(figsize=(10, 7))
plt.title("Dendrogramme pour le jeu de données Iris")
dendrogram(Z, labels=iris.target_names[y])
plt.xlabel('Échantillons')
plt.ylabel('Distance')
plt.show()
```

### Explication
1. **Chargement du jeu de données Iris** : Nous utilisons `load_iris()` de
`sklearn.datasets` pour charger les données.
2. **Création d'un DataFrame** : Pour une meilleure visualisation, nous
convertissons les données en DataFrame.
3. **Calcul de la matrice de liaison** : Nous utilisons `linkage()` de SciPy
pour effectuer le clustering hiérarchique avec la méthode de Ward.
4. **Tracé du dendrogramme** : Nous traçons le dendrogramme en utilisant
`dendrogram()` de SciPy.

### Résumé
La méthode CAH permet de regrouper les données de manière hiérarchique en
utilisant des distances entre les points de données. Le dendrogramme résultant
montre visuellement comment les données sont regroupées à différents niveaux de
similarité. Cette approche est utile pour identifier des structures et des
relations dans les données sans avoir besoin de connaissances préalables sur le
nombre de clusters.

J'espère que cela vous sera utile pour expliquer un cas plus concret à vos
supérieurs hiérarchiques !
"""

import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
from scipy.cluster.hierarchy import linkage, dendrogram
from sklearn.datasets import load_iris

# Charger le jeu de données Iris
iris = load_iris()
X = iris.data
y = iris.target
feature_names = iris.feature_names

# Créer un DataFrame pour une meilleure visualisation
df = pd.DataFrame(X, columns=feature_names)
df['Target'] = y

# Calculer la matrice de liaison
Z = linkage(X, method='ward')

# Tracer le dendrogramme
# plt.figure(figsize=(10, 7))
# plt.title("Dendrogramme pour le jeu de données Iris")
# dendrogram(Z, labels=iris.target_names[y])
# plt.xlabel('Échantillons')
# plt.ylabel('Distance')
# plt.show()

# PARTIE MANUELLE
fig = plt.figure(figsize=(14, 8))
fig.canvas.manager.set_window_title("ADO - TP4 - Iris with AI")
gridspec = fig.add_gridspec(1, 2)

# Plot dendrogram
axe = fig.add_subplot(gridspec[0, 0])
axe.set_title("Dendrogramme")
dendrogram(Z, labels=iris.target_names[y], ax=axe)
axe.set_xlabel("Échantillons")
axe.set_ylabel("Distance")

# Plot scatter
# indices = np.arange(151, 151 + 150 - 1)
# indicedZ = np.append(np.array([indices]).T, Z, axis=1)
# ZClasses = indicedZ[-6:-2]

# def searchClass(indice, clusters=indicedZ, classes=ZClasses):
# 	for i, row in enumerate(classes):
# 		if indice == row[0]:
# 			return i, row

# 	for row in clusters:
# 		if indice in row[1:3]:
# 			return searchClass(row[0], clusters, classes)

# axe = fig.add_subplot(gridspec[0, 1])
# axe.set_title("Scatter")
# scatter = axe.scatter(X.T[0], X.T[1], c=y)
# axe.add_artist(axe.legend(*scatter.legend_elements(), title="Classes"))
# axe.set_xlabel(feature_names[0])
# axe.set_ylabel(feature_names[1])

for axe in fig.axes:
	if len(axe.get_legend_handles_labels()[0]) > 0:
		axe.legend()
fig.tight_layout()
plt.show()