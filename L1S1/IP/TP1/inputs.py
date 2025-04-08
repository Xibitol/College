print("Entrez une valeur : ", end="")
x=input()
print(x,"est de type", type(x))

# print("Entrez une valeur : ", end="")
# x = input()
# x = float(x)
x = float(input("Entrez une valeur : "))
print(x,"est de type", type(x))

# Or we can do that to prevent the user of entering to no-castable values
x = None
while type(x) != "float":
    try:
        x = float(input("Entrez une valeur : "))
        break
    except Exception as ignored:
        print("Please enter something that looks like a number")
        pass
print(x,"est de type", type(x))