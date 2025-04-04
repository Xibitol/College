set(SUBPROJECT_NAME signal)
message(STATUS "Configuring ${SUBPROJECT_NAME} sub-project...")

# OUTPUTS
add_executable(signals "${SOURCE_DIR}/signals.c")
set_target_properties(signals PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)

add_executable(sig_chld "${SOURCE_DIR}/sig_chld.c")
set_target_properties(sig_chld PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)
add_executable(sig_chld1 "${SOURCE_DIR}/sig_chld1.c")
set_target_properties(sig_chld1 PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)
add_executable(sig_chld2 "${SOURCE_DIR}/sig_chld2.c")
set_target_properties(sig_chld2 PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)

add_executable(sig_hup "${SOURCE_DIR}/sig_hup.c")
set_target_properties(sig_hup PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)

add_executable(sync "${SOURCE_DIR}/sync.c")
set_target_properties(sync PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)