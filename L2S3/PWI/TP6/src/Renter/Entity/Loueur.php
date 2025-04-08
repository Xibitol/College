<?php

namespace L2S3PWI\Renter\Entity;

class Loueur implements \Countable{

	/** @var array<Vehicule> */
	private array $vehicules = [];

	public function __construct(array $vehicules){
		$this->vehicules = array_merge($this->vehicules, $vehicules);
	}

	// GETTERS
	public function count(): int{ return count($this->vehicules); }
}