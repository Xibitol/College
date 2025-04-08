import turtle as t

def reinit(coords: tuple[float, float]):
	t.penup()
	t.goto(coords)
	t.setheading(0)

def rect(l: float, h: float, x: float, y: float):
	reinit((x, y))

	t.pendown()
	for length in (l if i%2 == 0 else h for i in range(4)):
		t.forward(length)
		t.left(90)
	t.penup()

def histo(data: list[float], l: float, x: float = t.xcor(), y: float = t.ycor()):
	reinit((x, y))
	
	for i, d in enumerate(data):
		rect(l, d, x + i*l, y)
		t.forward(l)
		

if __name__ == "__main__":
	import random
	FACTOR, QUANTITY = random.randint(10, 10000), 10
	DATA = [random.random()*random.choice((-1, 1))*FACTOR for i in range(QUANTITY)]
	SPACE, WINDOW_HEIGHT, BAR_LARGER = 20, 500, 35

	# Exercise
	histo([100, 50, 120, 12, -30, 60, 250, 100], 35, 0, 0)
	
	# Bonus
	input("Enter something to continue...")
	t.clear()
	data = [d*WINDOW_HEIGHT/2/FACTOR for d in DATA]
	t.setup(BAR_LARGER*QUANTITY, WINDOW_HEIGHT)
	t.setworldcoordinates(0, -WINDOW_HEIGHT/2, BAR_LARGER*QUANTITY, WINDOW_HEIGHT/2)
	histo(data, BAR_LARGER)

	t.exitonclick()