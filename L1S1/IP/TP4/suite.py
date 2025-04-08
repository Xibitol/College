suiteDef = {
	"u0": {"t": float, "v": None},
	"r": {"t": float, "v": None},
	"n": {"t": int, "v": None}
}

for (k, d) in suiteDef.items():
	while type(d["v"]) is not d["t"]:
		try:
			d["v"] = d["t"](input(f"{k} ? "))
			if k == "n" and d["v"] <= 0:
				raise Exception(k)
		except Exception as e:
			print(f"Please enter a valid integer{' upper than zero' if e.args[0] == 'n' else ''}.")
			d["v"] = None

print(f"The {suiteDef['n']['v']} first numbers are :")
cummulator = suiteDef["u0"]['v']
for n in range(suiteDef["n"]['v']):
	print(cummulator, end=" ")
	cummulator = cummulator + suiteDef["r"]['v']

print()