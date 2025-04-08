i = 5
f = 2.17
b = True
s = "chaine"
print(10, i / 2, i // 2, i % 2)
print(f, int(f), i // 2.0, float(i) / 2)
si = str(i)
sf = str(f)
print(i + f, si + sf)
# corrigez la ligne suivante
# This line "print(s + i)" must be changed to
print(s + str(i)) # where i was converted to a string.

# Ex3: The last line cannot be executed properly; In Python, its isn't allowed to concatenate a string with an int.
