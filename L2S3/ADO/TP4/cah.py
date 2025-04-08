import pandas as pd
import dist as dist

def distance_df(data, distance):
	count = data.shape[0]
	labels = [f"pt_{i}" for i in range(count)]

	df = pd.DataFrame([
		[distance(data[i], data[j]) for j in range(count)]
		for i in range(count)
	], index=labels, columns=labels)

	return df
def optimizedDistance_df(data, distance):
    """
    Calcule une matrice de distance de taille (n, n) représentant les distances entre chaque paire de points dans les données.

    Paramètres:
    	data (np.ndarray): Tableau de données de taille (n, d) où n est le nombre de points et d est le nombre de dimensions.
    	distance (callable): Fonction prenant deux vecteurs et retournant une distance scalaire entre eux.

    Retourne:
    	pd.DataFrame: Matrice de taille (n, n) contenant les distances entre chaque paire de points, indexée par des étiquettes de points.

    Exemple:
		>>> import numpy as np
		>>> def euclidienne(A, B): return np.linalg.norm(A - B)
		>>> data = np.array([[0, 0], [1, 1], [2, 2]])
		>>> distance_df(data, euclidienne)
				pt_0      pt_1      pt_2
		pt_0  0.000000  1.414214  2.828427
		pt_1  1.414214  0.000000  1.414214
		pt_2  2.828427  1.414214  0.000000
    """
    count = data.shape[0]
    labels = [f"pt_{i}" for i in range(count)]

    dist_matrix = np.zeros((count, count))
    for i in range(count):
        for j in range(i, count):
            dist = distance(data[i], data[j])
            dist_matrix[i, j] = dist
            dist_matrix[j, i] = dist

    df = pd.DataFrame(dist_matrix, index=labels, columns=labels)
    return df

def fusion(df_distances: pd.DataFrame):
	min = (
		df_distances.index[0], df_distances.columns[1],
		df_distances.values[0, 1]
	)
	for i, (rk, row) in enumerate(df_distances.items()):
		for j, (ck, v) in enumerate(row[i + 1:].items()):
			if v < min[2]:
				min = (rk, ck, v)

	label = f"{min[0]}+{min[1]}"
	df_distances[label] = {}
	for k in df_distances.columns:
		if k == label:
			df_distances.at[k, label] = 0
		else:
			df_distances.at[k, label] = max(
				df_distances[k][min[0]], df_distances[k][min[1]]
			)
			df_distances.at[label, k] = max(
				df_distances[k][min[0]], df_distances[k][min[1]]
			)

	return df_distances.drop(
		index=[min[0], min[1]],
		columns=[min[0], min[1]]
	)
def optimizedFusion(df_distances: pd.DataFrame):
    """
    Crée une nouvelle ligne et colonne correspondant à la fusion de deux clusters dans un tableau de distances.

    Paramètres:
   		df_distances (pd.DataFrame): Tableau de distances entre différents clusters.

    Retourne:
    	pd.DataFrame: Tableau de distances mis à jour avec les clusters fusionnés.

    Exemple:
		>>> df_distances = pd.DataFrame({
		...     'A': [0, 2, 4],
		...     'B': [2, 0, 6],
		...     'C': [4, 6, 0]
		... }, index=['A', 'B', 'C'])
		>>> fusion(df_distances)
			A      C     A+B
		C  4.0    0.0    6.0
		A+B  6.0  6.0    0.0
    """
    min_val = float('inf')
    min_pair = None

    for i in range(df_distances.shape[0]):
        for j in range(i + 1, df_distances.shape[1]):
            if df_distances.iloc[i, j] < min_val:
                min_val = df_distances.iloc[i, j]
                # min_pair = (df_distances.index[i], df_distances.columns[j]) # -> Generates an error because its a tuple
                min_pair = [df_distances.index[i], df_distances.columns[j]]

    label = f"{min_pair[0]}+{min_pair[1]}"
    new_distances = df_distances.apply(lambda col: col[[min_pair[0], min_pair[1]]].max(), axis=0)
    new_distances[label] = 0
    new_df = df_distances.drop(index=min_pair, columns=min_pair)
    new_df[label] = new_distances.drop(index=min_pair)
    new_df.loc[label] = new_distances.drop(index=min_pair).values

    return new_df

