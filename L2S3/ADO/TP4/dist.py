import numpy as np

def euclidienne(A, B):
	return np.sqrt(np.sum((B - A)**2))

def optimizedEuclidienne(A, B):
    """
    Calcule la distance Euclidienne entre deux vecteurs de même taille.

    Paramètres:
		A (np.ndarray): Vecteur de taille n.
		B (np.ndarray): Vecteur de taille n.

    Retourne:
		float: La distance Euclidienne entre les deux vecteurs.

    Exemple:
		>>> A = np.array([1, 2, 3])
		>>> B = np.array([4, 5, 6])
		>>> euclidienne(A, B)
		5.196152422706632
    """
    return np.linalg.norm(B - A)

def manhattan(A, B):
	return np.sum(np.abs(B - A))

def optimizedManhattan(A, B):
    """
    Calcule la distance de Manhattan entre deux vecteurs de même taille.

    Paramètres:
   		A (np.ndarray): Vecteur de taille n.
    	B (np.ndarray): Vecteur de taille n.

    Retourne:
    	float: La distance de Manhattan entre les deux vecteurs.

    Exemple:
		>>> A = np.array([1, 2, 3])
		>>> B = np.array([4, 5, 6])
		>>> manhattan(A, B)
		9
    """
    return np.sum(np.abs(B - A))

def tchebychev(A, B):
	return np.max(np.abs(B - A))

def optimizedTchebychev(A, B):
    """
    Calcule la distance de Tchebychev entre deux vecteurs de même taille.

    Paramètres:
    	A (np.ndarray): Vecteur de taille n.
    	B (np.ndarray): Vecteur de taille n.

    Retourne:
   		float: La distance de Tchebychev entre les deux vecteurs.

    Exemple:
		>>> A = np.array([1, 2, 3])
		>>> B = np.array([4, 5, 6])
		>>> tchebychev(A, B)
		3
    """
    return np.max(np.abs(B - A))


