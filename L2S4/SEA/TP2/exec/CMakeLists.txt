set(SUBPROJECT_NAME exec)
message(STATUS "Configuring ${SUBPROJECT_NAME} sub-project...")

# OUTPUTS
add_executable(forkexec "${SOURCE_DIR}/forkexec.c")
set_target_properties(forkexec PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)
add_executable(sync "${SOURCE_DIR}/sync.c")
set_target_properties(sync PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)
add_executable(async "${SOURCE_DIR}/async.c")
set_target_properties(async PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)

add_executable(pair "${SOURCE_DIR}/pair.c")
set_target_properties(pair PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)
add_executable(cp "${SOURCE_DIR}/cp.c")
set_target_properties(cp PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)

add_executable(mycat "${SOURCE_DIR}/mycat.c")
set_target_properties(mycat PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)

add_executable(ppfss "${SOURCE_DIR}/partage_pere_fils_std_systeme.c")
set_target_properties(ppfss PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)