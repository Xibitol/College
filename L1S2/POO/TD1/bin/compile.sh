#!/bin/bash

if ! [[ -d out ]]; then
	mkdir out
fi
rm -r out/*
javac -d out/ src/*