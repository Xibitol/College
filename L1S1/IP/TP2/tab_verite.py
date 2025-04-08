def v(b1: bool, b2: bool) -> bool:
	return b1 and not b2 or not b1 and b2

def boolPrint(b: bool) -> str:
	return (str(b) + " ") if b else str(b)

print("b2\\\\b1| True  | False |")
print("-----------------------")
for row in [True, False]:
	print(f"{boolPrint(row)} | {boolPrint(v(True, row))} | {boolPrint(v(False, row))} |")

# Question awnser : This truth table corresponds to the xor operator