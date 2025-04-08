#!/bin/bash
if [ $# = 0 ]
then
echo init.sh : aucun argument
exit 2
fi
for NOM
do
mkdir $NOM
chmod 700 $NOM
cd $NOM
mkdir TP1 TP2 TP3
cd ..
cat lisez.moi>$NOM/$NOM.lisezmoi
done