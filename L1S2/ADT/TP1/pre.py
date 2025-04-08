import matplotlib.pyplot as plt
from tqdm import tqdm
import random

L = [round(random.random()*20 - 10, 2) for i in range(10)]

for i in tqdm(L):
	pass

plt.plot(L)
plt.show()