<?php

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