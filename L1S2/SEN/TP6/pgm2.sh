#!/bin/bash
if [ -f .index ]
then
	INDEX=$(cat .index)
else
	INDEX=3
fi
echo INDEX = $INDEX
if [ $INDEX != 5 ]
then
	$1
	INDEX=$(($INDEX+1))

else
	echo STOP : commande interdite

fi
echo $INDEX>.index