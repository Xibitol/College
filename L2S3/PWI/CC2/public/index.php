<?php

const PROJECT_PATH = "..";
const DEVELOPMENT_MODE = true;

// Adding a classes autoloader.
require PROJECT_PATH."/src/autoload.php";

spl_autoload_register(function(string $class){
	\SBPGames\Autoloader\loadClass($class, PROJECT_PATH, DEVELOPMENT_MODE);
});

// -------
use L2S3PWI\App\Entity\Velo;

$velo = new Velo([
	"id" => 31,
	"dateMiseService" => "2020-01-31T10:14:00",
	"dateRevision" => "2024-01-31T09:02:14",
	"stationId" => 1
]);
assert($velo->getId() === 31);
assert($velo->getDateMiseService()->format("c") === "2020-01-31T10:14:00+00:00");
assert($velo->getDateRevision()->format("c") === "2024-01-31T09:02:14+00:00");
assert($velo->getStationId() === 1);
assert(strcmp(
	strval($velo),
	"Velo{id=31,dateMiseService=31-01-2020,dateRevision=31-01-2024,stationId=1}"
) == 0);

// -------
use L2S3PWI\App\Entity\Station;

$station = new Station([
	"id" => 31,
	"nom" => "Technoforum",
	"nbVeloMax" => 31,
	"nbVeloPresent" => 20,
	"longitude" => -1.153399,
	"latitude" => 46.158045,
	"image" => "img/technoforum.jpg"
]);
assert($station->getId() === 31);
assert($station->getNom() === "Technoforum");
assert($station->getNbVeloMax() === 31);
assert($station->getNbVeloPresent() === 20);
assert($station->getLongitude() === -1.153399);
assert($station->getLatitude() === 46.158045);
assert($station->getImage() === "img/technoforum.jpg");
assert($station->getPlaceDispo() === 11);
assert(strval($station) === "Station{id=31,nom=Technoforum,nbPlaceDispo=11}");

$station->addVelo($velo);
assert($station->getNbVeloPresent() === 21);
assert($station->getPlaceDispo() === 10);
try{
	$station->addVelo($velo);
	assert(false);
}catch(\Exception $_){}

// ------
use L2S3PWI\App\Config\Database;
use L2S3PWI\App\Controller\StationController;
use L2S3PWI\App\Controller\VeloController;

$db = new Database("pwi_cc2_yelo");
$stController = new StationController($db);
$veController = new VeloController($db);

if(!isset($_GET["action"])){
	header("Location: /?action=stations");
	exit;
}

switch($_GET["action"]){
	case "stations":
		$stController->getAll();
		break;
	case "unestation":
		if(isset($_GET["id"])) $stController->afficherUneStation($_GET["id"]);
		else{
			// TODO: Return 400
			echo "'id' query parameter is missing.";
		}
		break;
	case "deleteStation":
		if(isset($_GET["id"])) $stController->deleteStation($_GET["id"]);
		else{
			// TODO: Return 400
			echo "'id' query parameter is missing.";
		}
		break;
	case "addVelo":
		$veController->addVelo($_POST);
		break;
	default:
		include(StationController::getViewPath("notFound"));
}