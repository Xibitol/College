"""
"""
__author__ = "Xibitol"

from typing import Collection

# Exercise 1
def f2c(n: float) -> float:
	return (n + 32)/1.8
def c2f(n: float) -> float:
	return n*1.8 + 32

# Exercise 2
def fiboIter(n: int) -> int:
	numbers = [0, 1]

	i = len(numbers)
	while len(numbers) <= n:
		numbers.append(numbers[i - 1] + numbers[i - 2])
		i += 1

	return numbers[n]

def fiboRec(n: int) -> int:
	return fiboRec(n - 1) + fiboRec(n - 2) if n > 1 else n

# Exercise 3
import inflect
import pyttsx3

TTDC_INTRO_SENTENCE_FORMAT = "On the {} day of Christmas my true love sent to me,\n"
TTDC_PRESENT_FIRST_FORMAT = "{} {}.\n"
TTDC_PRESENT_LINE_FORMAT = "{} {},\n"
TTDC_PRESENT_END_FORMAT = "And {} {}.\n"

inflectEngine = inflect.engine()
ttsEngine = pyttsx3.init()

def singTTDC(presents: Collection[str], sing: bool = False) -> str:
	lyrics = ""

	for d in range(len(presents)):

		lyrics += TTDC_INTRO_SENTENCE_FORMAT.format(
			inflectEngine.ordinal(d + 1)
		)

		for i, p in reversed(tuple(enumerate(presents[:d + 1]))):
			if i > d: break
			day = inflectEngine.number_to_words(i + 1)

			if d == 0:
				lyrics += TTDC_PRESENT_FIRST_FORMAT.format(day.capitalize(), p)
			elif i == 0:
				lyrics += TTDC_PRESENT_END_FORMAT.format(day, p)
			else:
				lyrics += TTDC_PRESENT_LINE_FORMAT.format(day.capitalize(), p)

	if sing:
		print("Listen...")
		ttsEngine.say(lyrics.replace("\n", ""))
		ttsEngine.startLoop()
		ttsEngine.stop()

	return lyrics