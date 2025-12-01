import csv

with open("notes.csv", "r") as f:
	iter = list(csv.reader(f, delimiter=";"))

	count = 0
	notAbsCount = 0
	gradeSum = 0
	numOfSC = 0
	for student in iter:
		count += 1
		if student[-2] != "ABS":
			gradeSum += float(student[-2])
			notAbsCount += 1
		if student[-1] == "OUI": numOfSC += 1

	print(f"Count: {count}; Average Grade: {round(gradeSum/notAbsCount, 1)}")
	print(f"Num of second chance: {numOfSC} ({numOfSC*100/count}%)")