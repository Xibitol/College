def deCversF(temp: float) -> float:
    return temp*9/5 + 32

def deFversC(temp: float) -> float:
    return (temp - 32)*5/9 

if __name__ == "__main__":
	functions = [
		(deCversF, "C", "F"),
		(deFversC, "F", "C")
	]
	print("Welcome to the °C - °F converter !")

	base = 0
	print(f"By the way, {base}°C is equal to {deCversF(base)}°F.", end="\n\n")

	i, choices = None, ' '.join((f"({j + 1}) °{f[1]} to °{f[2]}" for j, f in enumerate(functions)))
	while type(i) is not int:
		try:
			i = int(input(f"Which converter do you want to use ? {choices} : ")) - 1

			if(i < 0 or len(functions) <= i):
				i = None
				raise Exception()
			break
		except Exception as ignored:
			print("Please enter a possible choice as an integer.")

	temp = None
	while type(temp) is not float:
		try:
			temp = float(input(f"What's the your temp in °{functions[i][1]} ? "))
			break
		except Exception as ignored:
			print("Please enter something that looks like a number.")

	print(f"The converted temp of your one in °{functions[i][1]} is {functions[i][0](temp)}°{functions[i][2]}.")