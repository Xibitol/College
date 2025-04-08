from typing import Any, Callable
import turtle as t
import sys

GAME_TITLE = "DrawWithATurtle"

# The path to complete the maze is ADAGAAADAGADAAA
def displayMaze():
	t.bgpic("maze1.gif")
	t.penup()
	t.goto(-250,250)
	t.pencolor("white")
	t.pendown()

inputs: dict[str, tuple[str, str | Callable[[], None], tuple[Any] | None, str]] = {
	"a": (".", "forward", (100,), "moving"),
	"r": (".", "backward", (100,), "moving"),
	"g": (".", "left", (90,), "rotating"),
	"d": (".", "right", (90,), "rotating"),
	"m": ("Display a maze", displayMaze, (), "displaying"),
	"q": ("Quit", lambda: sys.exit(), (), "goodbye")
}
texts = {
	"welcome": "Welcome to {title} !",
	"commands": "Commands :",
	"commandLine": "{key} -> {action}.",
	"moving": "The turtle moved {amount} pixels {action}.",
	"rotating": "The turtle rotated of {amount} degrees on the {action}.",
	"displaying": "Drawing a maze.",
	"unexist": "The turtle wants to say you \"What do you mean with '{key}' ?\".",
	"goodbye": "See you soon :)"
}

print(texts["welcome"].format(title=GAME_TITLE))
print(texts["commands"])
for k, (dn, cmd, _, __) in inputs.items():
	print(texts["commandLine"].format(
		key=k,
		action=cmd.capitalize() if dn == '.' else dn
	))

print("")
while True:
	t.update()

	inpt = input("Enter a command : ")
	for char in inpt:
		for k, (dn, cmd, args, textID) in inputs.items():
			if k == char.lower():
				if type(cmd) is str:
					eval(f"t.{cmd}({','.join([str(i) for i in args])})")
					print(texts[textID].format(
						amount=args[0],
						action=cmd if dn == '.' else dn
					))
				else:
					print(texts[textID])
					cmd(*args)

				break
		else:
			print(texts["unexist"].format(key=char))
			break

	if len(inpt) > 1:
		t.exitonclick()
		break

print("")