set(SUBPROJECT_NAME array)
message(STATUS "Configuring ${SUBPROJECT_NAME} sub-project...")

# OUTPUTS
add_executable(array "${SOURCE_DIR}/array.c")
set_target_properties(array PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)