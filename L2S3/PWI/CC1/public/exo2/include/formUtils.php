<?php

/**
 * @param string $name 
 * @return string 
 *
 * @author Xibitol <xibitol@pimous.dev>
 */
function getCourseImgPath(string $name, string $path = ""): string{
	return sprintf("%simg/%s.jpg",
		$path, str_replace(" ", "+", strtolower($name))
	);
}