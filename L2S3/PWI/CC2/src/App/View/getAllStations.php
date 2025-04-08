<?php
	use L2S3PWI\App\Entity\Station;

	/** @var Station */
	$stations = $models;
?>
<!DOCTYPE html>
<html lang="fr">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Stations - CC2 - PWI</title>

	<link rel="stylesheet" href="/css/styles.css">
</head>
<body>
		<main>
		<h1>Liste de toutes les stations</h1>
		<section>
			<?php foreach($stations as $station): ?>
				<a href="/?action=unestation&id=<?= $station->getId(); ?>">
					<figure>
						<img
							src="/<?= $station->getImage(); ?>"
							alt="Image de la station <?= $station->getNom(); ?>."
						>
						<figcaption>
							<h2><?= $station->getNom(); ?></h2>
						</figcaption>
					</figure>
				</a>
			<?php endforeach; ?>
		</section>
	</main>
</body>
</html>