import math

n, sum, sign = 1_000_000, 0, 1

for k in range(n + 1):
	sum = sum + sign/((3)**k*(2*k + 1))
	sign = -sign

print(f"PI estimation : {math.sqrt(12)*sum}")

# We can see that this formula is more precise than the one seen in class.