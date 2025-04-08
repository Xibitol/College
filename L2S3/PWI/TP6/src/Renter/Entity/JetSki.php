<?php

namespace L2S3PWI\Renter\Entity;

use L2S3PWI\Renter\Interface\Vehicule;

class JetSki implements Vehicule{

	private string $marque;

	public function __construct(string $marque){
		$this->marque = $marque;
	}
	// GETTERS
	public function getMarque(): string{ return $this->marque; }

	public function rouler(): bool{ return false; }
	public function naviguer(): bool{ return true; }
	public function voler(): bool{ return false; }

}