# A
for i in range(6):
	print(i, end=" ")
print("")

print("-"*10)
# B
nums = [None, None]
for i in range(len(nums)):
	while type(nums[i]) is not int:
		try:
			nums[i] = int(input(f"Number {i + 1} ? "))
		except Exception as e:
			print("Please enter a integer.")

sNums = sorted(nums, reverse=True)
sum = 0
for i in range(sNums[1]):
	sum += sNums[0]
print(f"{nums[0]}x{nums[1]} = {sum}")