import json

with open("projet.json") as f:
    data = json.load(f)

print(data[1]["a"])