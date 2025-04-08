questions = [
	{"q": "Désignation ?", "t": str, "a": None},
	{"q": "Prix unitaire ?", "t": float, "a": None},
	{"q": "Quantité ?", "t": int, "a": None}
]

for q in questions:
	while type(q["a"]) is not q["t"]:
		try:
			q["a"] = q["t"](input(f"{q['q']} "))
		except Exception as _:
			print(f"Please enter a correct value (of type {q['t']})")

print(f"Facture : {questions[2]['a']} {questions[0]['a']} à {questions[1]['a']} €",
	  "l'unité font {questions[1]['a']*questions[2]['a']} €")