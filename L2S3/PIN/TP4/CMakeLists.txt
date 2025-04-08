cmake_minimum_required(VERSION 3.0)

project(Fracion C)

# OPTIONS
set(CMAKE_LIBRARY_PATH lib)

add_compile_options(-Wall -Wextra -Wpedantic)

set(CMAKE_CTEST_ARGUMENTS --output-on-failure)
set(VALGRIND_ARGUMENTS
	-s --leak-check=full --show-leak-kinds=all --error-exitcode=1
)
enable_testing()

# PROGRAMS
find_program(VALGRIND valgrind)

# LIBRAIRIES
include_directories(inc)

find_library(ARITHMETIC_LIB arithmetic REQUIRED)

# DIRECTORIES
add_subdirectory(src)
add_subdirectory(test)