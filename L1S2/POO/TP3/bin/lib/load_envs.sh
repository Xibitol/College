#!/bin/bash
# ------------------------------------------------------------------------------
# Declare a procedure to load properties files and load a .env file. Before
# that, it loads a default.env file to define default values. After, it
# declares dynamic variables : $JAVA_VERSION_SHORT (First line of {@code
# java --version}).
# ------------------------------------------------------------------------------

# Load {@code file} as a definition of project properties. It trims and parses
# its content and declare each constants as global variables.
# @param file Properties file path (Usually with the extension .env).
# @param silent Boolean indicating if the procedure has to be silent. Defaults
# to false.
loadEnvs(){
	if [[ -f $1 ]]; then
		# SAVE EXTGLOB STATUS AND SET IT (See http://mywiki.wooledge.org/glob#extglob)
		shopt -q extglob; EXTGLOB_SETTED=$?
		((EXTGLOB_SETTED)) && shopt -s extglob

		while IFS="=" read -d ";" -r var value; do
			spaceTab=$'\t\v'

			var=${var##*([[:space:]])} # Start trim
			var=${var%%*(["$spaceTab"])} # End trim

			value=${value##*(["$spaceTab"])} # Start trim
			value=${value%%*(["$spaceTab"])} # End trim

			if [[ $var = +([[:upper:]_-]) ]]; then
				if [[ $value = *([!$'\r\n\v\f']) ]]; then
					if [[ $2 = false || -z $2 ]]; then
						declare -grx "$var=$value"
						echo "] LOADED ENV. VAR $var WITH THE VALUE \"$value\"."
					else
						declare -grx "$var=$value" 2>/dev/null
					fi
				elif [[ $2 = false || -z $2 ]]; then
					echo "] INVALID ENV. VAR $var VALUE \"$value\"."
				fi
			elif [[ $2 = false || -z $2 ]]; then
				echo "] INVALID ENV. VAR \"$var\"."
			fi
		done < "$1"

		# RESET EXTGLOB
		((EXTGLOB_SETTED)) && shopt -u extglob
	fi
}

loadEnvs .env
loadEnvs ./bin/etc/default.env true

# DYNAMIC VARIABLES
javaVer="$("./bin/jdk-10.0.2/bin/java" --version)"
declare -rx "JAVA_VERSION_SHORT=${javaVer%%$'\n'*}"