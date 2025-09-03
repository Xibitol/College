set(SUBPROJECT_NAME base)
message(STATUS "Configuring ${SUBPROJECT_NAME} sub-project...")

# OUTPUTS
add_executable(sum "${SOURCE_DIR}/sum.c")
set_target_properties(sum PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)

add_executable(tests "${SOURCE_DIR}/tests.c")
set_target_properties(tests PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)

add_executable(prime "${SOURCE_DIR}/prime.c")
set_target_properties(prime PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)

add_executable(primes "${SOURCE_DIR}/primes.c")
set_target_properties(primes PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)