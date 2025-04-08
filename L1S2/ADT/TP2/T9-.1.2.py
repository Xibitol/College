WORDS = []
with open("data/dico.txt") as f:
	WORDS = f.read().splitlines()

CHARS_OFFSET = 97
CHARS_TO_LETTERS = (
	# abc     def     ghi     jkl     mno     pqrs    tuv     wxyz
	"2"*3 + "3"*3 + "4"*3 + "5"*3 + "6"*3 + "7"*4 + "8"*3 + "9"*4
)

def _allowedNumbers() -> tuple[str]:
	"""Returns a tuple of allowed numbers in the T9 method."""
	return tuple(set(CHARS_TO_LETTERS))

def c2n(char: str) -> str:
		"""Converts char into a number with the T9 method.
		@param char A character.
		"""
		assert len(char) == 1, "char must be just a character."

		code = ord(char) - CHARS_OFFSET
		assert code >= 0 and code < len(CHARS_TO_LETTERS), (
			"char must be in the ascii alphabet."
		)

		return CHARS_TO_LETTERS[code]

def n2cs(num: str) -> list[str]:
	"""Gives possible convertions of num into a character with the T9
	method.
	@param num A number allowed in the T9 convertion table (Usually in
	23456789).
	"""
	nums = _allowedNumbers()
	assert len(num) == 1 and num in nums, (
		f"num must be a number in {nums}"
	)

	return [chr(CHARS_OFFSET + i)
		for i, n in enumerate(CHARS_TO_LETTERS)
		if n == num
	]

def n2ws(num: str, words: list[str]) -> list[str]:
	"""Gives possible convertions of num into a word from a given dataset of
	words with the T9 method.
	@param num A sequence of numbers.
	"""
	nums = _allowedNumbers()
	assert all([n in nums for n in num]), (
		f"num must be a sequence of numbers in {nums}"
	)

	convertions = []
	for word in words:
		if len(word) == len(num):
			for i in range(len(word)):
				if c2n(word[i]) != num[i]:
					break
			else:
				convertions.append(word)

	return convertions

if __name__ == "__main__":
	print("--- T9 ---")

	word, encrypted = "non", ""
	for c in word:
		encrypted += c2n(c)

	print(f"{word} -> {encrypted}")
	print(f"{encrypted} -> {n2ws(encrypted, WORDS)}")