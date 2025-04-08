import turtle, math

BASE_LENGTH = 100
turtle.penup()
turtle.backward(BASE_LENGTH*5/2)

# Number 1
i = 0
turtle.pendown()
while i < 4:
	turtle.forward(BASE_LENGTH)
	turtle.left(90)
	i += 1
else:
	turtle.penup()
	turtle.forward(BASE_LENGTH*2)

# Number 2
n = None
while type(n) is not int:
	try:
		n = int(input("Choose a number of sides (min of 3) : "))
		if n < 3: raise Exception()
	except Exception as _:
		print("Please enter a valid integer.")

l, d = BASE_LENGTH*3/n, 360/n
turtle.forward(l/2)
turtle.pendown()
for i in range(n):
	turtle.forward(l)
	turtle.left(d)
else:
	turtle.penup()
	turtle.forward(l*3/2 + BASE_LENGTH)

# Number 3
inf, sup, div, biggest = 91, 179, 2520, 1
for n in range(sup, inf - 1, -1):
	if div%n == 0:
		biggest = n
		print(f"The biggest divisor of {div} in [{91}, {179}] is {biggest}")
		break

# Number 4
turtle.left(90)
turtle.forward(BASE_LENGTH/2)
turtle.right(90)
sx, sy = turtle.xcor(), turtle.ycor()
turtle.pendown()
turtle.speed(1000)
while True:
	turtle.forward(100)
	turtle.left(biggest)

	print(sx, turtle.xcor())
	if math.isclose(sx, turtle.xcor(), abs_tol=1e-8) and math.isclose(sy, turtle.ycor(), abs_tol=1e-8):
		print("Returned to its aproximative start.")
		break

turtle.mainloop()