<?php

const PROJECT_PATH = "..";
const DEVELOPMENT_MODE = true;

// Adding a classes autoloader.
require PROJECT_PATH."/src/autoload.php";

spl_autoload_register(function(string $class){
	\SBPGames\Autoloader\loadClass($class, PROJECT_PATH, DEVELOPMENT_MODE);
});

echo "<pre>";
// ------
use L2S3PWI\App\Ville;
use L2S3PWI\App2\Ville as Ville2;

$city = new Ville("Bordeaux");
echo strval($city)."\n";
$city2 = new Ville2("Bordeaux", 12.5, new \DateTime("2024-12-03"));
echo strval($city2)."\n";

// -----
use L2S3PWI\Renter\Entity\Zoe;

echo Zoe::pub()."\n";

$zoe = new Zoe("rouge sang", "SL-137-OS");
echo strval($zoe)."\n";;
echo $zoe->polluer()."\n";
echo "Long: ".$zoe->getLongitude()."\n";
echo "Lat: ".$zoe->getLatitude()."\n";
echo "Charge: ".$zoe->getCharge()."\n";

$zoe->recharger();

echo "Charge: ".$zoe->getCharge()."\n";

// -----
use L2S3PWI\Renter\Entity\ParcZoe;

$parc = new ParcZoe();
echo "ParcZoe Zoes count: ".count($parc)."\n";
var_dump($parc->getLesZoe());

// -----
use L2S3PWI\Renter\Entity\JetSki;
use L2S3PWI\Renter\Entity\Loueur;

$js = new JetSki("Pimous");
var_dump($js);

$loueur = new Loueur([
	new JetSki("RedBull"),
	new JetSki("Lambda"),
	new JetSki("AquaSport2000")
]);
var_dump($loueur);

// ------
// II. Exercice ...

echo "</pre>";