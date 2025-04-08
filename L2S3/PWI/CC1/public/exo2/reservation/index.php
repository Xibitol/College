<?php
	require("../include/connexion.php");
	require("../include/formUtils.php");

	if(!isset($_GET["id_cours"])){
		header("Location: /exo2");
		exit;
	}

	$dbConnection = connectDB();

	const REQUIRED_FIELDS = ["nom", "id_personne", "id_cours"];

	// FORM PROCESSING
	if(isset($_POST["submit"])){
		$missingFields = array_values(array_filter(
			REQUIRED_FIELDS,
			function($field){
				$v = $_POST[$field] ?? $_FILES[$field] ?? null;

				return !(match(gettype($v)){
					"string" => strlen($v) > 0,
					"array" => sizeof($v) > 0,
					default => isset($v)
				});
			}
		));

		if(sizeof($missingFields) === 0){
			$user = null;
			$reserved = false;

			// Check if user exist
			try{
				$stmt = $dbConnection->prepare(
					"SELECT * FROM personne WHERE nom = :nom AND id_personne = :id_personne;"
				);
				$stmt->execute([
					"id_personne" => $_POST["id_personne"],
					"nom" => $_POST["nom"]
				]);
				$user = $stmt->fetch();

				if($user === false) $user = null;
			}catch(Exception $error){}

			// Check if user already reserved
			try{
				$stmt = $dbConnection->prepare(
					"SELECT 1 FROM participe WHERE id_personne = :id_personne AND id_cours = :id_cours;"
				);
				$stmt->execute([
					"id_personne" => $_POST["id_personne"],
					"id_cours" => $_POST["id_cours"]
				]);
				$reserved = $stmt->fetch() !== false;
			}catch(Exception $error){}

			// If yes and if can still book a session, push new reservation
			if(isset($user) && $user["nbSeance"] > 0 && !$reserved){
				try{
					$stmt = $dbConnection->prepare(
						"INSERT INTO participe(id_cours, id_personne) VALUES (:id_cours, :id_personne);"
					);
					$stmt->execute([
						"id_cours" => $_POST["id_cours"],
						"id_personne" => $_POST["id_personne"]
					]);

					$stmt = $dbConnection->prepare(
						"UPDATE personne SET nbSeance = :nbSeance WHERE id_personne = :id_personne"
					);
					$stmt->execute([
						"id_personne" => $_POST["id_personne"],
						"nbSeance" => $user["nbSeance"] - 1
					]);
				}catch(Exception $error){}
			}
		}
	}
?>
<!DOCTYPE html>
<html>
    <head>
		<title>Les mills</title>
		<meta charset="UTF-8">
		<meta name="description" content="">
		<meta name="keywords" content="">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="/exo2/css/reset.css">
        <link rel="stylesheet" href="/exo2/css/styles.css">
    </head>
    <body>
		<?php require("../include/header.php"); ?>

        <form method="POST" enctype="multipart/form-data">
			<div>
				<label for="nom">Nom*:</label>
				<input id="nom" type="text"
					name="nom"
					required
				>
			</div>

			<div>
				<label for="id_personne">Identifiant*:</label>
				<input id="id_personne" type="text"
					name="id_personne"
					required
				>
			</div>

			<input id="id_cours" type="text"
				name="id_cours"
				value="<?= $_GET["id_cours"]; ?>"
				required
				hidden
			>

			<input type="submit" name="submit" value="Réserver">
			<?php if(isset($missingFields) && sizeof($missingFields) > 0): ?>
				<p style="color: red;">
					Le champ <?= $missingFields[0] ?> est manquant.
				</p>
			<?php elseif(isset($missingFields) && isset($error)): ?>
				<p style="color: red;">
					Une erreur est survenue lors de l'envoi du formulaire.
				</p>
			<?php elseif(isset($missingFields) && !isset($user)): ?>
				<p style="color: red;">
					Nom ou identifiant d'utilisateur invalide.
				</p>
			<?php elseif(isset($missingFields) && $reserved): ?>
				<p style="color: red;">
					Vous avez déjà réservé cette séance.
				</p>
			<?php else: ?>
				<p>Réservation effectuée.</p>
			<?php endif; ?>

			<p>*: Obligatoire.</p>
		</form>

		<?php require("../include/footer.php"); ?>
    </body>
</html>
