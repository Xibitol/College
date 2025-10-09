#!/bin/bash

select key in "Salut" "Bonjour" "Bonsoir"; do
	case $key in
		"Salut")
			echo "Oh no..."
			break
		;;
		*)
			echo "..."
		;;
	esac
done