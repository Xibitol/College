<?php

namespace L2S3PWI\Renter\Entity;

use L2S3PWI\Renter\Enum\TypeVoiture;
use L2S3PWI\Renter\Interface\Vehicule;

abstract class Voiture implements Vehicule{

	private string $couleur;
	private TypeVoiture $type;
	private string $immatriculation;

	public function __construct(
		string $couleur, TypeVoiture $type, string $immatriculation
	){
		$this->couleur = $couleur;
		$this->type = $type;
		$this->immatriculation = $immatriculation;
	}

	// GETTERS
	public function getCouleur(): string{ return $this->couleur; }
	public function getType(): TypeVoiture{ return $this->type; }
	public function getImmatriculation(): string{
		return $this->immatriculation;
	}
	public function rouler(): bool{ return true; }
	public function naviguer(): bool{ return false; }
	public function voler(): bool{ return false; }

	public abstract function polluer(): string;

	// FUNCTIONS
	public function __toString(): string{
		return sprintf("Je suis une voiture; le savais-tu ?");
	}
}