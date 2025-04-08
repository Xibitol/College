if __name__ == "__main__":
	import math
	import numpy as np
	import trans as tr
	import matplotlib.animation as ani
	import matplotlib.pyplot as plt

	# --- Data ---
	fps = 120
	framesCount = fps*30

	n = 60
	t = np.linspace(0,2*math.pi, n)
	SUN = np.array([np.cos(t),np.sin(t)])

	EARTH = tr.T(
		tr.H(0.2) @ np.array([[1, 1, -1, -1, 1], [-1, 1, 1, -1, -1]]),
		np.array([[6], [0]])
	)

	# --- Animation ----
	fig = plt.figure()

	sun, = plt.fill(*SUN, color="yellow")
	earth,  = plt.fill(*EARTH, color="blue")

	plt.axis('scaled')
	plt.axis((-10, 10, -10, 10))
	
	def animate(i):
		mv = np.array([
			[math.cos(i/fps*math.pi*2)*3*(math.cos(i/fps/math.pi) + 1)/2],
			[0]
		])

		earth.set_xy(tr.T(tr.R((math.pi*2)*i/fps) @ EARTH, mv).T)
		return (sun, earth)
	
	anim = ani.FuncAnimation(fig, animate, framesCount)
	anim.save('solarsys.mp4', fps = fps)