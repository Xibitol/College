cmake_minimum_required(VERSION 3.0)

project(Arithmetic C)

# Librairies
add_library(arithmetic SHARED arithmetic.c arithmetic.h)

install(
	TARGETS arithmetic
	LIBRARY DESTINATION lib
	ARCHIVE DESTINATION lib
	RUNTIME DESTINATION bin
)

install(
	FILES arithmetic.h
	DESTINATION include
)

# Executables
add_executable(gcd-32-56 gcd-32-56.c)
add_dependencies(gcd-32-56 arithmetic)
target_link_libraries(gcd-32-56 arithmetic)

# Tests
add_executable(test-gcd test-gcd.c)
add_dependencies(test-gcd arithmetic)
target_link_libraries(test-gcd arithmetic)

enable_testing()
add_test(test-gcd ./test-gcd)