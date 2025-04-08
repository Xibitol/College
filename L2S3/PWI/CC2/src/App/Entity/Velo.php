<?php

namespace L2S3PWI\App\Entity;

class Velo{

	private const VELO_REPR_FORMAT =
		"Velo{id=%d,dateMiseService=%s,dateRevision=%s,stationId=%d}";

	private int $id;
	private \DateTime $dateMiseService;
	private \DateTime $dateRevision;
	private int $stationId;

	public function __construct(array $values){
		$this->hydrate($values);
	}

	// GETTERS
	public function getId(): int{ return $this->id; }
	public function getDateMiseService(): \DateTime{
		return $this->dateMiseService;
	}
	public function getDateRevision(): \DateTime{ return $this->dateRevision; }
	public function getStationId(): int{ return $this->stationId; }

	// SETTERS
	public function setId(int $id): void{
		$this->id = $id;
	}
	public function setDateMiseService(string|\DateTime $dms): void{
		$this->dateMiseService = is_string($dms) ? new \DateTime($dms) : $dms;
	}
	public function setDateRevision(string|\DateTime $dr): void{
		$this->dateRevision = is_string($dr) ? new \DateTime($dr) : $dr;
	}
	public function setStationId(int $sId): void{ $this->stationId = $sId; }

	public function hydrate(array $values){
		$attrs = (new \ReflectionObject($this))->getProperties();

		foreach($attrs as $attr)
			$this->{sprintf("set%s", ucwords($attr->getName()))}(
				$values[$attr->getName()]
			);
	}

	// FUNCTIONS
	public function toArray(): array{
		return [
			"id" => $this->getId(),
			"dateMiseService" => $this->getDateMiseService()->format("o-m-d"),
			"dateRevision" => $this->getDateRevision()->format("o-m-d"),
			"stationId" => $this->getStationId()
		];
	}
	public function __toString(): string{
		return sprintf(Velo::VELO_REPR_FORMAT,
			$this->getId(),
			$this->getDateMiseService()->format("d-m-o"),
			$this->getDateRevision()->format("d-m-o"),
			$this->getStationId()
		);
	}
}