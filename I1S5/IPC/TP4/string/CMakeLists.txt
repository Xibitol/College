set(SUBPROJECT_NAME string)
message(STATUS "Configuring ${SUBPROJECT_NAME} sub-project...")

# OUTPUTS
add_executable(string "${SOURCE_DIR}/string.c")
target_link_libraries(string PUBLIC ${MATH_LIBRARY})
set_target_properties(string PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)