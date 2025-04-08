__author__ = ["Christophe Saint-Jean"]

from random import randint

def pivot_premier(T, g, d):
    return g


def pivot_dernier(T, g, d):
    return d

def pivot_milieu(T, g, d):
    return (g + d) // 2


def pivot_median(T, g, d):
    i1, i2, i3 = [randint(g, d) for _ in range(3)]
    if T[i2] <= T[i1] <= T[i3]: return i1
    if T[i3] <= T[i1] <= T[i2]: return i1
    if T[i1] <= T[i2] <= T[i3]: return i2
    if T[i3] <= T[i2] <= T[i1]: return i2
    return i3


def partition(T, g, d, ipivot=None):
    if ipivot is None:
        ipivot = pivot_median(T, g, d)
    if ipivot < d:
        T[ipivot], T[d] = T[d], T[ipivot]
    pivot = T[d]

    i = g
    for j in range(g, d):
        if T[j] <= pivot:
            T[i], T[j] = T[j], T[i]
            i = i + 1
    T[i], T[d] = T[d], T[i]
    return i


def tri_rapide(T, g=0, d=None):
    if d is None:
        d = len(T) - 1
    if g < d:
        ipivot = partition(T, g, d)
        tri_rapide(T, g, ipivot - 1)
        tri_rapide(T, ipivot + 1, d)


def tri_rapide_bis(T):
    if len(T) > 0:
        pivot = T[pivot_median(T, 0, len(T) - 1)]
        Tinf = [Ti for Ti in T if Ti < pivot]
        Teq = [Ti for Ti in T if Ti == pivot]
        Tsup = [Ti for Ti in T if Ti > pivot]
        return tri_rapide_bis(Tinf) + Teq + tri_rapide_bis(Tsup)
    return []


if __name__ == "__main__":
    T = [3, 6, 5, 3, 5, 6, 1, 7, 0.5, 8, 4]
    ipivot = partition(T, g=0, d=len(T) - 1)
    print(T)
    print(ipivot, T[ipivot])

    T = [3, 6, 5, 3, 5, 6, 1, 7, 0.5, 8, 4]
    tri_rapide(T)
    print(T)

    T = [3, 6, 5, 3, 5, 6, 1, 7, 0.5, 8, 4]
    print(tri_rapide_bis(T))
