from tqdm import tqdm
from lib.supp_tp1 import levenshteinDistance
import lib.ppn as ppn

WORDS = []
with open("data/dico.txt") as f:
	WORDS = f.read().splitlines()

def correctWord(word: str, words: list[str] = WORDS) -> str:
	return words[ppn.getNearest(words, word, levenshteinDistance)[0]]

class T9:
	CHARS_OFFSET = 97
	CHARS_TO_LETTERS = (
	   # abc     def     ghi     jkl     mno     pqrs    tuv     wxyz
		"2"*3 + "3"*3 + "4"*3 + "5"*3 + "6"*3 + "7"*4 + "8"*3 + "9"*4
	)

	def c2n(char: str) -> str:
		"""Converts char into a number with the T9 method.
		@param char A character.
		"""
		assert len(char) == 1, "char must be just a character."

		code = ord(char) - T9.CHARS_OFFSET
		assert code >= 0 and code < len(T9.CHARS_TO_LETTERS), (
			"char must be in the ascii alphabet."
		)

		return T9.CHARS_TO_LETTERS[code]

	def n2cs(num: str) -> list[str]:
		"""Gives possible convertions of num into a character with the T9
		method.
		@param num A number allowed in the T9 convertion table (Usually in
		23456789).
		"""
		nums = T9._allowedNumbers()
		assert len(num) == 1 and num in nums, (
			f"num must be a number in {nums}"
		)

		return [chr(T9.CHARS_OFFSET + i)
		  	for i, n in enumerate(T9.CHARS_TO_LETTERS)
			if n == num
		]

	def n2ws(num: str, words: list[str]) -> list[str]:
		"""Gives possible convertions of num into a word from a given dataset of
		words with the T9 method.
		@param num A sequence of numbers.
		"""
		nums = T9._allowedNumbers()
		assert all([n in nums for n in num]), (
			f"num must be a sequence of numbers in {nums}"
		)

		convertions = []
		for word in words:
			if len(word) == len(num):
				for i in range(len(word)):
					if T9.c2n(word[i]) != num[i]:
						break
				else:
					convertions.append(word)

		return convertions

	def n2ws_nByn(num: str, words: list[str], silent: bool = True) -> list[str]:
		"""Gives possible convertions of num into a word from a given dataset of
		words with the T9 method. This function is derived from n2ws and search
		for convertions from num number by number.
		@param num A sequence of numbers.
		@param words A dataset of words.
		@param silent If this function should print information during
		processing.
		"""
		PREFIX = f"[{T9.n2ws_nByn.__name__}]"

		nums = T9._allowedNumbers()
		assert all([n in nums for n in num]), (
			f"num must be a sequence of numbers in {nums}"
		)

		if not silent:
			print(f"{PREFIX} Search for convertions of {num}.")

		iconvertions = list(range(len(words)))
		for i, n in enumerate(num):
			if not silent:
				print(
					f"{PREFIX} Current possible convertions at number {i + 1}",
					f"of {num} : {len(iconvertions)}"
				)
			
			r = range(len(iconvertions) - 1, -1, -1)
			for j in (tqdm(r) if not silent else r):
				word = words[iconvertions[j]]
				if len(word) > i:
					try:
						if T9.c2n(word[i]) != n:
							raise AssertionError()
					except AssertionError as _:
						iconvertions.pop(j)

			
			if len(iconvertions) <= 1:
				break

		if not silent: print(f"{PREFIX} Removing too long words.")
		convertions = [words[ic]
			for ic in (tqdm(iconvertions) if not silent else iconvertions)
			if len(words[ic]) == len(num)
		]

		if not silent :
			print(
				f"{PREFIX} Found convertions for {num} ->",
				f"({len(convertions)}){convertions}"
			)
		return convertions


	def _allowedNumbers() -> tuple[str]:
		"""Returns a tuple of allowed numbers in the T9 method."""
		return tuple(set(T9.CHARS_TO_LETTERS))

if __name__ == "__main__":
	# CORRECTION
	print("--- CORRECTION ---")

	fruits = ["pome", "banone", "pasteque", "mirtille", "frase"]
	sentence = (
		"La frase est plus roge qu la pome mais plus potit que la patèque"
	)

	if len(input(
		"Should I correct words and a sentence ? (Nothing for no) "
	)) > 0:
		for i, fruit in enumerate(tqdm(fruits)):
			fruits[i] = correctWord(fruit)
			pass

		for w in tqdm(sentence.split()):
			sentence = sentence.replace(w, correctWord(w.lower()), 1)
		
		print(fruits, sentence, sep="\n")

	# T9
	print("--- T9 ---")

	word, encrypted = "non", ""
	for c in word:
		encrypted += T9.c2n(c)

	print(f"{word} -> {encrypted}")
	print(f"{encrypted} -> {T9.n2ws(encrypted, WORDS)}")
	T9.n2ws_nByn(encrypted, WORDS, False)