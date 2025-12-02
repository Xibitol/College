import time

secret = ''
f = open("resource/db.txt")
lines = f.read().split("\n")
for word in lines:
    if word == secret:
        print("Trouvé!")
        break
time.sleep(5)
f.close()