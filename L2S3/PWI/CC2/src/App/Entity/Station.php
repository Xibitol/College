<?php

namespace L2S3PWI\App\Entity;

use L2S3PWI\App\Trait\Geolocalisable;

class Station{
	use Geolocalisable;

	private const STATION_REPR_FORMAT =
		"Station{id=%d,nom=%s,nbPlaceDispo=%d}";

	private int $id;
	private string $nom;
	private int $nbVeloMax;
	private int $nbVeloPresent;
	private string $image;

	/** @var Velo[] */
	private array $listeVelosPresents = [];

	public function __construct(array $values){
		$this->hydrate($values);
	}

	// GETTERS
	public function getId(): int{ return $this->id; }
	public function getNom(): string{ return $this->nom; }
	public function getNbVeloMax(): int{ return $this->nbVeloMax; }
	public function getNbVeloPresent(): int{ return $this->nbVeloPresent; }
	public function getImage(): string{ return $this->image; }

	/**
	 * @return Velo[]
	 */
	public function getListVelos(): array{ return $this->listeVelosPresents; }

	public function getPlaceDispo(): int{
		return $this->getNbVeloMax() - $this->getNbVeloPresent();
	}

	// SETTERS
	public function setId(int $id): void{ $this->id = $id; }
	public function setNom(string $nom): void{ $this->nom = $nom; }
	public function setNbVeloMax(int $nb): void{ $this->nbVeloMax = $nb; }
	public function setNbVeloPresent(int $nb): void{
		$this->nbVeloPresent = $nb;
	}
	public function setImage(string $path): void{
		$this->image = $path;
	}

	public function addVelo(Velo $velo): void{
		if(in_array($velo, $this->getListVelos()))
			throw new \RuntimeException("Velo already present in the Station.");
		elseif($this->getPlaceDispo() <= 0)
			throw new \OutOfBoundsException("The Station is full.");

		array_push($this->listeVelosPresents, $velo);
		$velo->setStationId($this->getId());
		$this->nbVeloPresent++;
	}

	public function hydrate(array $values){
		$attrs = (new \ReflectionObject($this))->getProperties();

		foreach($attrs as $attr)
			if($attr->getName() !== "listeVelosPresents")
				$this->{sprintf("set%s", ucwords($attr->getName()))}(
					$values[$attr->getName()]
				);
	}

	// FUNCTIONS
	public function toArray(): array{
		$attrs = (new \ReflectionClass(Velo::class))->getAttributes();
		$values = [];

		foreach($attrs as $attr)
			if($attr->getName() !== "listeVelosPresents")
				$values[$attr->getName()] = call_user_func(
					sprintf("get%s()", ucwords($attr->getName()))
				);

		return $values;
	}
	public function __toString(): string{
		return sprintf(Station::STATION_REPR_FORMAT,
			$this->getId(),
			$this->getNom(),
			$this->getNbVeloMax() - $this->getNbVeloPresent()
		);
	}
}