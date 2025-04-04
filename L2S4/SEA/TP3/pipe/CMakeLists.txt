set(SUBPROJECT_NAME pipe)
message(STATUS "Configuring ${SUBPROJECT_NAME} sub-project...")

# OUTPUTS
add_executable(pipe "${SOURCE_DIR}/pipe.c")
set_target_properties(pipe PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)
add_executable(pfe "${SOURCE_DIR}/pfe.c")
set_target_properties(pfe PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)