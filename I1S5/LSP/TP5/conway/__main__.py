import numpy as np
import matplotlib.pyplot as plt

import conway as cw

print("cw.voisins(1, 2, 5) ->", cw.voisins(1, 2, 5))
print("cw.voisins(3, 4, 5) ->", cw.voisins(3, 4, 5))

N = 5
g: cw.Grid[5] = np.zeros((N, N), dtype=bool)
g[2,1:4] = 1
print(g)
print(f"cw.nb_voisins_habites(g, {N}, 2, 2) ->",
	cw.nb_voisins_habites(g, N, 2, 2)
)

print(g)
print(f"cw.etat_suivant(g, {N}, 2, 2) ->",
	cw.etat_suivant(g, N, 2, 2)
)

g = cw.grille_suivante(g, N)
print(f"cw.grille_suivante(g, {N}) ->\n", g)

# ---
import argparse

parser = argparse.ArgumentParser()
parser.add_argument("N", help = "Grid size.", type = int)
parser.add_argument("--nframes", help = "Frame count.",
	type = int, default = 30
)
parser.add_argument("--seed", help = "Random seed",
	type = int, default = None
)
args = parser.parse_args()

np.random.seed(args.seed)
grid = np.random.choice((0, 1), (args.N, args.N))
anim = cw.jeu_de_la_vie(grid, args.N, args.nframes)
plt.show()