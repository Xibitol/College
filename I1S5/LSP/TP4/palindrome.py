mot = 'kayak'

is_palindrome = mot[:len(mot)//2] == mot[:len(mot)//2:-1]

if is_palindrome:
	print(mot, 'est un palindrome')
else:
	print(mot, "n'est pas un palindrome")