if __name__ == "__main__":
	import unittest as ut

	# PROMPT: Tu es un développeur Python et tu dois implémenter des fonctions de calculs de distances. Tu commences par la distance Euclidienne entre deux vecteurs de taille n (Vecteurs étant des NDArray de Numpy) et tu écrits: ```python import numpy as np def euclidienne(A, B):return np.sqrt(np.sum((B - A)**2)) ``` Après cela, tu dois documenter la fonction, écrire des tests unitaires, la refactoriser et enfin améliorer ses performances.
	# D'accord, commençons par tout cela en détails. Voici le processus complet :

	# ### 1. Implémentation de la Fonction de Distance Euclidienne
	# ```python
	# import numpy as np

	# def euclidienne(A, B):
	#     return np.sqrt(np.sum((B - A)**2))
	# ```

	# ### 2. Documentation de la Fonction
	# ```python
	# import numpy as np

	# def euclidienne(A, B):
	#     """
	#     Calcule la distance Euclidienne entre deux vecteurs de même taille.
		
	#     Paramètres:
	#     A (np.ndarray): Vecteur de taille n.
	#     B (np.ndarray): Vecteur de taille n.
		
	#     Retourne:
	#     float: La distance Euclidienne entre les deux vecteurs.
		
	#     Exemple:
	#     >>> A = np.array([1, 2, 3])
	#     >>> B = np.array([4, 5, 6])
	#     >>> euclidienne(A, B)
	#     5.196152422706632
	#     """
	#     return np.sqrt(np.sum((B - A)**2))
	# ```

	# ### 3. Tests Unitaires
	# Pour les tests unitaires, nous pouvons utiliser le module `unittest`.

	# ```python
	# import unittest
	# import numpy as np

	# class TestEuclidienne(unittest.TestCase):
	#     def test_euclidienne(self):
	#         A = np.array([1, 2, 3])
	#         B = np.array([4, 5, 6])
	#         self.assertAlmostEqual(euclidienne(A, B), 5.196152422706632)
		
	#     def test_euclidienne_zero(self):
	#         A = np.array([0, 0, 0])
	#         B = np.array([0, 0, 0])
	#         self.assertAlmostEqual(euclidienne(A, B), 0.0)
		
	#     def test_euclidienne_negative(self):
	#         A = np.array([-1, -2, -3])
	#         B = np.array([1, 2, 3])
	#         self.assertAlmostEqual(euclidienne(A, B), 7.483314773547883)

	# if __name__ == '__main__':
	#     unittest.main()
	# ```

	# ### 4. Refactorisation
	# Refactoriser pour améliorer la lisibilité et la clarté du code.

	# ```python
	# import numpy as np

	# def euclidienne(A, B):
	#     """
	#     Calcule la distance Euclidienne entre deux vecteurs de même taille.
		
	#     Paramètres:
	#     A (np.ndarray): Vecteur de taille n.
	#     B (np.ndarray): Vecteur de taille n.
		
	#     Retourne:
	#     float: La distance Euclidienne entre les deux vecteurs.
		
	#     Exemple:
	#     >>> A = np.array([1, 2, 3])
	#     >>> B = np.array([4, 5, 6])
	#     >>> euclidienne(A, B)
	#     5.196152422706632
	#     """
	#     diff = B - A
	#     return np.sqrt(np.sum(diff ** 2))
	# ```

	# ### 5. Amélioration des Performances
	# Pour améliorer les performances de la fonction, nous pouvons utiliser `np.linalg.norm` qui est optimisé pour ce genre de calcul.

	# ```python
	# import numpy as np

	# def euclidienne(A, B):
	#     """
	#     Calcule la distance Euclidienne entre deux vecteurs de même taille.
		
	#     Paramètres:
	#     A (np.ndarray): Vecteur de taille n.
	#     B (np.ndarray): Vecteur de taille n.
		
	#     Retourne:
	#     float: La distance Euclidienne entre les deux vecteurs.
		
	#     Exemple:
	#     >>> A = np.array([1, 2, 3])
	#     >>> B = np.array([4, 5, 6])
	#     >>> euclidienne(A, B)
	#     5.196152422706632
	#     """
	#     return np.linalg.norm(B - A)
	# ```

	# Ce processus couvre l'implémentation initiale, la documentation, les tests unitaires, la refactorisation pour la lisibilité, et l'amélioration des performances de la fonction de calcul de distance Euclidienne. Si tu as d'autres questions ou besoin d'autres exemples, n'hésite pas !
	class TestEuclidienne(ut.TestCase):
		def test_euclidienne(self):
			A = np.array([1, 2, 3])
			B = np.array([4, 5, 6])
			self.assertAlmostEqual(euclidienne(A, B), 5.196152422706632)

		def test_euclidienne_zero(self):
			A = np.array([0, 0, 0])
			B = np.array([0, 0, 0])
			self.assertAlmostEqual(euclidienne(A, B), 0.0)

		def test_euclidienne_negative(self):
			A = np.array([-1, -2, -3])
			B = np.array([1, 2, 3])
			self.assertAlmostEqual(euclidienne(A, B), 7.483314773547883)

	# PROMPT: Tu es un développeur Python et tu dois implémenter des fonctions de calcul de distances. Tu commences par la distance de Manhattan entre deux vecteurs de taille n (Vecteurs étant des NDArray de Numpy) et tu écris: ```python import numpy as np def manhattan(A, B): return np.sum(np.abs(B - A)) ``` Après cela, tu dois documenter la fonction, écrire des tests unitaires, la refactoriser et enfin améliorer ses performances.
	# Parfait ! Voici comment procéder avec votre fonction de distance de Manhattan.

	# ### 1. Documentation de la Fonction
	# Tout d'abord, nous allons ajouter une docstring à la fonction pour expliquer son fonctionnement.

	# ```python
	# import numpy as np

	# def manhattan(A, B):
	#     """
	#     Calcule la distance de Manhattan entre deux vecteurs de même taille.
		
	#     Paramètres:
	#     A (np.ndarray): Vecteur de taille n.
	#     B (np.ndarray): Vecteur de taille n.
		
	#     Retourne:
	#     float: La distance de Manhattan entre les deux vecteurs.
		
	#     Exemple:
	#     >>> A = np.array([1, 2, 3])
	#     >>> B = np.array([4, 5, 6])
	#     >>> manhattan(A, B)
	#     9
	#     """
	#     return np.sum(np.abs(B - A))
	# ```

	# ### 2. Tests Unitaires
	# Nous allons maintenant écrire des tests unitaires en utilisant `unittest`.

	# ```python
	# import unittest
	# import numpy as np

	# class TestManhattan(unittest.TestCase):
	#     def test_manhattan(self):
	#         A = np.array([1, 2, 3])
	#         B = np.array([4, 5, 6])
	#         self.assertEqual(manhattan(A, B), 9)
		
	#     def test_manhattan_zero(self):
	#         A = np.array([0, 0, 0])
	#         B = np.array([0, 0, 0])
	#         self.assertEqual(manhattan(A, B), 0)
		
	#     def test_manhattan_negative(self):
	#         A = np.array([-1, -2, -3])
	#         B = np.array([1, 2, 3])
	#         self.assertEqual(manhattan(A, B), 12)

	# if __name__ == '__main__':
	#     unittest.main()
	# ```

	# ### 3. Refactorisation
	# Refactorisons le code pour améliorer la lisibilité et la clarté.

	# ```python
	# import numpy as np

	# def manhattan(A, B):
	#     """
	#     Calcule la distance de Manhattan entre deux vecteurs de même taille.
		
	#     Paramètres:
	#     A (np.ndarray): Vecteur de taille n.
	#     B (np.ndarray): Vecteur de taille n.
		
	#     Retourne:
	#     float: La distance de Manhattan entre les deux vecteurs.
		
	#     Exemple:
	#     >>> A = np.array([1, 2, 3])
	#     >>> B = np.array([4, 5, 6])
	#     >>> manhattan(A, B)
	#     9
	#     """
	#     diff = np.abs(B - A)
	#     return np.sum(diff)
	# ```

	# ### 4. Amélioration des Performances
	# Pour optimiser cette fonction, nous pouvons utiliser des opérations vectorielles plus rapides fournies par NumPy. Cependant, étant donné que `np.sum(np.abs(B - A))` est déjà très performant, il n'y a pas de refactorisation spécifique à faire pour améliorer les performances. Nous pouvons cependant nous assurer d'utiliser les bonnes pratiques pour que le code reste efficace et clair.

	# ### Conclusion
	# Nous avons documenté la fonction, écrit des tests unitaires, refactorisé le code pour plus de clarté et confirmé que la fonction est déjà optimisée pour les performances. Si tu as d'autres questions ou si tu as besoin d'autres fonctions de distance, fais-le-moi savoir !
	class TestManhattan(ut.TestCase):
		def test_manhattan(self):
			A = np.array([1, 2, 3])
			B = np.array([4, 5, 6])
			self.assertEqual(manhattan(A, B), 9)

		def test_manhattan_zero(self):
			A = np.array([0, 0, 0])
			B = np.array([0, 0, 0])
			self.assertEqual(manhattan(A, B), 0)

		def test_manhattan_negative(self):
			A = np.array([-1, -2, -3])
			B = np.array([1, 2, 3])
			self.assertEqual(manhattan(A, B), 12)
	
	# PROMPT: Tu es un développeur Python et tu dois implémenter des fonctions de calcul de distances. Tu commences par la distance de Tchebychev entre deux vecteurs de taille n (Vecteurs étant des NDArray de Numpy) et tu écris: ```python import numpy as np def tchebychev(A, B): return np.max(np.abs(B - A)) ``` Après cela, tu dois documenter la fonction, écrire des tests unitaires, la refactoriser et enfin améliorer ses performances.
	# Parfait, voici comment procéder avec la fonction de distance de Tchebychev.

	# ### 1. Documentation de la Fonction
	# Ajoutons une docstring à la fonction pour expliquer son fonctionnement.

	# ```python
	# import numpy as np

	# def tchebychev(A, B):
	#     """
	#     Calcule la distance de Tchebychev entre deux vecteurs de même taille.
		
	#     Paramètres:
	#     A (np.ndarray): Vecteur de taille n.
	#     B (np.ndarray): Vecteur de taille n.
		
	#     Retourne:
	#     float: La distance de Tchebychev entre les deux vecteurs.
		
	#     Exemple:
	#     >>> A = np.array([1, 2, 3])
	#     >>> B = np.array([4, 5, 6])
	#     >>> tchebychev(A, B)
	#     3
	#     """
	#     return np.max(np.abs(B - A))
	# ```

	# ### 2. Tests Unitaires
	# Nous allons maintenant écrire des tests unitaires en utilisant `unittest`.

	# ```python
	# import unittest
	# import numpy as np

	# class TestTchebychev(unittest.TestCase):
	#     def test_tchebychev(self):
	#         A = np.array([1, 2, 3])
	#         B = np.array([4, 5, 6])
	#         self.assertEqual(tchebychev(A, B), 3)
		
	#     def test_tchebychev_zero(self):
	#         A = np.array([0, 0, 0])
	#         B = np.array([0, 0, 0])
	#         self.assertEqual(tchebychev(A, B), 0)
		
	#     def test_tchebychev_negative(self):
	#         A = np.array([-1, -2, -3])
	#         B = np.array([1, 2, 3])
	#         self.assertEqual(tchebychev(A, B), 6)

	# if __name__ == '__main__':
	#     unittest.main()
	# ```

	# ### 3. Refactorisation
	# Refactorisons le code pour améliorer la lisibilité et la clarté.

	# ```python
	# import numpy as np

	# def tchebychev(A, B):
	#     """
	#     Calcule la distance de Tchebychev entre deux vecteurs de même taille.
		
	#     Paramètres:
	#     A (np.ndarray): Vecteur de taille n.
	#     B (np.ndarray): Vecteur de taille n.
		
	#     Retourne:
	#     float: La distance de Tchebychev entre les deux vecteurs.
		
	#     Exemple:
	#     >>> A = np.array([1, 2, 3])
	#     >>> B = np.array([4, 5, 6])
	#     >>> tchebychev(A, B)
	#     3
	#     """
	#     diff = np.abs(B - A)
	#     return np.max(diff)
	# ```

	# ### 4. Amélioration des Performances
	# Pour optimiser cette fonction, nous pouvons utiliser des opérations vectorielles fournies par NumPy. Cependant, comme `np.max(np.abs(B - A))` est déjà très performant, il n'y a pas de refactorisation spécifique à faire pour améliorer les performances.

	# ### Conclusion
	# Nous avons documenté la fonction, écrit des tests unitaires, refactorisé le code pour plus de clarté et confirmé que la fonction est déjà optimisée pour les performances. Si tu as d'autres questions ou si tu as besoin d'autres fonctions de distance, fais-le-moi savoir
	class TestTchebychev(ut.TestCase):
		def test_tchebychev(self):
			A = np.array([1, 2, 3])
			B = np.array([4, 5, 6])
			self.assertEqual(tchebychev(A, B), 3)
		
		def test_tchebychev_zero(self):
			A = np.array([0, 0, 0])
			B = np.array([0, 0, 0])
			self.assertEqual(tchebychev(A, B), 0)
		
		def test_tchebychev_negative(self):
			A = np.array([-1, -2, -3])
			B = np.array([1, 2, 3])
			self.assertEqual(tchebychev(A, B), 6)

	ut.main()