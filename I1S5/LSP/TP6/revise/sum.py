if __name__ == "__main__":
	from revise import intsum

	n = None

	while n is None:
		try:
			n = int(input("Enter a number: "))

			if n <= 0:
				raise ValueError("Number must be a positive or null integer;")
		except ValueError as e:
			print(f"Invalid input ({e})...")
			n = None

	print(f"Sum of integers between 1 and {n} is {intsum(n)}.")