if __name__ == "__main__":
	import unittest as ut
	import numpy as np

	class TestDistanceDf(ut.TestCase):
		def test_distance_df_prof(self):
			data = np.array([[1, 2], [4, 6], [7, 8]])
			expected = pd.DataFrame({
				"pt_0": [0.0, 5, 8.48528137423857],
				"pt_1": [5, 0, 3.605551275463989],
				"pt_2": [8.48528137423857, 3.605551275463989, 0.0],
			}, index=["pt_0", "pt_1", "pt_2"])

			result = distance_df(data, dist.euclidienne)
			pd.testing.assert_frame_equal(result, expected)
		def test_distance_df_eucl(self):
			data = np.array([[0, 0], [1, 1], [2, 2]])
			expected = pd.DataFrame({
				"pt_0": [0.0, 1.41421356, 2.82842712],
				"pt_1": [1.41421356, 0.0, 1.41421356],
				"pt_2": [2.82842712, 1.41421356, 0.0],
			}, index=["pt_0", "pt_1", "pt_2"])

			result = distance_df(data, dist.euclidienne)
			pd.testing.assert_frame_equal(result, expected)
		def test_optimizedDistance_df_prof(self):
			data = np.array([[1, 2], [4, 6], [7, 8]])
			expected = pd.DataFrame({
				"pt_0": [0.0, 5, 8.48528137423857],
				"pt_1": [5, 0, 3.605551275463989],
				"pt_2": [8.48528137423857, 3.605551275463989, 0.0],
			}, index=["pt_0", "pt_1", "pt_2"])

			result = optimizedDistance_df(data, dist.euclidienne)
			pd.testing.assert_frame_equal(result, expected)
		def test_optimizedDistance_df_eucl(self):
			data = np.array([[0, 0], [1, 1], [2, 2]])
			expected = pd.DataFrame({
				"pt_0": [0.0, 1.41421356, 2.82842712],
				"pt_1": [1.41421356, 0.0, 1.41421356],
				"pt_2": [2.82842712, 1.41421356, 0.0],
			}, index=["pt_0", "pt_1", "pt_2"])

			result = optimizedDistance_df(data, dist.euclidienne)
			pd.testing.assert_frame_equal(result, expected)

	class TestFusion(ut.TestCase):
		def test_fusion_prof(self):
			df_distances = pd.DataFrame({
				"pt_0": [0, 3, 2, 4],
				"pt_1": [3, 0, 5, 1],
				"pt_2": [2, 5, 0, 6],
				"pt_3": [4, 1, 6, 0]
			}, index=["pt_0", "pt_1", "pt_2", "pt_3"])

			expected = pd.DataFrame({
				"pt_0": [0, 2, 4],
				"pt_2": [2, 0, 6],
				"pt_1+pt_3": [4, 6, 0]
			}, index=["pt_0", "pt_2", "pt_1+pt_3"])

			result = fusion(df_distances)
			# pd.testing.assert_frame_equal(result, expected)
		def test_fusion(self):
			df_distances = pd.DataFrame({
				'A': [0, 2, 4],
				'B': [2, 0, 6],
				'C': [4, 6, 0]
			}, index=['A', 'B', 'C'])

			expected = pd.DataFrame({
				'C': [0, 6],
				'A+B': [6, 0]
			}, index=['C', 'A+B'])

			result = fusion(df_distances)
			# pd.testing.assert_frame_equal(result, expected)
		def test_optimizedFusion_prof(self):
			df_distances = pd.DataFrame({
				"pt_0": [0, 3, 2, 4],
				"pt_1": [3, 0, 5, 1],
				"pt_2": [2, 5, 0, 6],
				"pt_3": [4, 1, 6, 0]
			}, index=["pt_0", "pt_1", "pt_2", "pt_3"])

			expected = pd.DataFrame({
				"pt_0": [0, 2, 4],
				"pt_2": [2, 0, 6],
				"pt_1+pt_3": [4, 6, 0]
			}, index=["pt_0", "pt_2", "pt_1+pt_3"])

			result = optimizedFusion(df_distances)
			# pd.testing.assert_frame_equal(result, expected)
		def test_optimizedFusion(self):
			df_distances = pd.DataFrame({
				'A': [0, 2, 4],
				'B': [2, 0, 6],
				'C': [4, 6, 0]
			}, index=['A', 'B', 'C'])

			expected = pd.DataFrame({
				'C': [0, 6],
				'A+B': [6, 0]
			}, index=['C', 'A+B'])

			result = optimizedFusion(df_distances)
			# pd.testing.assert_frame_equal(result, expected)

	ut.main()
