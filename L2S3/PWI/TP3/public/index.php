<?php
	require_once("../src/connexion.php");
	$connection = connectDB();
?>
<!DOCTYPE html>
<html lang="fr">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>TP3 - PWI</title>

	<style>
		table{
			border-collapse: collapse;
		}
		tr > *{
			padding: 5px 10px;

			border: 1px solid #000;
		}
		th{
			background-color: gainsboro;
		}
	</style>
</head>
<body>
	<pre>

	</pre>

	<h2>Mes contacts</h2>
	<?php
		$sql = "SELECT id, nom, prenom, telephone, mail, annee_naissance FROM Contact;";
		$info = $connection->query($sql)->fetchAll();
	?>
	<table>
		<thead>
			<tr>
				<th>Identifiant</th>
				<th>Nom</th>
				<th>Prénom</th>
				<th>Telephone</th>
				<th>Mail</th>
				<th>Année de naissance</th>
			</tr>
		</thead>
		<tbody>
			<?php foreach($info as $contact): ?>
				<tr>
					<td><?= $contact["id"]; ?></td>
					<td><?= $contact["nom"]; ?></td>
					<td><?= $contact["prenom"]; ?></td>
					<td><?= $contact["telephone"]; ?></td>
					<td><?= $contact["mail"]; ?></td>
					<td><?= $contact["annee_naissance"]; ?></td>
				</tr>
			<?php endforeach; ?>
		</tbody>
	</table>

	<h2>Requête avec critères</h2>
	<?php
		$sql = "SELECT id, nom, prenom, telephone, mail, annee_naissance FROM Contact WHERE ville_id=2;";
		$info = $connection->query($sql)->fetchAll();
	?>
	<table>
		<thead>
			<tr>
				<th>Identifiant</th>
				<th>Nom</th>
				<th>Prénom</th>
				<th>Telephone</th>
				<th>Mail</th>
				<th>Année de naissance</th>
			</tr>
		</thead>
		<tbody>
			<?php foreach($info as $contact): ?>
				<tr>
					<td><?= $contact["id"]; ?></td>
					<td><?= $contact["nom"]; ?></td>
					<td><?= $contact["prenom"]; ?></td>
					<td><?= $contact["telephone"]; ?></td>
					<td><?= $contact["mail"]; ?></td>
					<td><?= $contact["annee_naissance"]; ?></td>
				</tr>
			<?php endforeach; ?>
		</tbody>
	</table>

	<h2>Requête d'insertion</h2>
	<?php
		$newVille = [
			"nom" => random_bytes(10),
			"code_postal" => random_int(0, 99999)
		];

		$sql = "SELECT 1 FROM Ville WHERE code_postal=:code_postal;";
		$req = $connection->prepare($sql);
		$req->execute([
			"code_postal" => $newVille["code_postal"]
		]);
		$test = $req->fetch();

		if($test === false){
			$sql = "INSERT INTO Ville(nom, code_postal) VALUES (:nom, :code_postal)";
			$connection->prepare($sql)->execute($newVille);
		}

		$sql = "SELECT id, nom, code_postal FROM Ville;";
		$info = $connection->query($sql)->fetchAll();
	?>
	<table>
		<thead>
			<tr>
				<th>Identifiant</th>
				<th>Nom</th>
				<th>Code postal</th>
			</tr>
		</thead>
		<tbody>
			<?php foreach($info as $contact): ?>
				<tr>
					<td><?= $contact["id"]; ?></td>
					<td><?= $contact["nom"]; ?></td>
					<td><?= $contact["code_postal"]; ?></td>
				</tr>
			<?php endforeach; ?>
		</tbody>
	</table>
	<?php
		$connection->exec(sprintf(
			"DELETE FROM Ville WHERE code_postal=%s",
			$newVille["code_postal"]
		));
	?>

	<h2>Requête préparé</h2>
	<?php
		$sql = "SELECT nom, prenom FROM Contact WHERE ville_id=:ville_id;";
		$req = $connection->prepare($sql);
		$req->execute(["ville_id" => 2]);
		$info = $req->fetchAll();
	?>
	<table>
		<thead>
			<tr>
				<th>Nom</th>
				<th>Prénom</th>
			</tr>
		</thead>
		<tbody>
			<?php foreach($info as $contact): ?>
				<tr>
					<td><?= $contact["nom"]; ?></td>
					<td><?= $contact["prenom"]; ?></td>
				</tr>
			<?php endforeach; ?>
		</tbody>
	</table>

	<span id="ex5"></span>
	<h2>Requête avec critère issu d'un formulaire</h2>
	<?php
		$city = $_GET["city"];
		$cityID = null;

		if(isset($city)){
			$sql = "SELECT id FROM Ville WHERE nom=:city";
			$req = $connection->prepare($sql);
			$req->execute(["city" => $city]);
			$cityID = $req->fetch();

			if(gettype($cityID) === "array") $cityID = $cityID["id"];
			else $cityID = -1;
		}

		$sql = "SELECT nom, prenom FROM Contact";
		if(isset($cityID) && $cityID !== -1)
			$sql .= " WHERE ville_id=:ville_id;";
		$req = $connection->prepare($sql);
		$req->execute(isset($cityID) && $cityID !== -1 ? ["ville_id" => $cityID] : []);
		$info = $req->fetchAll();
	?>
	<form method="get" action="/#ex5">
		<fieldset>
			<legend>Rechercher les contacts d'une ville</legend>

			<div>
				<label for="city">Nom de la ville*:</label>
				<input id="city" type="text" name="city" required>
			</div>

			<input type="submit" value="Chercher les contacts">
			<?php if(isset($cityID) && $cityID === -1): ?>
				<p style="color: red;">
					Il n'y a aucunne ville nommée <?= $city; ?>.
				</p>
			<?php endif; ?>
		</fieldset>
	</form>
	<table>
		<thead>
			<tr>
				<th>Nom</th>
				<th>Prénom</th>
			</tr>
		</thead>
		<tbody>
			<?php foreach($info as $contact): ?>
				<tr>
					<td><?= $contact["nom"]; ?></td>
					<td><?= $contact["prenom"]; ?></td>
				</tr>
			<?php endforeach; ?>
		</tbody>
	</table>

	<span id="ex6"></span>
	<h2>Requête avec d'insertion issu d'un formulaire</h2>
	<?php
		$contact = $_POST;

		foreach($contact as $key => $value){
			if(empty($value)) $contact[$key] = null;
			else if(in_array($key, ["telephone", "annee_naissance"]))
				$contact[$key] = intval($value);
		}

		if(sizeof($contact) > 1){
			$sql = "INSERT Contact(nom, prenom, telephone, mail, annee_naissance, ville_id) VALUES (:nom, :prenom, :telephone, :mail, :annee_naissance, (SELECT id FROM Ville WHERE nom=:city));";
			
			try{
				$req = $connection->prepare($sql)->execute($contact);
			}catch(PDOException $e){
				$error = $e->getMessage();
			}
		}

		$sql = "SELECT id, nom, prenom, telephone, mail, annee_naissance, ville_id FROM Contact;";
		$info = $connection->query($sql)->fetchAll();
	?>
	<form method="post" action="/#ex6">
		<fieldset>
			<legend>Ajouter un contact</legend>

			<div>
				<label for="nom">Nom:</label>
				<input id="nom" type="text" name="nom">
			</div>
			<div>
				<label for="prenom">Prénom*:</label>
				<input id="prenom" type="text" name="prenom" required>
			</div>
			<div>
				<label for="telephone">Téléphone:</label>
				<input id="telephone" type="text" name="telephone">
			</div>
			<div>
				<label for="mail">Mail:</label>
				<input id="mail" type="text" name="mail">
			</div>
			<div>
				<label for="annee_naissance">Année de naissance:</label>
				<input id="annee_naissance" type="text" name="annee_naissance">
			</div>
			<div>
				<label for="city">Ville:</label>
				<input id="city" type="text" name="city">
			</div>

			<p><i>Un numéro de téléphone ou un mail est obligatoire.</i></p>

			<input type="submit" value="Ajouter">
			<?php if(isset($error)): ?>
				<p style="color: red;"><?= $error; ?>.</p>
			<?php endif; ?>
		</fieldset>
	</form>
	<table>
		<thead>
			<tr>
				<th>Identifiant</th>
				<th>Nom</th>
				<th>Prénom</th>
				<th>Telephone</th>
				<th>Mail</th>
				<th>Année de naissance</th>
				<th>Identifiant de la ville</th>
			</tr>
		</thead>
		<tbody>
			<?php foreach($info as $contact): ?>
				<tr>
					<td><?= $contact["id"]; ?></td>
					<td><?= $contact["nom"]; ?></td>
					<td><?= $contact["prenom"]; ?></td>
					<td><?= $contact["telephone"]; ?></td>
					<td><?= $contact["mail"]; ?></td>
					<td><?= $contact["annee_naissance"]; ?></td>
					<td><?= $contact["ville_id"]; ?></td>
				</tr>
			<?php endforeach; ?>
		</tbody>
	</table>
</body>
</html>