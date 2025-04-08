#!/bin/bash
# ------------------------------------------------------------------------------
# Gives some utilities about bash arrays.
# ------------------------------------------------------------------------------

# Join each elements of an {@code array} separated by a string.
# @param 1 String that separates each elements.
# @param 2-n Elements to join.
arrayJoin(){
	sep=$1
	str=$2
	if shift 2; then
		str=$str$(printf %s "${@/#/"$sep"}")
	fi
	echo "$str"
}