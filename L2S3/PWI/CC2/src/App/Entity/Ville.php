<?php

namespace L2S3PWI\App\Entity;

use L2S3PWI\App\Trait\Geolocalisable;

class Ville{
	use Geolocalisable;

	private ?int $id = null;
	private string $nom = "";
	private int $population;
	private int $pays_id;

	public function __construct(array $values){
		$this->hydrate($values);
	}

	// GETTERS
	public function getIdentifier(): ?int{ return $this->id; }
	public function getNom(): string{ return $this->nom; }
	public function getPopulation(): int{ return $this->population; }
	public function getPaysId(): int{ return $this->pays_id; }

	// SETTERS
	public function setIdentifier(?int $id): void{ $this->id = $id; }
	public function setNom(string $nom): void{ $this->nom = $nom; }
	public function setPopulation(int $population): void{
		$this->population = $population;
	}
	public function setPaysId(int $pays_id): void{ $this->pays_id = $pays_id; }

	private function hydrate(array $values){
		$this->setIdentifier($values["id"] ?? null);
		$this->setNom($values["nom"]);
		$this->setPopulation($values["pop"]);
		$this->setLongitude($values["long"]);
		$this->setLatitude($values["lat"]);
		$this->setPaysId($values["pays_id"]);
	}

	// FUNCTIONS
	public function toArray(): array{
		$arr = [
			"id" => $this->getIdentifier(),
			"nom" => $this->getNom(),
			"pop" => $this->getPopulation(),
			"long" => $this->getLongitude(),
			"lat" => $this->getLatitude(),
			"pays_id" => $this->getPaysId()
		];

		if($this->getIdentifier() === null) unset($arr["id"]);

		return $arr;
	}
	public function __toString(): string{
		$values = $this->toArray();

		return sprintf("%s{%s}", Ville::class,
			implode(",",
				array_map(function($k, $v){
					return sprintf("%s=%s", $k, strval($v));
				}, array_keys($values), array_values($values))
			)
		);
	}
}