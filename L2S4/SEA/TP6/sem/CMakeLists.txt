set(SUBPROJECT_NAME sem)
message(STATUS "Configuring ${SUBPROJECT_NAME} sub-project...")

# OUTPUTS
add_executable(dentiste0 "${SOURCE_DIR}/dentiste0.c")
target_link_libraries(dentiste0 pthread)
set_target_properties(dentiste0 PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)

add_executable(dentiste1 "${SOURCE_DIR}/dentiste1.c")
target_link_libraries(dentiste1 pthread)
set_target_properties(dentiste1 PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)

add_executable(mt_mergesort "${SOURCE_DIR}/mt_mergesort.c")
target_link_libraries(mt_mergesort pthread)
set_target_properties(mt_mergesort PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)