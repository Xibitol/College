import math

number = None
while type(number) is not float:
	try:
		number = float(input("Which number do you want its square root ? "))

		if number < 0: raise Exception()
	except Exception as _:
		print("Please enter a valid number (Upper than 0).")

srMin, srMax, sr = 0, number, number
props = []
while not math.isclose(sr**2, number, abs_tol=1e-09):
	sr = (srMin + srMax)/2
	props.append((sr, srMin, srMax))

	if sr**2 < number: srMin = sr
	else: srMax = sr

print(
	f"Propositions ({len(props)}):",
	";\n".join(map(lambda p: f"\t- {p[0]} in [{p[1]};{p[2]}]", props)),
	f"The square root of {number} is {sr}.",
	sep="\n"
)