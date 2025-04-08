<?php

namespace L2S3PWI\App\Controller;

use L2S3PWI\App\Config\Database;
use L2S3PWI\App\Model\StationModel;
use L2S3PWI\App\Model\VeloModel;

class VeloController{

	public const VIEW_PATH_FORMAT = "../src/App/View/%s.php";

	private VeloModel $model;
	private StationModel $stationModel;

	public function __construct(Database $connexion){
		$this->model = new VeloModel($connexion);
		$this->stationModel = new StationModel($connexion);
	}

	// GETTERS
	public static function getViewPath(string $name){
		return sprintf(VeloController::VIEW_PATH_FORMAT, $name);
	}

	// FUNCTIONS
	public function addVelo(array $values): void{
		if(count($values) === 0){
			include(VeloController::getViewPath("putVeloForm"));
			return;
		}elseif(!preg_match("/-?\d+/", $values["id"])){
			$error = "Valeur de l'identifiant du vélo invalide.";
			include(VeloController::getViewPath("putVeloForm"));
			return;
		}elseif(!preg_match("/-?\d+/", $values["stationId"])){
			$error = "Valeur de l'identifiant de la station invalide.";
			include(VeloController::getViewPath("putVeloForm"));
			return;
		}
		$identifier = intval($values["id"]);
		$stationId = intval($values["stationId"]);

		$veloEntity = $this->model->findOne($values["id"]);
		$stationEntity = $this->stationModel->findOne($values["stationId"]);

		$stationEntity->addVelo($veloEntity);
		$error = !$this->model->updateVelo($veloEntity);

		include(VeloController::getViewPath("putVelo"));
	}
}