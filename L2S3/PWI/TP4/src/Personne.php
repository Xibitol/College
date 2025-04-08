<?php

class Personne{

	private int $id;
	private string $nom;
	private string $prenom;

	public function __construct(array $data){
		$this->hydrate($data);
	}

	// GETTERS
	public function getID(): int{ return $this->id; }
	public function getNom(): string{ return $this->nom; }
	public function getPrenom(): string{ return $this->prenom; }

	// SETTERS
	public function setNom(string $nom): void{ $this->nom = $nom; }
	public function setPrenom(string $prenom): void{ $this->prenom = $prenom; }

	public function hydrate(array $data){
		$this->id = intval($data["id"]);
		$this->setNom($data["nom"]);
		$this->setPrenom($data["prenom"]);
	}

	// FUNCTIONS
	public function toArray(): array{
		return [
			"id" => $this->id,
			"nom" => $this->nom,
			"prenom" => $this->prenom
		];
	}
	public function __toString(): string{
		return sprintf("%s %s est d'identifiant %d.",
			$this->prenom, $this->nom, $this->id
		);
	}
}