#!/bin/bash

for ((i=1; i < $#; i++)) do
	printf "%s " ${!i}
done
echo ""
echo "${@:2}"

echo "---"

for i in $(seq 1 $(($# - 1))); do
	echo "${!i}"
done
echo "${@:1:$(($# - 1))}"