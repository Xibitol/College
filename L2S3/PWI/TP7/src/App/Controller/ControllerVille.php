<?php

namespace L2S3PWI\App\Controller;

use L2S3PWI\App\Config\Database;
use L2S3PWI\App\Entity\Ville;
use L2S3PWI\App\Model\VilleModel;

class ControllerVille{

	public const VIEW_PATH_FORMAT = "../src/App/View/%s.php";

	private VilleModel $model;

	public function __construct(Database $connexion){
		$this->model = new VilleModel($connexion);
	}

	// GETTERS
	public static function getViewPath(string $name){
		return sprintf(ControllerVille::VIEW_PATH_FORMAT, $name);
	}

	// FUNCTIONS
	public function getAll(): void{
		$models = $this->model->findAll();

		include(ControllerVille::getViewPath("getAllVilles"));
	}
	public function getOne(int $identifier): void{
		$model = $this->model->findOne($identifier);

		include(ControllerVille::getViewPath("getOneVille"));
	}
	public function getAllPays(int $paysId): void{
		$models = $this->model->findByPaysId($paysId);

		include(ControllerVille::getViewPath("getAllVilles"));
	}

	public function postVille(array $values): void{
		if(count($values) === 0){
			include(ControllerVille::getViewPath("postVilleForm"));
			return;
		}

		if(strlen($values["id"]) !== 0) $values["id"] = intval($values["id"]);
		else unset($values["id"]);
		$values["pop"] = intval($values["pop"]);
		$values["long"] = floatval($values["long"]);
		$values["lat"] = floatval($values["lat"]);
		$values["pays_id"] = intval($values["pays_id"]);

		$model = new Ville($values);
		$error = !$this->model->updateVille($model);

		include(ControllerVille::getViewPath("postVille"));
	}
}