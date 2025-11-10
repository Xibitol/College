set(SUBPROJECT_NAME mem)
message(STATUS "Configuring ${SUBPROJECT_NAME} sub-project...")

# OUTPUTS
add_executable(enum "${SOURCE_DIR}/enum.c")
target_link_libraries(enum array)
set_target_properties(enum PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)