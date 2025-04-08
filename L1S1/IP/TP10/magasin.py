d_pu = {
	"Épée en mousse": 5,
	"Masque Dark Vador": 30,
	"Hand spinner 3d": 10,
	"Console Minux": 150,
	"Lego Footix": 15
}

d_stock = {
	"Épée en mousse": 10,
	"Masque Dark Vador": 4,
	"Hand spinner 3d": 10,
	"Console Minux": 2,
	"Lego Footix": 5
}

def verification(d1: dict, d2: dict) -> bool:
	d1Keys, d2Keys = sorted(list(d1.keys())), sorted(list(d2.keys()))
	return len(d1) == len(d2) and all((d1Keys[i] == d2Keys[i] for i in range(len(d1))))

def achat_possible(k: str, v: int, d: dict):
	return k in d.keys() and d[k] >= v

def achats_possibles(a: dict[str, int], d: dict):
	return all([achat_possible(k, v, d) for k, v in a.items()])

def achats(a: dict[str, int], p: dict, s: dict, facture: bool = False):
	if(not achats_possibles(a, s)):
		if(facture):
			print("Purchases can't be done")
			return
	
	total = 0
	for obj, qty in a.items():
		s[obj] -= qty

		if(facture):
			price = qty*p[obj]
			total += price
			print(f"{obj}: {qty} x {p[obj]} = {price}")
	else:
		if(facture):
			print("-"*20)
			print("Prix total:", total)

if __name__ == "__main__":
	dpuFirstKey = list(d_pu.keys())[0]
	tests = {
		verification: [
			((d_pu, d_stock), True),
			(({**d_pu, "_": 0}, d_stock), False),
			((d_pu, {**d_stock, "_": 0}), False),
			(({**dict(list(d_pu.items())[1:]), dpuFirstKey[:-1]: d_pu[dpuFirstKey]}, d_stock), False),
			((d_pu, {**dict(list(d_stock.items())[1:]), dpuFirstKey[:-1]: d_stock[dpuFirstKey]}), False),
			(({}, {}), True)
		],
		achat_possible: [
			((dpuFirstKey, d_stock[dpuFirstKey]//2, d_stock), True),
			((dpuFirstKey[:-1], d_stock[dpuFirstKey], d_stock), False),
			((dpuFirstKey, d_stock[dpuFirstKey] + 1, d_stock), False),
			((dpuFirstKey, d_stock[dpuFirstKey], {}), False)
		],
		achats_possibles: [
			(({dpuFirstKey: d_stock[dpuFirstKey]//2}, d_stock), True),
			(({dpuFirstKey: d_stock[dpuFirstKey]}, d_stock), True),
			((d_stock, d_stock), True),
			(({}, d_stock), True),
			(({dpuFirstKey[:-1]: d_stock[dpuFirstKey]}, d_stock), False),
			(({dpuFirstKey: d_stock[dpuFirstKey] + 1}, d_stock), False),
			(({dpuFirstKey: d_stock[dpuFirstKey]//2}, {}), False)
		]
	}

	for func, cases in tests.items():
		print(f"Testing {func.__name__} function:")
		for i, (args, expected) in enumerate(cases):
			print(f"\tCase n°{i + 1}:", end=" ")

			if(func(*args) == expected):
				print("Passed ✔️")
			else:
				print(f"Failed ❌\n\t  {func.__name__}({', '.join([str(arg) for arg in args])}) != {expected}")
	
	print("")
	print("| Stock:", d_stock)
	purchases = {dpuFirstKey: d_stock[dpuFirstKey], list(d_pu.keys())[1]: d_stock[list(d_pu.keys())[1]]//2}
	print("| Purchases:", purchases)
	achats(purchases, d_pu, d_stock, True)
	print("| Stock:", d_stock)
	achats(purchases, d_pu, d_stock, True)