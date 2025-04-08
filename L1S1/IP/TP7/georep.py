import math, turtle, colorsys

BASE_DISTANCE = 100

def printTitle(title: str, level: int = 1):
	assert level in [l for l in range(1, 5)], "The title level can only be between 1 and 4 included."
	print("-"*(5 - level + 1), title, "-"*(5- level))

def createPoint(x: float, y: float) -> dict[str, float | int]:
	return {"x": x, "y": y}

def isPointClose(p1: dict[str, float | int], p2: dict[str, float | int], tol: float = 1e-8) -> bool:
	return (
		math.isclose(p1["x"], p2["x"], abs_tol=tol)
		and math.isclose(p1["y"], p2["y"], abs_tol=tol)
	)

def trace(k: int, silent: bool = True) -> list[dict[str, float | int]]:
	if not silent: print("Starting plotting...")

	# Searching of the biggest divisor
	inf, sup, div, biggest = 91, 179, k*360, 1
	for m in range(sup, inf - 1, -1):
		if div%m == 0:
			biggest = m
			if not silent: print(f"The biggest divisor of {div} in [{91}, {179}] is {biggest}")
			break

	# Number 4, 5
	points = [createPoint(turtle.xcor(), turtle.ycor())]

	turtle.speed(1000)
	while True:
		turtle.forward(BASE_DISTANCE)
		turtle.left(biggest)

		p = createPoint(turtle.xcor(), turtle.ycor())
		points.append(p)
		if len(points) > 1 and isPointClose(points[0], p):
			if not silent: print("Returned to its aproximative start.")
			break
	
	roundTurtleCoords()
	return points

def roundTurtleCoords():
	turtle.goto(round(turtle.xcor()), round(turtle.ycor()))

# INITIALIZATION
turtle.penup()
turtle.back(BASE_DISTANCE*2)
turtle.pendown()

printTitle("Number ~, 6, 7")
points = trace(7, False)

# Number 6
pointsDupli = list(map(lambda p1: {**p1, "count": (
	len(list(filter(lambda p2: isPointClose(p1, p2), points)))
)}, points))

if pointsDupli[0]["count"] == 2 and pointsDupli[-1]["count"] == 2:
	print("First and last points are aproximatively identical !")

# Number 7
pointsRDUpli = list(filter(lambda pD: pD["count"] >= 2, pointsDupli[1:-1]))
if len(pointsRDUpli) <= 0:
	print("The turtle hasn't moved on another point 2 times.")
else:
	for pRD in pointsRDUpli:
		print(f"The turtle has moved 2 times on the point {pRD[1]}")

# CHALLENGE 1
printTitle("Challenges 1")
turtle.penup()
turtle.forward(BASE_DISTANCE*3/2)
turtle.pendown()
roundTurtleCoords()

NUMBER_OF_K_VALUES = 2
kValues, i = [None]*NUMBER_OF_K_VALUES, 0
while any([type(k) is not int for k in kValues]) or i < len(kValues):
	try:
		kValues[i] = int(input(f"Choose a value for k n°{i + 1} : "))
		i += 1
	except ValueError as _:
		print("Please enter a valid integer.")

kPoints, similarityCount = [], 0

for i, k in enumerate(kValues):
	turtle.color(colorsys.hls_to_rgb(i/len(kValues), 0.5, 0.5))
	kPoints.append(trace(k))
turtle.color(0, 0, 0)

for i, p in enumerate(kPoints[0]):
	for points in kPoints[1:]:
		for j, pBis in enumerate(points):
			if (
				isPointClose(p, pBis) and
				(i != 0 or j != len(points) - 1) and
				(i != len(kPoints[0]) - 1 or j != 0)
			):
				similarityCount += 1
print(f"The two plots has {similarityCount} points in common.")

# CHALLENGE 2
printTitle("Challenges 2")
turtle.penup()
turtle.forward(BASE_DISTANCE*3/2)
turtle.pendown()

k = None
while type(k) is not int:
	try:
		k = int(input(f"Choose a value for k : "))
	except ValueError as _:
		print("Please enter a valid integer.")

points = trace(k)



turtle.mainloop()