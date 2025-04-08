if __name__ == "__main__":
	import random as r, bisect

	def randNum(): return r.randint(0, 10)

	T = sorted([randNum() for _ in range(4)])

	print(f"In {T} ...")
	for n in [randNum() for _ in range(6)]:
		print(f"insert {n}, {"in" if n in T else "not in"} the table, with:")
		print(f"\t{bisect.bisect_left.__name__}: {bisect.bisect_left(T, n)}")
		print(f"\t{bisect.bisect_right.__name__}: {bisect.bisect_right(T, n)}")