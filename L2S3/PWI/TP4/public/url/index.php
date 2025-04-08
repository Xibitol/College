<?php
	$product = $_GET["produit"];
	$price = $_GET["prix"];

	if(!isset($product) || !isset($price)){
		header("Location: /");
		exit;
	}
?>
<!DOCTYPE html>
<html lang="fr">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Paramètres d'URL</title>
</head>
<body>
	<p>
		Affichage du produit <?= $product; ?> dont le prix est <?= $price; ?>.
	</p>
</body>
</html>