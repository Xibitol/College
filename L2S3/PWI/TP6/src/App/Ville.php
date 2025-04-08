<?php

namespace L2S3PWI\App;

class Ville{

	private string $nom;

	public function __construct(string $nom){
		$this->nom = $nom;
	}

	// GETTERS
	public function getNom(): string{ return $this->nom; }

	// FUNCTIONS
	public function __toString(): string{
		return sprintf("%s{nom=%s}",
			Ville::class, $this->getNom()
		);
	}
}