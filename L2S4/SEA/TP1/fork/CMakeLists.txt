set(SUBPROJECT_NAME fork)
message(STATUS "Configuring ${SUBPROJECT_NAME} sub-project...")

# OUTPUTS
add_executable(hello "${SOURCE_DIR}/hello.c")
set_target_properties(hello PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)
add_executable(compte_arg "${SOURCE_DIR}/compte_arg.c")
set_target_properties(compte_arg PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)

add_executable(fork "${SOURCE_DIR}/fork.c")
set_target_properties(fork PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)
add_executable(fork_loop "${SOURCE_DIR}/fork_loop.c")
set_target_properties(fork_loop PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)

add_executable(zombie1 "${SOURCE_DIR}/zombie1.c")
set_target_properties(zombie1 PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)
add_executable(zombie2 "${SOURCE_DIR}/zombie2.c")
set_target_properties(zombie2 PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)
add_executable(double_fork "${SOURCE_DIR}/double_fork.c")
set_target_properties(double_fork PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)

add_executable(execlp "${SOURCE_DIR}/execlp.c")
set_target_properties(execlp PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)
add_executable(execvp "${SOURCE_DIR}/execvp.c")
set_target_properties(execvp PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)
add_executable(execl "${SOURCE_DIR}/execl.c")
set_target_properties(execl PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)
add_executable(execv "${SOURCE_DIR}/execv.c")
set_target_properties(execv PROPERTIES
	RUNTIME_OUTPUT_DIRECTORY ${SOURCE_DIR}/prod
)