<?php

namespace L2S3PWI\Renter\Entity;

use L2S3PWI\Renter\Enum\TypeVoiture;
use L2S3PWI\Renter\Trait\Geolocalisation;
use L2S3PWI\Renter\Trait\Rechargeable;

class Zoe extends Voiture{
	use Geolocalisation, Rechargeable;

	private const TYPE_ENERGY = TypeVoiture::ELECTRIQUE;

	public function __construct(string $couleur, string $immatriculation){
		parent::__construct($couleur, Zoe::TYPE_ENERGY, $immatriculation);
	}

	// GETTERS
	public static function pub(): string{
		return "N'achetez pas Zoe, vous risqueriez d'être ravi.";
	}

	public function polluer(): string{ return "Je roule propre"; }

	// FUNCTIONS
	public function __toString(): string{
		return "Je suis une voiture discutable (Zoe) de type electrique immatriculée {$this->getImmatriculation()}.";
	}
}