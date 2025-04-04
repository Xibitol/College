/**
 * @file list.inc.h
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#pragma once

#include <stdlib.h>

#include "list.h"

struct _List{
	const void* data;
	List* next;
};