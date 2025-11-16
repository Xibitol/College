#include "random.h"

#include <stdlib.h>
#include <time.h>

void init_rand(const char *envvar)
{
    const char *seed = getenv(envvar);
    srand(seed ? atoi(seed) : time(NULL));
}