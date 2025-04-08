<?php
	use L2S3PWI\App\Entity\Velo;
	use L2S3PWI\App\Entity\Station;

	/** @var Velo */
	$velo = $veloEntity;
	/** @var Station */
	$station = $stationEntity;
?>
<!DOCTYPE html>
<html lang="fr">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Ajouter un vélo - CC2 - PWI</title>

	<link rel="stylesheet" href="/css/styles.css">
</head>
<body>
	<main>
		<section>
			<?php if($error): ?>
				<h1 class="titre">
					Impossible d'ajouter ce vélo.
				</h1>
				<p>
					Une erreur est survenue; Le vélo ou la station pourrait ne pas exister.
				</p>
			<?php else: ?>
				<h1 class="titre">
					Vélo n°<?= $velo->getId(); ?> ajouté à la station n°<?= $velo->getStationId(); ?>
				</h1>
				<a href="/?action=unestation&id=<?= $velo->getStationId(); ?>">
					Station n°<?= $velo->getStationId(); ?>
				</a>
			<?php endif; ?>
			<a href="/">Accueil</a>
		</section>
	</main>
</body>
</html>