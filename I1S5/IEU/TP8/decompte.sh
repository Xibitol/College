#!/bin/bash

LANGUAGE="english"

tts(){
	echo "$1" | festival --tts --language $LANGUAGE
}

if [[ $# -lt 2 ]]; then
	echo "Usage: decompte.sh start delay" 1>&2
	exit 2
fi

start=$1

delay=$2
for ((i=start; i > 0; i--)); do
	echo "$i..."
	tts "$i" &
	sleep "$delay"
done
tts "You are now subject to die."
tts "Run..."

# i=$start
# while [[ $i -gt 0 ]]; do
# 	echo "$i..."
# 	sleep "$delay"

# 	i=$((i - 1))
# done