<?php

/**
 * @param string $name 
 * @return string 
 *
 * @author Xibitol <xibitol@pimous.dev>
 */
function getThumbnailPath(string $name): string{
	return sprintf("thumbnails/%s.jpg",
		str_replace(" ", "+", strtolower($name))
	);
}

/**
 * @param array<string, array<string, int|float>> $cities
 * @return float
 * 
 * @author Xibitol <xibitol@pimous.dev>
 */
function averagePop(array $cities): float{
	$avg = 0;

	foreach($cities as $city) $avg += $city["pop"];

	return round($avg/sizeof($cities));
}

/**
 * @param array<string, array<string, int|float>> $cities
 * @param string[] $path
 * @return float
 * 
 * @author Xibitol <xibitol@pimous.dev>
 */
function distance(array $cities, array $path): float{
	if(sizeof($path) <= 1) return 0;
	$dist = 0;

	for($i = 1; $i < sizeof($path); $i++){
		$from = $cities[$path[$i - 1]];
		$to = $cities[$path[$i]];

		$fromLat = deg2rad($from["lat"]);
		$fromLong = deg2rad($from["long"]);
		$toLat = deg2rad($to["lat"]);
		$toLong = deg2rad($to["long"]);

		$dist += 6371.0097714*acos(
			sin($fromLat)*sin($toLat)
			+ cos($fromLat)*cos($toLat)*cos($fromLong - $toLong)
		);
	}

	return round($dist, 2);
}