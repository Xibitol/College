"""
"""
__author__ = "Xibitol"

from typing import Literal, TypeAlias, TypeVar
from numpy import ndarray, dtype, uint

import itertools as it
import collections as collec
import numpy as np
import matplotlib.pyplot as plt
from matplotlib.animation import FuncAnimation

VISION_RANGE = tuple(range(-1, 2))

type Grid[N] = ndarray[tuple[N, N], dtype[bool]]

def voisins(i: int, j: int , N: int) -> list[tuple[uint, uint]]:
    return [
		(max(0, min(i + x, N - 1)), max(0, min(j + y, N - 1)))
		for x in VISION_RANGE for y in VISION_RANGE
		if not (x == 0 and y == 0)
	]

def habitee[T: uint](g: Grid[T], N: T, i: int, j: int) -> bool:
    return g[i, j] == 1

def nb_voisins_habites[T: uint](g: Grid[T], N: T, i: int, j: int) -> int:
	counter = it.count()
	collec.deque(zip(
		filter(lambda p: habitee(g, N, p[0], p[1]), voisins(i, j, N)),
		counter
	), maxlen=0)
	return next(counter)

def etat_suivant[T: uint](g: Grid[T], N: T, i: int, j: int) -> bool:
	nbNeighbors = nb_voisins_habites(g, N, i, j)
	return nbNeighbors in (2, 3) if habitee(g, N, i, j) else nbNeighbors == 3

def grille_suivante[T: uint](g: Grid[T], N: T) -> Grid[T]:
	points = filter(
		lambda p: etat_suivant(g, N, p[0], p[1]),
		[(x, y) for x in range(N) for y in range(N)]
	)
	ng = np.zeros((N, N), dtype=bool)

	for (x, y) in points:
		ng[x, y] = True

	return ng

def jeu_de_la_vie(g, N, nframes=30):
    fig = plt.figure()
    im = plt.imshow(g, interpolation='none', cmap='binary')

    def animate(_):
        im.set_array(grille_suivante(im.get_array(), N))

    return FuncAnimation(fig, animate, frames=nframes, repeat=False)