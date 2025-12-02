import numpy as np
import matplotlib.pyplot as plt

fig = plt.figure(figsize=(16, 12))
manager = fig.canvas.manager
if manager is not None:
	manager.set_window_title(f"Images")
gridspec = fig.add_gridspec(3, 3)

axe = fig.add_subplot(gridspec[0, 0])
img = np.random.choice(range(255), (128, 128))
axe.imshow(img)

# ---
axe = fig.add_subplot(gridspec[0, 1])
square = np.zeros((10, 10), dtype=int)
square[2:8, 2:8] = 1
axe.imshow(square)

# ---
axe = fig.add_subplot(gridspec[0, 2])
square = np.zeros((10, 10), dtype=int)
square[::2, ::2] = 1
square[1::2, 1::2] = 1
axe.imshow(square)

# ---
axe = fig.add_subplot(gridspec[1, 0])

img = plt.imread("resource/kitty.jpg")
axe.imshow(img[::20, ::20], interpolation = "none")

# ---
axe = fig.add_subplot(gridspec[1, 1])

img = plt.imread("resource/kitty.jpg")
axe.imshow(img.sum(axis = 2)/3, cmap = "gray")

# ---
axe = fig.add_subplot(gridspec[1, 2])

img = plt.imread("resource/kitty.jpg")
axe.imshow(img.sum(axis = 2)/3 >= 125, cmap = "gray")

# ---
axe = fig.add_subplot(gridspec[2, 0])

img = plt.imread("resource/kitty.jpg")
axe.imshow(np.rot90(img, k = 2))

# ---
axe = fig.add_subplot(gridspec[2, 1])

img = plt.imread("resource/kitty.jpg").sum(axis = 2)/3
yeux = img[220:350, 250:700]
img[100:230, 200:650] = yeux
axe.imshow(img)

plt.show()