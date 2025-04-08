#!/bin/bash

# shellcheck source=compile.sh
. bin/compile.sh
# shellcheck source=execute.sh
. bin/execute.sh "$1"