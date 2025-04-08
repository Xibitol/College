with open("emails.txt") as f:
	M = f.read().splitlines()

print(M)
print(f"M has {len(M)} addresses.")
print(f"There is {len([0 for m in M if m.split('.')[-1] == 'fr'])} addresses that ends with \".fr\".")
print(f"{max(M, key=lambda m: len(m))} is the longest address of M.")

D = [m.split(r"@")[-1] for m in M]
Du = list({*D})

PperD = [D.count(d) for d in Du]

print([(d, PperD[i]) for i, d in enumerate(Du)])
















