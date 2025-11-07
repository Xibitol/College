set(SUBPROJECT_NAME struct)
message(STATUS "Configuring ${SUBPROJECT_NAME} sub-project...")

# OUTPUTS
add_executable(struct "${SOURCE_DIR}/struct.c")
target_link_libraries(struct PUBLIC ${MATH_LIBRARY})
set_target_properties(struct PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)