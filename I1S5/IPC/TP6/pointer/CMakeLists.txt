set(SUBPROJECT_NAME pointer)
message(STATUS "Configuring ${SUBPROJECT_NAME} sub-project...")

# OUTPUTS
add_executable(pointer "${SOURCE_DIR}/pointer.c")
target_link_libraries(pointer PUBLIC ${MATH_LIBRARY})
set_target_properties(pointer PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)