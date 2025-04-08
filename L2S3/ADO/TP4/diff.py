"""
En tant que data-analyste, vous êtes missionées d'expliquer à vos supérieur
hiérarchique votre façon de travailler. Pour ce faire, sur la base d'un petit
jeu de données que vous avez soignesement généré, vous leur fournissez un
exemple très simple de code Python permettant d'illustrer la différence entre
"apprentissage supervisé" et apprentissage non-supervisé".
--------------------------------------------------------------------------------
Excellente idée ! Voici un exemple simple de code Python pour illustrer la
différence entre l'apprentissage supervisé et l'apprentissage non-supervisé en
utilisant le jeu de données des fruits que nous avons mentionné précédemment.

### Jeu de Données
```python
import pandas as pd

data = {
    'Fruit': ['Pomme', 'Banane', 'Orange', 'Cerise'],
    'Couleur': ['Rouge', 'Jaune', 'Orange', 'Rouge'],
    'Taille': ['Petite', 'Moyenne', 'Moyenne', 'Petite'],
    'Poids': [150, 120, 180, 10]
}

df = pd.DataFrame(data)
print(df)
```

### Apprentissage Supervisé
Pour l'apprentissage supervisé, nous allons ajouter une colonne 'Mûr' et
entraîner un modèle simple de classification.

```python
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
from sklearn.preprocessing import LabelEncoder

# Ajouter une colonne 'Mûr'
df['Mûr'] = ['Oui', 'Oui', 'Non', 'Oui']

# Encodage des variables catégorielles
le = LabelEncoder()
df['Couleur'] = le.fit_transform(df['Couleur'])
df['Taille'] = le.fit_transform(df['Taille'])

# Séparation des caractéristiques et de la cible
X = df[['Couleur', 'Taille', 'Poids']]
y = df['Mûr']

# Séparation des données en ensembles d'entraînement et de test
X_train, X_test, y_train, y_test = train_test_split(
	X, y, test_size=0.25, random_state=42
)

# Entraînement du modèle
model = RandomForestClassifier()
model.fit(X_train, y_train)

# Prédiction
y_pred = model.predict(X_test)
print("Prédictions :", y_pred)
```

### Apprentissage Non-Supervisé
Pour l'apprentissage non-supervisé, nous allons utiliser le clustering pour
regrouper les fruits en fonction de leurs caractéristiques.

```python
from sklearn.cluster import KMeans

# Entraînement du modèle de clustering
kmeans = KMeans(n_clusters=3)
clusters = kmeans.fit_predict(X)

# Ajouter les clusters au DataFrame
df['Cluster'] = clusters
print(df)
```

### Résumé
- **Apprentissage Supervisé** : Nous utilisons un modèle de classification
supervisé pour prédire si un fruit est mûr en fonction de ses caractéristiques.
- **Apprentissage Non-Supervisé** : Nous utilisons un modèle de clustering
non-supervisé pour regrouper les fruits en fonction de leurs caractéristiques
similaires.

J'espère que ce code vous aidera à expliquer ces concepts à vos supérieurs
hiérarchiques !
"""

import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
from sklearn.preprocessing import LabelEncoder
from sklearn.cluster import KMeans

data = {
    'Fruit': ['Pomme', 'Banane', 'Orange', 'Cerise'],
    'Couleur': ['Rouge', 'Jaune', 'Orange', 'Rouge'],
    'Taille': ['Petite', 'Moyenne', 'Moyenne', 'Petite'],
    'Poids': [150, 120, 180, 10]
}

df = pd.DataFrame(data)
print(df)

# CLASSIFICATION
# Ajouter une colonne 'Mûr'
df['Mûr'] = ['Oui', 'Oui', 'Non', 'Oui']

# Encodage des variables catégorielles
le = LabelEncoder()
df['Couleur'] = le.fit_transform(df['Couleur'])
df['Taille'] = le.fit_transform(df['Taille'])

# Séparation des caractéristiques et de la cible
X = df[['Couleur', 'Taille', 'Poids']]
y = df['Mûr']

# Séparation des données en ensembles d'entraînement et de test
X_train, X_test, y_train, y_test = train_test_split(
	X, y, test_size=0.25, random_state=42
)

# Entraînement du modèle
model = RandomForestClassifier()
model.fit(X_train, y_train)

# Prédiction
y_pred = model.predict(X_test)
print("Prédictions :", y_pred)

# CLUSTERING
# Entraînement du modèle de clustering
kmeans = KMeans(n_clusters=3)
clusters = kmeans.fit_predict(X)

# Ajouter les clusters au DataFrame
df['Cluster'] = clusters
print(df)
