from typing import Iterable, Callable

def indexify[T](
	strings: Iterable[str],
	action: Callable[[str], T] = lambda s: s[0]
) -> dict[T, list[str]]:
	index = {}

	for w in strings:
		key = action(w)
		if key in index: index[key].append(w)
		else: index[key] = [w]

	return index

if __name__ == "__main__":
	import argparse

	algorithms = {
		"premiere_lettre": lambda s: s[0],
		"longueur": lambda s: len(s),
		"lettres": lambda s: "".join(sorted(set(s)))
	}

	parser = argparse.ArgumentParser()
	parser.add_argument("words", nargs='+',
		help="Words to indexify.",
		type=str
	)
	parser.add_argument("--cle",
		help="Used key.",
		choices=algorithms.keys(),
		default=list(algorithms.keys())[0],
		type=str
	)

	args = parser.parse_args()

	for (c, l) in sorted(
		indexify(args.words, algorithms[args.cle]).items(),
		key = lambda t: t[0]
	):
		print("Index {}:".format(c))
		for w in sorted(l):
			print("  - {}".format(w))