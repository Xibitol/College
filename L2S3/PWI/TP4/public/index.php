<?php
	session_start();

	$_SESSION["prenom"] = "Xib";
	$_SESSION["nom"] = "Itol";
	$_SESSION["age"] = 91
?>
<!DOCTYPE html>
<html lang="fr">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>TP4 - PWI</title>
</head>
<body>
	<nav>
		<ul>
			<li><a href="/url?produit=23&prix=120">Paramètres d'URL</a></li>
			<li><a href="/profil">Cookies</a></li>
			<li><a href="/profil">Profil</a></li>
		</ul>
	</nav>
</body>
</html>