#!/bin/bash

questions=$(seq 1 8)

for i in $questions; do
	questionDir="Q$i"

	javac -d "$questionDir" "$questionDir"/*.java 2>/dev/null

	find "$questionDir" -name "Test*.java" \
		-exec java -cp "$questionDir" {} \; \
		2>/dev/null

	rm "$questionDir"/*.class
done