<?php

namespace L2S3PWI\App\Controller;

use L2S3PWI\App\Config\Database;
use L2S3PWI\App\Model\VilleModel;

class ControllerVilleApi{

	public const VIEW_PATH_FORMAT = "../src/App/View/api/%s.php";

	private VilleModel $model;

	public function __construct(Database $connexion){
		$this->model = new VilleModel($connexion);
	}

	// GETTERS
	public static function getViewPath(string $name){
		return sprintf(ControllerVilleApi::VIEW_PATH_FORMAT, $name);
	}

	// FUNCTIONS
	public function getAllApi(): void{
		$villes = $this->model->findAll();

		$data = [
			"nhits" => count($villes),
			"format" => "json",
			"records" => array_map(function($v){
				return [
					"datasetid" => "villeVoyage",
					"fields" => [
						"nom" => $v->getNom(),
						"pop" => $v->getPopulation(),
						"pays" => "Sri lanka",
						"geo" => [
							$v->getLatitude(),
							$v->getLongitude()
						]
					]
				];
			}, $villes)
		];

		include(ControllerVilleApi::getViewPath("viewApi"));
	}
}