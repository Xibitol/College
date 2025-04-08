<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>TP1 - PWI</title>

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
	<h1>TP1 - PWI</h1>

	<!-- ------------------------------------------------------------------- -->
	<h2>Introduction</h2>
	<?php
		$date = date("o-m-d");
		$heure = date("H:i:s");
	?>
	<p>Nous sommes le <?= $date; ?> et il est <?= $heure; ?>.</p>

	<!-- ------------------------------------------------------------------- -->
	<h2>Exercice 1: Calcul sur les variables</h2>
	<?php
		$tva = 0.2;
		$prix = 150;
		$nombre = 10;
	?>
	<p>
		Pour <?= $nombre; ?> articles de <?= $prix; ?> CHF, cela vous coûtera
		<?= $prix*$nombre*($tva + 1); ?> CHF TTC (<?= $prix*$nombre; ?> CHF HT).
	</p>

	<!-- ------------------------------------------------------------------- -->
	<h2>Exercice 2: Bouclons la suite</h2>
	<blockquote>Styliser une page n'est qu'une boucle n'échecs.</blockquote>
	<p>- Cody Six</p>
	<?php
		$nbre = 20;
		$sum;

		for($i = 1; $i <= $nbre; $i++) $sum += $i;
	?>
	<ol>
		<?php for($i = 0; $i < $nbre; $i++): ?>
			<li>Ce point n'est pas le numéro <?= $nbre - $i ?>;</li>
		<?php endfor; ?>
	</ol>
	<p>Les points sommés font <?= $sum; ?>.</p>

	<!-- ------------------------------------------------------------------- -->
	<h2>Exercice 3: La fratrie d'articles</h2>
	<section>
		<?php for($i = 0; $i < 4; $i++): ?>
			<article id="<?= $i + 1 ?>">
				<header>
					<h3>Article n°<?= $i; ?></h3>
					<p><i>Écrit le <?= date("D j F o"); ?></i></p>
				</header>
				<?= file_get_contents("https://loripsum.net/api/1"); ?>
			</article>
		<?php endfor; ?>
	</section>

	<!-- ------------------------------------------------------------------- -->
	<h2>Exercice 4: Il n'y pas de si!</h2>
	<?php
		const days = [
			"Lundi",
			"Mardi",
			"Mercredi",
			"Jeudi",
			"Vendredi",
			"Samedi",
			"Dimanche"
		];

		function getPricing(int $age, bool $hasDiscount, int $day): int{
			if($age < 14) return 4;
			elseif($age < 18) return 5;
		
			if($day == 1) return 6;
			elseif($hasDiscount) return 7;

			return 8;
		}

		$age = random_int(3, 115);
		$hasDiscount = random_int(0, 1);
		$day = random_int(1, 7) - 1;
	?>
	<p>
		Nous somme le <?= days[$day]; ?>, vous avez <?= $age; ?> ans et vous
		<?= $hasDiscount ? "avez une" : "n'avez pas de"; ?> réduction. <b>Votre
		place vous coûtera <?= getPricing($age, $hasDiscount, $day); ?>£.</b>
	</p>

	<!-- ------------------------------------------------------------------- -->
	<h2>Exercice 5: Suite de valeurs contiguës</h2>
	<?php
		$grades = [10, 15, 16, 8, 12, 3];
		$avg = 0;

		foreach($grades as $grade) $avg += $grade;
		$avg = round($avg/sizeof($grades), 2);
	?>
	<ul>
		<?php foreach($grades as $grade): ?>
			<li><?= $grade ?>/20 points.</li>
		<?php endforeach; ?>
	</ul>
	<p>La moyenne des notes ci-dessus est de <?= $avg ?>/20 points.</p>

	<!-- ------------------------------------------------------------------- -->
	<h2>Exercice 6: Les tourtereaux.</h2>
	<?php
		$months = [
			"January" => "Janvier",
			"February" => "Février",
			"March" => "Mars",
			"April" => "Avril",
			"May" => "Mai",
			"June" => "Juin",
			"July" => "Juillet",
			"August" => "Août",
			"September" => "Septembre",
			"October" => "Octobre",
			"November" => "Novembre",
			"December" => "Décembre"
		];
	?>
	<table>
		<thead>
			<tr>
				<th>Le mois en anglais ...</th>
				<th>... traduit en français!</th>
			</tr>
		</thead>
		<tbody>
			<?php foreach($months as $enMonth => $frMonth): ?>
				<tr>
					<td><?= $enMonth; ?></td>
					<td><?= $frMonth; ?></td>
				</tr>
			<?php endforeach; ?>
		</tbody>
	</table>

	<!-- ------------------------------------------------------------------- -->
	<h2>Exercice 7: Macron explosion !</h2>
	<?php
		$email = "apellera@etudiant.univ-lr.fr";
		$email = "apellera@etudiant.univ-lr.fr";
	?>
	<p>
		Votre nom d'utilisateur est <?= explode("@", $email, 2)[0]; ?> et vous
		êtes inscrit sous le nom de domaine <?= explode("@", $email, 3)[1]; ?>.
	</p>

	<!-- ------------------------------------------------------------------- -->
	<h2>Exercice 8: Matrix</h2>
	<?php
		$clients = [
			["Leparc", "paris", 35],
			["Durox", "Bordeaux", 22],
			["Dupont", "Nantes", 27]
		];
	?>
	<table>
		<thead>
			<tr>
				<th>Numero</th>
				<th>Nom</th>
				<th>Ville</th>
				<th>Age</th>
			</tr>
		</thead>
		<tbody>
			<?php foreach($clients as $id => $client): ?>
				<tr>
					<td><?= $id; ?></td>
					<td><?= $client[0]; ?></td>
					<td><?= $client[1]; ?></td>
					<td><?= $client[2]; ?></td>
				</tr>
			<?php endforeach; ?>
		</tbody>
	</table>
	<pre><?= json_encode($clients); ?></pre>

	<!-- ------------------------------------------------------------------- -->
	<h2>Exercice 9: Les tourtereaux - Reloaded</h2>
	<?php
		$departments = [
			17 => "Charente Maritime",
			16 => "Charente",
			93 => "Seine Saint-Denis",
			33 => "Gironde"
		];
		
		$departments[62] = "Pas-de-Calais";

		ksort($departments);
	?>
	<ul>
		<?php foreach($departments as $number => $name): ?>
			<li>Le numéro du département <?= $name; ?> est <?= $number; ?>.</li>
		<?php endforeach ?>
	</ul>

	<!-- ------------------------------------------------------------------- -->
	<h2>Exercice 10: Les tourteraux - Endgame</h2>
	<?php
		include_once "srilanka.php";
		include_once "fonctions.php";
	?>
	<pre><?php print_r($srilanka); ?></pre>
	<ul>
		<?php foreach($srilanka as $city => $caracteristics): ?>
			<li><?= $city; ?>
				<ul>
					<?php foreach($caracteristics as $name => $value): ?>
						<li><?= $name; ?>: <?= $value; ?></li>
					<?php endforeach; ?>
				</ul>
			</li>
		<?php endforeach; ?>
	</ul>
	<p>
		La population moyenne des <?= sizeof($srilanka); ?> villes est de
		<?= averagePop($srilanka); ?> habitants.
	</p>
</body>
</html>