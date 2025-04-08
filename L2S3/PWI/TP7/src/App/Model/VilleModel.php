<?php

namespace L2S3PWI\App\Model;

use L2S3PWI\App\Config\Database;
use L2S3PWI\App\Entity\Ville;

class VilleModel extends Model{

	public function __construct(Database $connexion){
		parent::__construct($connexion, "ville");
	}

	// FUNCTIONS
	/**
	 * @return Ville[]
	 */
	public function findAll(): array{
		return array_map(function($values){
			return new Ville($values);
		}, parent::find());
	}
	public function findOne(int $identifier): ?Ville{
		$ville = $this->find([
			"conditions" => [
				"id=" => $identifier
			]
		]);
		return count($ville) == 1 ? new Ville($ville[0]) : null;
	}
	/**
	 * @return Ville[]
	 */
	public function findByPaysId(int $pays_id): array{
		return array_map(function($values){
			return new Ville($values);
		}, $this->find([
			"conditions" => [
				"pays_id=" => $pays_id
			]
		]));
	}

	/**
	 * @param array<string, mixed> $values
	 */
	public function updateVille(Ville $ville): bool{
		if($ville->getIdentifier() === null){
			$id = $this->create($ville->toArray());

			var_dump($id);

			if(isset($id) && $id !== 0) $ville->setIdentifier($id);

			return $id !== 0;
		}else
			return $this->update($ville->toArray());
	}
}