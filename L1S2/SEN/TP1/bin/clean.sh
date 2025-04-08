#!/bin/bash

mkdir out 2>/dev/null
cp -r ~/Applications ~/Bin "$HOME/TP systeme" out
rm -r ~/Applications ~/Bin "$HOME/TP systeme"

mkdir out/tmp 2>/dev/null
cp /tmp/filetmp out/tmp
rm /tmp/filetmp