#!/bin/bash

if [[ ! -d out ]]; then
	mkdir out
fi
rm -fr out/*
javac -d out/ src/**/*
echo COMPILED.