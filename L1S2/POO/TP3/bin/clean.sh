#!/bin/bash
# ------------------------------------------------------------------------------
# Removes all output .class files and folders.
# @param source Which source output to clean. Either "project" or "test".
# Defaults to "project".
# ------------------------------------------------------------------------------
shopt -s globstar

#shellcheck source=./lib/load_envs.sh
source ./bin/lib/load_envs.sh

# PARAMETERS
outputPath=$PROJECT_OUTPUT
if [[ $1 = "test" ]]; then
	outputPath=$PROJECT_TEST_OUTPUT
elif [[ $1 != "project" ]]; then
	echo "INVALID \"$1\" SOURCE; DEFAULTING TO \"project\""
fi

# DELETION
echo "REMOVING ..."
if [[ -d $outputPath && $(ls -A "$outputPath") ]]; then
	echo "FOUND $(find "$outputPath"/* | wc -l) file(s) and folder(s)."
	rm -frv "$outputPath"/**/*.class
	rm -dfr "${outputPath:?}"/**/*
	echo "REMOVED."
else
	echo "No \"$outputPath\" directory or is empty; Aborting."
fi