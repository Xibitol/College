from ppn import getNearest
from supp_tp1 import levenshteinDistance
from tqdm import tqdm

SPACE = " "

with open("dico.txt") as f:
	words = f.read().splitlines()

# Words
sentence = input("Type a sentence : ").lower()
end = sentence[-1] if sentence[-1] == "." else ""
sWords = sentence.split(SPACE)

correctedWords = []
for i, word in enumerate(tqdm(sWords)):
	correctedWords.append(
		words[getNearest(words, word, levenshteinDistance)[0]]
	)

if sentence == correctedWords:
	print("Its well written ...")
else:
	print("Corrected sentence :", SPACE.join(correctedWords).capitalize() + end)

# FIXME: levenshteinDistance isn't working properly; Make a better algorithm