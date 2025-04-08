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
			<h1>Ajout d'un vélo</h1>
			<form method="post">
				<div>
					<label for="nom">Identifiant du vélo*:</label>
					<input id="nom" type="text" name="id" min="1" required>
				</div>
				<div>
					<label for="nom">Identifiant de la station*:</label>
					<input id="nom" type="text" name="stationId" min="1" required>
				</div>

				<div>
					<input type="submit" name="submit"
						min="1"
						value="Sauvegarder"
					>
				</div>

				<?php if(isset($error)): ?>
					<p><?= $error ?></p>
				<?php endif; ?>
			</form>
		</section>
	</main>
</body>
</html>