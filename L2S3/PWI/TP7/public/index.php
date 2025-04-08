<?php

// ROUTING API
if(isset($_GET["controller"]) && strcmp($_GET["controller"], "api") == 0){
	header("Location: /indexApi.php");
	exit;
}else unset($_GET["controller"]);

// -------
const PROJECT_PATH = "..";
const DEVELOPMENT_MODE = true;

// Adding a classes autoloader.
require PROJECT_PATH."/src/autoload.php";

spl_autoload_register(function(string $class){
	\SBPGames\Autoloader\loadClass($class, PROJECT_PATH, DEVELOPMENT_MODE);
});

// -------
use L2S3PWI\App\Entity\Ville;

$ville = new Ville([
	"id" => 31,
	"nom" => "Fred Land",
	"pop" => 310120,
	"long" => 1,
	"lat" => 20,
	"pays_id" => 33000
]);
assert($ville->getIdentifier() == 31);
assert($ville->getNom() == "Fred Land");
assert($ville->getPopulation() == 310120);
assert($ville->getLongitude() == 1);
assert($ville->getLatitude() == 20);
assert($ville->getPaysId() == 33000);
assert(strcmp(
	strval($ville),
	"Ville{id=31,nom=Fred Land,pop=310120,long=1,lat=20,pays_id=33000}"
) == 0);

// ------
use L2S3PWI\App\Config\Database;
use L2S3PWI\App\Controller\ControllerVille;

$db = new Database();
$controller = new ControllerVille($db);

// WORKAROUND FOR VSCODE
unset($_GET["vscodeBrowserReqId"]);

?>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>TP7 - PWI</title>
</head>
<body>
	<?php if(isset($_GET["id"]) && intval($_GET["id"]) !== 0): ?>
		<h2>Ville n°<?= $_GET["id"]; ?>:</h2>
		<?= $controller->getOne($_GET["id"]); ?>
	<?php elseif(isset($_GET["pays"])): ?>
		<h2>Ville du pays <?= $_GET["pays"]; ?>:</h2>
		<?= $controller->getAllPays($_GET["pays"]); ?>
	<?php elseif(isset($_GET["action"])
		&& strcmp($_GET["action"], "save") == 0
	): ?>
		<h2>Ajout d'une ville</h2>
		<?= $controller->postVille($_POST); ?>
	<?php elseif(count($_GET) === 0): ?>
		<h2>Villes:</h2>
		<?= $controller->getAll(); ?>
	<?php else: ?>
		<p style="color:red">
			Valeurs de recherche incorrects ou résultat introuvable.
		</p>
	<?php endif; ?>
</body>
</html>