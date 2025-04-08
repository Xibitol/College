#!/bin/bash
# ------------------------------------------------------------------------------
# Executes .class files with the current JVM.
# @param source Which source to execute. Either "project" or "test". Defaults to
# "project".
# @throw 1 The output folder doesn't exist.
# @throw 2 The JVM returns error(s).
# ------------------------------------------------------------------------------
# TODO: Add jvm choice.

#shellcheck source=./lib/load_envs.sh
source ./bin/lib/load_envs.sh
source ./bin/lib/array.sh

# PARAMETERS
outputPath=$PROJECT_OUTPUT

mainClass=$MAIN_CLASS
if [[ $1 = "test" ]]; then
	outputPath=$PROJECT_TEST_OUTPUT
	classPaths+=("$PROJECT_OUTPUT")
	mainClass=$MAIN_TEST_CLASS
elif [[ $1 != "project" ]]; then
	echo "INVALID \"$1\" SOURCE; DEFAULTING TO \"project\""
fi


# EXECUTION
echo "EXECUTING WITH $JAVA_VERSION_SHORT ..."
if [[ -d $outputPath ]]; then
	# Args
	args="-cp $(arrayJoin ':' "$outputPath" "${classPaths[@]}") $mainClass"

	if eval "./bin/jdk-10.0.2/bin/java $args"; then
		echo "TERMINATED."
	else
		exit 2
	fi
else
	echo "No \"$outputPath\" directory; Cannot execute."
	exit 1
fi