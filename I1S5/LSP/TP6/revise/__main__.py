from revise import convert_to_m, aire, note, cesar, nzp, sous_ensembles, max_occurrences

print(f"convert_to_m: 2, 5.2 -> {convert_to_m(2, 5.2)};")
print(f"aire: 5.2, 12 -> {aire(5.2, 12)};")
print(f"note: B -> {note('B')};")
print(f"note: B -> {note('X')};")
print(f"cesar: \"AIDEZ MOI STOP Z\" -> {cesar("AIDEZ MOI STOP Z", 2)};")
print("cesar: {} -> {}".format(
	[0,-4,3,1,9,0,-1,6,-2,0,7],
	nzp([0,-4,3,1,9,0,-1,6,-2,0,7])
))
print(f"sous_ensembles: {{1,2,3}} -> {sous_ensembles({1,2,3})};")

print(f"max_occurrences: {[1,2,1,3,4,5,2,7,5]} ->", end = " ")
for x in max_occurrences([1,2,1,3,4,5,2,7,5]):
    print(x, end = ", ")
print()