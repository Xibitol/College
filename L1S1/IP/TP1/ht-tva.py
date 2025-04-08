print("Welcome to the HT - TTC converter !")
print("Currently, the TVA is at 20.6%.", end="\n\n")
tvaFac = 1+20.6/100

ht = None
while type(ht) != "float":
    try:
        ht = float(input("What's the HT price ? "))
        break
    except Exception as ignored:
        print("Please enter something that looks like a number.")

print("Then, the TTC price of your HT price is :", ht*tvaFac)