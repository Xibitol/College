<?php

namespace L2S3PWI\Renter\Interface;

interface Vehicule{

	// GETTERS
	public function rouler(): bool;
	public function naviguer(): bool;
	public function voler(): bool;
}