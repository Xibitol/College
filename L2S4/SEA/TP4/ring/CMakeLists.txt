set(SUBPROJECT_NAME ring)
message(STATUS "Configuring ${SUBPROJECT_NAME} sub-project...")

# OUTPUTS
add_executable(ring "${SOURCE_DIR}/ring.c")
set_target_properties(ring PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)