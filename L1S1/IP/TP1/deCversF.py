print("Welcome to the °C - °F converter !")

def convertCToF(temp: float) -> float:
    return temp*9/5 + 32

base = 0
print(f"By the way, {base}°C is equal to {convertCToF(base)}°F.", end="\n\n")

temp = None
while type(temp) != "float":
    try:
        temp = float(input("What's the your temp in °C ? "))
        break
    except Exception as ignored:
        print("Please enter something that looks like a number.")

print(f"The temp in °F of your one in °C is {convertCToF(temp)}°F.")