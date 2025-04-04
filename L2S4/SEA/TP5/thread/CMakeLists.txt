set(SUBPROJECT_NAME thread)
message(STATUS "Configuring ${SUBPROJECT_NAME} sub-project...")

# OUTPUTS
add_executable(n_threads "${SOURCE_DIR}/n_threads.c")
target_link_libraries(n_threads pthread)
set_target_properties(n_threads PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)
add_executable(threads_100 "${SOURCE_DIR}/threads_100.c")
target_link_libraries(threads_100 pthread)
set_target_properties(threads_100 PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)
add_executable(threads_pi "${SOURCE_DIR}/threads_pi.c")
target_link_libraries(threads_pi pthread m)
set_target_properties(threads_pi PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)