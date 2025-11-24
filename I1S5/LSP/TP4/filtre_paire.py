l1 = ['arbre', 'vers', 'ville', 'vélo']

l2 = list(filter(lambda s: len(s)%2 == 0, l1))

print(l2)