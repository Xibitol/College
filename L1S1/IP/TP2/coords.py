coords = {"x": None, "y": None}

for k in coords.keys():
	while type(coords[k]) is not float:
		try:
			coords[k] = float(input(f"{k} ? "))
		except Exception as e:
			print("Please enter a floating-point number.")

print(f"The point at ({coords['x']};{coords['y']}),"
	  f"{'is' if (coords['x'] >= 0 and coords['y'] >= 0 and coords['x']**2 + coords['y']**2 < 1) else 'is not'}",
	  "in the circle's upper right quarter.")