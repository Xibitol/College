import time

secret = ''
f = open("resource/db.txt")
while True:
    line = f.readline()
    if line == "": # fin de fichier
        break
    word = line.strip()
    if word == secret:
        print("Trouvé")
        break
time.sleep(5)
f.close()