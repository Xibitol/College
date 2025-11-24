from typing import Iterable

def avg(numbers: Iterable[int | float]) -> int | float:
	return sum(numbers)/len(numbers)

if __name__ == "__main__":
	import argparse
	
	parser = argparse.ArgumentParser()
	parser.add_argument("number",
		nargs='+',
		help="Numbers to include in average.",
		type=float
	)

	args = parser.parse_args()
	
	print(avg(args.number))