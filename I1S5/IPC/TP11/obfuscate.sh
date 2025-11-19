#!/bin/bash

obfuscate(){
	cat "$1"/"$1".c \
	| sed 's/\/\*.*/\/* ... *\//g' \
	| sed 's/\/\/.*/\/\/ .../g' \
	| grep -Ev '^ \*.*$' \
	> "$1".c
}

obfuscate battleship
obfuscate cocktail