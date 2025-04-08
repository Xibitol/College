# TESTS fraction
set(fractionTests
	# Initialization
	test-init.c

	# Creation and Destruction
	test-fill.c
	test-mem.c

	# Getters
	test-getters.c

	# Cloning
	test-copy.c
	test-clone.c

	# Utils
	test-malloc.c
	test-sgn.c
	test-reduce.c

	# Arithmetic operations
	test-addition.c
	test-symmetric.c
	test-substraction.c

	test-multiplication.c
	test-inverse.c
	test-division.c

	# Streaming
	test-toString.c
	test-file.c
)
create_test_sourcelist(fractionTest test-fraction.c ${fractionTests})

foreach(fTest ${fractionTests})
	get_filename_component(name ${fTest} NAME_WE)
	add_test(NAME ${name}
		COMMAND ${VALGRIND} ${VALGRIND_ARGUMENTS} ./test-fraction ${name}
	)
endforeach()

# EXECUTABLES
add_executable(test-fraction ${fractionTest})
add_dependencies(test-fraction fraction)
target_link_libraries(test-fraction PRIVATE fraction)