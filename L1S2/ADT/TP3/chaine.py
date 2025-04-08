def whereIs(where: str, what: str, nb: int = -1) -> list[int]:
	# FIXME: nb must be greater than or equal to -1
	i: int = 0
	ps: list[int] = []
	while nb != 0:
		if i + len(what) > len(where): nb = 0
		else:
			j = 0
			while j < len(what) and where[i + j] == what[j]:
				j += 1
			if j == len(what):
				nb -= 1
				ps.append(i)
			i += 1
	return ps

def whereIsFirst(where: str, what: str) -> int|None:
	return ps[0] if len(ps := whereIs(where, what, 1)) else None
	
def count(where: str, what: str) -> int:
	return len(whereIs(where, what))

def has(where: str, what: str) -> bool:
	return whereIsFirst(where, what) is not None

if __name__ == "__main__":
	import requests

	FILE = "alice.txt"

	# TODO: Don't do that if FILE exists and http req returns a 304 status code
	# Downloads the text from web
	url = "https://www.gutenberg.org/files/55456/55456-0.txt"
	with open(FILE, "wb") as webF:
		webF.write(requests.get(url).content)

	# Loads the text locally
	with open(FILE) as locF:
		text = str(locF.read())

	# TESTS
	print("---- BOOK ----")
	w = "[Illustration]"
	print(f"{has.__name__}(text, {w}) -> {has(text, w)}")

	i = whereIsFirst(text, w)
	text = text[i:]
	print(
		f"{whereIsFirst.__name__}(text, {w}) ->",
		f"{i} \"{text[:len(w) + 10]} ...\""
	)

	w = "Alice"
	print(f"{count.__name__}(text, {w}) -> {count(text, w)}")
	print(f"  | {text.count.__qualname__}({w}) -> {text.count(w)}")

	ps = whereIs(text, w)
	print(f"{whereIs.__name__}(text, {w}) -> [{", ".join(
		[str(p) for p in ps[:10]] + ["..."]
	)}]")

	rText, ps, nw, preSize = "", [*ps], "Pam Pam", 352
	for i in range(0, len(ps)):
		rText += text[0 if i == 0 else ps[i - 1] + len(w):ps[i]] + nw
	rText += text[ps[-1] + len(w):]
	print(f"replace(text, {w}, {nw}) -> \"{rText[:preSize]} ...\"")
	pyRText = text.replace(w, nw)
	print(f"  | {text.replace.__qualname__}({w}, {nw}) ->",
	   f"\"{pyRText[:preSize]} ...\""
	)
	print(f"Égal : {rText == pyRText}.")