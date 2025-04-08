<?php
	use L2S3PWI\App\Entity\Station;

	/** @var Station */
	$station = $model;
?>
<!DOCTYPE html>
<html lang="fr">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Station <?= $station->getNom(); ?> - CC2 - PWI</title>

	<link rel="stylesheet" href="/css/styles.css">
</head>
<body>
	<main>
		<h1 class="titre"><?= $station->getNom(); ?></h1>
		<figure>
			<img
				src="/<?= $station->getImage(); ?>"
				alt="Image de la station <?= $station->getNom(); ?>."
			>
			<figcaption>
				<p>Nombre de vélos max = <?= $station->getNbVeloMax(); ?></p>
				<p>Nombre de vélos présents = <?= $station->getNbVeloPresent(); ?></p>
			</figcaption>
		</figure>
	</main>
</body>
</html>