import typing

T = typing.TypeVar('T', int, str)

def longss3(sequence: typing.Sequence[T]) -> dict[T, tuple[int, int]]:
	""""""
	longests: dict[T,  tuple[int, int]] = {}
	last: tuple[int, int] = (0, 1)

	for i in range(1, len(sequence) + 1):
		lastElement = sequence[last[0]]

		if i < len(sequence) and sequence[i] == lastElement:
			last = (last[0], last[1] + 1)
		else:
			if (
				lastElement not in longests.keys() or
				last[1] > longests[lastElement][1]
			):
				longests[lastElement] = last
			last = (i, 1) # At this point, we are with a new element.

	return longests

def longss1(sequence: typing.Sequence[T], x: T) -> tuple[int, int] | None:
	return longss3(sequence)[x] if x in sequence else None

def longss2(sequence: typing.Sequence[T]) -> tuple[T, int, int]:
	longest = max(longss3(sequence).items(), key=lambda longss: longss[1][1])
	return (longest[0], longest[1][0], longest[1][1])

if __name__ == "__main__":
	print("('a') ->", longss1("aabbbccccaaaca", "a"))
	print("('a') ->", longss1("aabbbcccaaaaaa", "a"))
	print("(4) ->", longss1([1, 2, 3, 3, 3, 2, 4, 4, 4, 4, 3], 4))

	print("() ->", longss2("aabbbccccaaaca"))
	print("() ->", longss2("aabbbccccaaacaaaaaaaa"))

	print("() ->", longss3("aabbbccccaaaca"))