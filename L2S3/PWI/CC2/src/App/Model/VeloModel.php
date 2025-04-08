<?php

namespace L2S3PWI\App\Model;

use L2S3PWI\App\Config\Database;
use L2S3PWI\App\Entity\Velo;

class VeloModel extends Model{

	public function __construct(Database $connexion){
		parent::__construct($connexion, "velo");
	}

	// FUNCTIONS
	public function findOne(int $identifier): ?Velo{
		$velos = $this->find([
			"conditions" => [
				"id=" => $identifier
			]
		]);
		return count($velos) == 1 ? new Velo($velos[0]) : null;
	}

	public function updateVelo(Velo $velo): bool{
		return $this->update($velo->toArray());
	}
}