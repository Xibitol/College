#!/bin/bash

folder=$1

if [[ ! -d $folder ]]; then
	echo "Folder doesn't exist!" 1>&2
	exit 3
fi

shopt -s nullglob
for f in "$1"/*.pdf; do
	if [[ ! $(file -bi "$f") =~ application/pdf* ]]; then
		printf "\033[31m%s: not PDF, potentially corrupted!\033[0m\n" "$f"
	else
		echo "$f: fine."
	fi
done