D = {"Nom":"Xibitol", "Prénom":"Itol"}

D["Age"] = 18

print("My age is",
	"greater" if D["Age"] >= len(D["Nom"] + D["Prénom"]) else "smaller",
	"than the length of my first and last name concatenated."
)

D2 = {"Taille": 1.90, "Yeux": "Bleu ciel"}
D.update(D2)

del D["Age"]
D["AgeLycée"] = 15

for attr, value in D.items():
	print(f"{attr}: {value}")