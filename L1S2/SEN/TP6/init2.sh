#!/bin/bash
if [ $# = 0 ]
then
echo init.sh : aucun argument
exit 2
fi
INDEX=$2
while [ $INDEX != 0 ]
do
mkdir $1$INDEX
chmod 700 $1$INDEX
cd $1$INDEX
mkdir TP1 TP2 TP3
cd ..
cp lisez.moi $1$INDEX/$1$INDEX.lisezmoi
INDEX=$(($INDEX-1))
done