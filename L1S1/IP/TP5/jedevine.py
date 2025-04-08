import random

MIN, MAX = 1, 20
number = random.randint(MIN, MAX)

print(f"Start of the game in [{MIN};{MAX}]")
print("-"*30)

inpt = None
while inpt != number:
	try:
		inpt = int(input("Take a number in the range: "))

		if inpt == number:
			print(f"Congratulations, I've chosen {number}.")
		else:
			print("Bigger!" if inpt < number else "Smaller!")
	except Exception as e:
		print("Please enter a valid number")