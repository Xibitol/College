#!/bin/bash
if grep $1 /etc/passwd>/dev/null
then
	echo $?
    exit 2
else
	echo $?
    exit 4
fi
echo fin de programme