import turtle, json
import convertisseur, turtle_liste

def generateGraph(file: str):
	with open(file) as f:
		turtle_liste.histo([float(v) for v in f.read().splitlines()], 35)

	
if __name__ == "__main__":
	CELSIUS_PATH = "./temps/celsius.txt"
	FAHRENHEIT_PATH = "./temps/fahrenheit.txt"
	JSON_PATH = "./temps/temperatures.json"

	with open(CELSIUS_PATH) as f:
		celsiusValues = [float(v) for v in f.read().splitlines()]

	converted = [convertisseur.deCversF(v) for v in celsiusValues]

	with open(FAHRENHEIT_PATH, "w") as f:
		f.writelines((f"{c}\n" for c in converted))

	# generateGraph function testing
	generateGraph("./temps/fahrenheit.txt")
	turtle.exitonclick()

	# Saving to json
	with open(JSON_PATH, "w") as f:
		json.dump({"Celsius": celsiusValues, "Fahrenheit": converted}, f)