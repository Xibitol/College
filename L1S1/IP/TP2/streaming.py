MAX_OUT = 10**8
MIN_Q = 4000
MAX_Q = 8000

subs = None
while type(subs) is not int:
	try:
		subs = int(input("Write down the number of subscribers : "))
	except Exception as e:
		print("Please enter a correct value")

minSubs = MAX_OUT//MAX_Q
maxSubs = MAX_OUT//MIN_Q

if subs <= minSubs:
	print("S1 : The system can provide the maximum quality to all subscribers.",
	   f"You can even add up to {minSubs - subs} new ones.")
elif subs > maxSubs:
	print("S3 : The system can't provide the minimum quality to all subscribers.",
	   f"You need to remove at less {subs - maxSubs} ones of them.")
else:
	print("S2 : The system can provide the intermediate quality to all subscribers.",
	   f"Averange speed : {MAX_OUT/subs}")