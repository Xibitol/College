import math as m

n = None
while type(n) is not float:
	try:
		n = float(input("Give a number : "))
	except Exception as e:
		print("Please give a correct value.")

if n < 0: print("There is not square root for a negative number...")
else: print(f"Square root of your number : {m.sqrt(n)}")