import numpy as np
from scipy.optimize import linprog

# Consultez "le mode d'emploi"
c = np.array([-1,4])
A = np.array([[3,1],[-2,3],[-3,-4]])
b = np.array([29,-9,-35])
x = (0,None)
y = (0,None)

# Recherche du minimum
res = linprog(c, A, b, bounds=(x, y))
print(res.fun, res.x)

# Le maximum se situe en (96/11;31/11) et vaut 28/11
res = linprog(-c, A, b, bounds=(x, y))
print(-res.fun, res.x)