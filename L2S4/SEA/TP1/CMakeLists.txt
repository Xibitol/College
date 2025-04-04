cmake_minimum_required(VERSION 3.0)

project(SEA-TP1
	LANGUAGES C
)

# OPTIONS
set(CMAKE_LIBRARY_PATH lib)

file(GLOB SUBPROJECT_SOURCE_DIRS
	LIST_DIRECTORIES true
	RELATIVE ${CMAKE_SOURCE_DIR}
	${CMAKE_SOURCE_DIR}/*/src/
)
include_directories(inc ${SUBPROJECT_SOURCE_DIRS})

add_compile_options(-Wall -Wextra -Wpedantic)

enable_testing()
if(POLICY CMP0110)
	cmake_policy(SET CMP0110 NEW)
endif()
set(CMAKE_CTEST_ARGUMENTS --output-on-failure)
set(VALGRIND_ARGUMENTS
	-s --leak-check=full --show-leak-kinds=all --error-exitcode=1
	--track-origins=yes
)

set(CMAKE_BUILD_TYPE Debug)

# EXTERNAL PROGRAMS
find_program(VALGRIND_EXECUTABLE "valgrind")
message(STATUS "VALGRIND_EXECUTABLE=${VALGRIND_EXECUTABLE}")

find_program(DOXYGEN_EXECUTABLE "doxygen")
message(STATUS "DOXYGEN_EXECUTABLE=${DOXYGEN_EXECUTABLE}")

find_program(SPHINX_EXECUTABLE "sphinx-build")
message(STATUS "SPHINX_EXECUTABLE=${SPHINX_EXECUTABLE}")

# MACROS
macro(add_valgrind_test SUBPROJECT_NAME NAME EXECUTABLE)
	set(EXECUTABLE_PATH
		${CMAKE_CURRENT_BINARY_DIR}/${EXECUTABLE}
	)

	set(WORKING_TEST_DIR ${CMAKE_SOURCE_DIR}/tmp/${SUBPROJECT_NAME})
	make_directory(${WORKING_TEST_DIR})

	add_test(
		NAME "(${SUBPROJECT_NAME}) ${NAME}"
		COMMAND ${EXECUTABLE_PATH} ${ARGN}
		WORKING_DIRECTORY ${WORKING_TEST_DIR}
	)
	if(VALGRIND_EXECUTABLE)
		add_test(
			NAME "(${SUBPROJECT_NAME})[mem-check] ${NAME}"
			COMMAND ${VALGRIND_EXECUTABLE}
			${VALGRIND_ARGUMENTS} ${EXECUTABLE_PATH} ${ARGN}
			WORKING_DIRECTORY ${WORKING_TEST_DIR}
		)
	endif()
endmacro()

# PROJECTS
set(SOURCE_DIR src)
set(TEST_DIR test)

add_subdirectory(fork)