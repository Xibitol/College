<!DOCTYPE html>
<html lang="fr">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Supression station n°<?= $identifier; ?> - CC2 - PWI</title>

	<link rel="stylesheet" href="/css/styles.css">
</head>
<body>
	<main>
		<section>
			<?php if($error): ?>
				<h1 class="titre">
					Station n°<?= $identifier; ?> n'a pas pu être supprimée
				</h1>
				<p>
					Une erreur est survenue; La station pourrait ne pas exister.
				</p>
			<?php else: ?>
				<h1 class="titre">
					Station n°<?= $identifier; ?> a été supprimée...
				</h1>
			<?php endif; ?>
			<a href="/">Accueil</a>
		</section>
	</main>
</body>
</html>