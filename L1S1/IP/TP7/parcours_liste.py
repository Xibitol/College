import random as r

R = [r.randint(1, 20) for _ in range(10)]
print("Debug :", R)

long = len(R)

print(*R, sep=" : ")

print(sum(R))

print(R.index(max(R)))

print(len(list(filter(lambda n: n >= 10, R))))

R2 = [n*(-1 if n > 10 else 1) for n in R]
print("Debug :", R2)

R3 = R + R2
print("Debug :", R3)