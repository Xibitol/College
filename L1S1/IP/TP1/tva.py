print("Welcome to the HT - TTC converter !")

tva = 20.6

print(f"Currently, the TVA is at {tva}%.", end="\n\n")

ht = None
while type(ht) != "float":
    try:
        ht = float(input("What's the HT price ? "))
        break
    except Exception as ignored:
        print("Please enter something that looks like a number.")

print(f"Then, the TTC price of your HT price is : {ht*(1+tva/100)}")