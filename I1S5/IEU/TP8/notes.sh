#!/bin/bash

OLDIFS=$IFS

out=$1
if [[ ! -f $1 || ! -w $1 ]]; then
	echo "notes: $1 doesn't exist or isn't readable;"
	exit 3 
fi

echo "Type name:grade; use ^D (Ctrl-D) to stop."
IFS=:
while read -r nom note; do
	if [[ -z $nom || -z $note || ! $note =~ ^-?[0-9]+$ ]]; then
		continue
	fi

	mention="Ajourné"
	if [[ note -ge 16 ]]; then mention="Très bien"
	elif [[ note -ge 14 ]]; then mention="Bien"
	elif [[ note -ge 12 ]]; then mention="Assez Bien"
	elif [[ note -ge 10 ]]; then mention="Passable"
	fi

	printf "\"%s\",%s,\"%s\"\n" "$nom" "$note" "$mention" >> "$out"
done

IFS=$OLDIFS