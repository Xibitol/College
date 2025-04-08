<?php

namespace L2S3PWI\App2;

class Ville{

	private string $nom;
	private float $population;
	private \DateTime $census;

	public function __construct(string $nom, float $pop, \DateTime $census){
		$this->nom = $nom;
		$this->population = $pop;
		$this->census = $census;
	}

	// GETTERS
	public function getNom(): string{ return $this->nom; }
	public function getPopulation(): float{ return $this->population; }
	public function getCensus(): \DateTime{ return $this->census; }

	// FUNCTIONS
	public function __toString(): string{
		return sprintf("%s{nom=%s,population=%d,census=%s}",
			Ville::class,
			$this->getNom(),
			$this->getPopulation(),
			$this->getCensus()->format("c")
		);
	}
}