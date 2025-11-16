#pragma once

#define RAND_BTW(_x, _y) \
  ((rand()%((_y) - (_x))) + (_x))

extern void init_rand(const char *envvar);