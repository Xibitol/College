<?php

namespace L2S3PWI\App\Model;

use L2S3PWI\App\Config\Database;
use L2S3PWI\App\Entity\Station;

class StationModel extends Model{

	public function __construct(Database $connexion){
		parent::__construct($connexion, "stationvelo");
	}

	// FUNCTIONS
	/**
	 * @return Station[]
	 */
	public function findAll(): array{
		return array_map(function($values){
			return new Station($values);
		}, parent::find());
	}
	public function findOne(int $identifier): ?Station{
		$stations = $this->find([
			"conditions" => [
				"id=" => $identifier
			]
		]);
		return count($stations) == 1 ? new Station($stations[0]) : null;
	}

	public function deleteStation(int $identifier): bool{
		return $this->delete($identifier);
	}
}