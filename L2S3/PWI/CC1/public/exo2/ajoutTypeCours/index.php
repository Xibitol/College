<?php
	require("../include/connexion.php");
	require("../include/formUtils.php");
	$dbConnection = connectDB();

	const REQUIRED_FIELDS = ["nom", "description", "photo"];

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
			$alreadyExist = false;

			// Check if course "nom" already exist
			try{
				$stmt = $dbConnection->prepare(
					"SELECT 1 FROM typecours WHERE nom = :nom;"
				);
				$stmt->execute([
					"nom" => $_POST["nom"]
				]);

				$alreadyExist = $stmt->fetch() !== false;
			}catch(Exception $error){}

			// If not, push new course
			if(!$alreadyExist){
				try{
					$secureNom = htmlspecialchars($_POST["nom"]);
	
					$stmt = $dbConnection->prepare(
						"INSERT INTO typecours(nom, description, photo) VALUES (:nom, :description, :photo);"
					);
					$stmt->execute([
						"nom" => $_POST["nom"],
						"description" => $_POST["description"],
						"photo" => getCourseImgPath($secureNom)
					]);
	
					move_uploaded_file($_FILES["photo"]["tmp_name"],
						getCourseImgPath($secureNom, "../")
					);
	
					header("Location: /exo2");
					exit;
				}catch(Exception $error){}
			}
		}
	}
?>
<!DOCTYPE html>
<html lang="fr">
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

		<main>
			<form method="POST" enctype="multipart/form-data">
				<div>
					<label for="nom">Nom*:</label>
					<input id="nom" type="text"
						name="nom"
						required
					>
				</div>

				<div>
					<label for="description">Description*:</label>
					<input id="description" type="text"
						name="description"
						required
					>
				</div>

				<div>
					<label for="photo">Photo*:</label>
					<input id="photo" type="file"
						name="photo"
						required
					>
				</div>

				<input type="submit" name="submit" value="Ajouter">
				<?php if(isset($missingFields) && sizeof($missingFields) > 0): ?>
					<p style="color: red;">
						Le champ <?= $missingFields[0] ?> est manquant.
					</p>
				<?php elseif(isset($alreadyExist) && $alreadyExist): ?>
					<p style="color: red;">
						Un type de cours avec ce nom existe déjà.
					</p>
				<?php elseif(isset($error)): ?>
					<p style="color: red;">
						Une erreur est survenue lors de l'envoi du formulaire.
					</p>
				<?php endif; ?>

				<p>*: Obligatoire.</p>
			</form>
		</main>

		<?php require("../include/footer.php"); ?>
	</body>
</html>