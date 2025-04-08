# LIBRAIRIES
add_library(fraction SHARED fraction.c)
target_link_libraries(fraction PUBLIC ${ARITHMETIC_LIB})

# EXECUTABLES
add_executable(main main.c)
add_dependencies(main fraction)
target_link_libraries(main PRIVATE fraction)