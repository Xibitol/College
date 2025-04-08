"""
Algorithm:
Create a list of all walls, and create a set for each cell, each containing just that one cell.
For each wall, in some random order:
    If the cells divided by this wall belong to distinct sets:
    Remove the current wall.
    Join the sets of the formerly divided cells.
    Test end of generation.
"""
import itertools as it
import random
from turtle import *

from my_set import *


def draw_walls(walls, N, w=800):
    def draw_wall_0(i, j, cote):
        penup()
        setpos(int(cote * (i + 0.5)), int(cote * (j + 0.5)))
        setheading(270)
        pendown()
        forward(cote)

    def draw_wall_1(i, j, cote):
        penup()
        setpos(int(cote * (i - 0.5)), int(cote * (j + 0.5)))
        setheading(0)
        pendown()
        forward(cote)

    cote = w / N
    screen = Screen()
    screen.setup(w + 4, w + 8)
    screen.setworldcoordinates(-cote, -cote, w, w)
    speed(0)
    penup()
    setpos(int(0 - cote / 2), int(0 - cote / 2))
    setheading(0)
    pendown()
    forward(N * cote)
    left(90)
    forward(N * cote)
    left(90)
    forward(N * cote)
    left(90)
    forward(N * cote)
    for wall in walls:
        i, j, o = wall
        if o == 0:
            draw_wall_0(i, j, cote)
        elif o == 1:
            draw_wall_1(i, j, cote)


def find_set(cells, ij):
    for idx, cell in enumerate(cells):
        if set_in(cell, ij):
            return idx
    return None


if __name__ == "__main__":
    N = 20

    walls = [(i, j, o) for i, j, o in it.product(range(N), range(N), [0, 1])]
    cells = [[(i, j)] for i, j in it.product(range(N), range(N))]
    start = (0, 0)
    end = (N - 1, N - 1)

    while walls:
        i_wall = random.randint(0, len(walls) - 1)
        i, j, o = walls[i_wall]
        i_set1 = find_set(cells, (i, j))
        if o == 0 and i < N - 1:
            i_set2 = find_set(cells, (i + 1, j))
        elif o == 1 and j < N - 1:
            i_set2 = find_set(cells, (i, j + 1))
        else:
            continue
        if i_set1 != i_set2:
            union = set_union(cells[i_set1], cells[i_set2])
            cells[i_set1] = union
            del cells[i_set2]
            del walls[i_wall]
            # un chemin unique entre start et end
            #if set_in(union, start) and set_in(union, end):
            #    break
            # un chemin unique entre chaque cellule (plus joli)
            if len(cells) == 1:
               break
    print(cells)
    print(walls)
    draw_walls(walls, N)
    exitonclick()
