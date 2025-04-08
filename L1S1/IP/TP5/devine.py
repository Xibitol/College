import random, math

MIN, MAX = 1, 20
COMMANDS = {'+': 1, '=': 0, '-': -1}
COMMAND_NAMES = list(COMMANDS.keys())

auto = None
while type(auto) is not bool:
	auto = str(input("Would you like to play as a spectator ? "))

	if auto.lower() in ["y", "yes", "ya"]:
		auto = True
	elif auto.lower() in ["n", "no", "nah"]:
		auto = False
	else:
		print("Please enter 'yes' or 'no'.")

print(f"The number must be in [{MIN};{MAX}]")
print("-"*30)

nMin, nMax, number, rounds = MIN, MAX, random.randint(MIN, MAX), 1
estiNum, inpt = None, None

if auto:
	print(f"(Whisper: The computer chosen {number})")

while inpt != '=':
	estiNum, inpt = math.floor((nMax+nMin)/2), None

	while type(inpt) is not int:
		print(f"Do you've chosen {estiNum}? ", end="")
		
		if not auto:
			inpt = str(input(""))

			if inpt not in COMMAND_NAMES:
				allowed = ", ".join(map(lambda el: f"'{el}'", COMMAND_NAMES[:-1]))
				print(f"Please enter either {allowed} or '{COMMAND_NAMES[-1]}'")
				continue
			
			inpt = COMMANDS[inpt]
		else:
			command = list(filter(lambda v: min(max(-1, number-estiNum), 1) == v[1], COMMANDS.items()))[0]
			print(command[0])
			inpt = command[1]
	
	print(nMax, nMin)

	if inpt == 0:
		print(f"I found {estiNum} in {rounds} propositions!")
		break
	elif nMax == nMin or nMin == estiNum and inpt < 0 or nMax == estiNum and inpt > 0:
		print(f"I think that you are trying to trick me! There is no solution there!")
		break
	else:
		rounds += 1
		if inpt > 0: nMin = estiNum + inpt
		else: nMax = estiNum + inpt