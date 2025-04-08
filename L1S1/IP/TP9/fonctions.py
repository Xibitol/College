def aire_carre(c: int) -> int:
    return c**2

def aire_rectangle(l: int, L: int) -> int:
    return l*L

def aire_carre2(c: int) -> int:
    return aire_rectangle(c, c)

def aire_rectangle2(c: int, l: int = None) -> int:
    return c*l if l else aire_carre(c)

def coords_carre(c: int, coords: tuple) -> list:
    return [(coords[0] + x, coords[1] + y) for y in (0, c) for x in ((0, c) if y == 0 else (c, 0))]