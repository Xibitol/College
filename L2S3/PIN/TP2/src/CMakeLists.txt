cmake_minimum_required(VERSION 3.0)

project(Fracion C)

# OPTIONS
set(CMAKE_LIBRARY_PATH ${CMAKE_SOURCE_DIR}/../lib)
set(CMAKE_TEST_PATH ${CMAKE_SOURCE_DIR}/../test)

add_compile_options(-Wall -Wextra -Wpedantic)

# LIBRAIRIES
include_directories(${CMAKE_SOURCE_DIR}/../inc)

find_library(ARITHMETIC_LIB arithmetic)

add_library(fraction SHARED fraction.c fraction.h)
target_link_libraries(fraction ${ARITHMETIC_LIB})

# EXECUTABLES
add_executable(main main.c)
add_dependencies(main fraction)
target_link_libraries(main fraction)

# TESTS fraction
add_executable(test-fraction ${CMAKE_TEST_PATH}/test-fraction.c)
add_dependencies(test-fraction fraction)
target_link_libraries(test-fraction fraction)

enable_testing()
add_test(test-sgn ./test-fraction n)
add_test(test-reduce ./test-fraction r)

add_test(test-addition ./test-fraction a)
add_test(test-symmetric ./test-fraction y)
add_test(test-substraction ./test-fraction s)

add_test(test-multiplication ./test-fraction m)
add_test(test-inverse ./test-fraction i)
add_test(test-division ./test-fraction d)