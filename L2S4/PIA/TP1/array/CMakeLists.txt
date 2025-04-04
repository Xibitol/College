set(SUBPROJECT_NAME array)
message(STATUS "Configuring ${SUBPROJECT_NAME} sub-project...")

# OUTPUTS
file(GLOB LIBRARY_FILES CONFIGURE_DEPENDS "${SOURCE_DIR}/*.*")
add_library(lib SHARED ${LIBRARY_FILES})
set_target_properties(lib PROPERTIES
	OUTPUT_NAME array
	LIBRARY_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)

# TESTS
set(TEST_EXECUTABLE_NAME testArray)

file(GLOB ABSOLUTE_TEST_DIR "${TEST_DIR}")
file(GLOB_RECURSE TEST_FILES
	RELATIVE "${ABSOLUTE_TEST_DIR}/.."
	CONFIGURE_DEPENDS
	"${TEST_DIR}/*.c"
)
create_test_sourcelist(tests "${TEST_EXECUTABLE_NAME}.c" ${TEST_FILES})

add_executable(testLib ${tests})
add_dependencies(testLib lib)
target_link_libraries(testLib PRIVATE lib ${MATH_LIBRARY})
set_target_properties(testLib PROPERTIES
	OUTPUT_NAME ${TEST_EXECUTABLE_NAME}
	RUNTIME_OUTPUT_DIRECTORY ${TEST_DIR}
)

get_target_property(EXECUTABLE_OUTPUT_PATH testLib
	RUNTIME_OUTPUT_DIRECTORY
)
foreach(FILENAME ${TEST_FILES})
	string(REPLACE ".c" "" NAME ${FILENAME})
	string(REPLACE "${TEST_DIR}/" "" SHORT_NAME ${NAME})

	add_valgrind_test(${SUBPROJECT_NAME} ${SHORT_NAME}
		"${EXECUTABLE_OUTPUT_PATH}/${TEST_EXECUTABLE_NAME}" ${NAME}
	)
endforeach()