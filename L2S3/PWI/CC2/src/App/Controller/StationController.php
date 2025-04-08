<?php

namespace L2S3PWI\App\Controller;

use L2S3PWI\App\Config\Database;
use L2S3PWI\App\Model\StationModel;

class StationController{

	public const VIEW_PATH_FORMAT = "../src/App/View/%s.php";

	private StationModel $model;

	public function __construct(Database $connexion){
		$this->model = new StationModel($connexion);
	}

	// GETTERS
	public static function getViewPath(string $name){
		return sprintf(StationController::VIEW_PATH_FORMAT, $name);
	}

	// FUNCTIONS
	public function getAll(): void{
		$models = $this->model->findAll();

		include(StationController::getViewPath("getAllStations"));
	}
	public function afficherUneStation(mixed $identifier): void{
		if(is_string($identifier) && !preg_match("/-?\d+/", $identifier)){
			// TODO: Return 400
			echo "Invalid 'id' query parameter.";
			return;
		}
		$identifier = intval($identifier);

		$model = $this->model->findOne($identifier);

		if(isset($model))
			include(StationController::getViewPath("getUneStation"));
		else
			include(StationController::getViewPath("notFound"));
	}

	public function deleteStation(mixed $identifier): void{
		if(is_string($identifier) && !preg_match("/-?\d+/", $identifier)){
			// TODO: Return 400
			echo "Invalid 'id' query parameter.";
			return;
		}
		$identifier = intval($identifier);

		$error = !$this->model->deleteStation($identifier);

		include(StationController::getViewPath("deleteStation"));
	}
}