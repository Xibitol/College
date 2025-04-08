<?php
	setcookie(
		"SESSION",
		json_encode([
			"prenom" => "Xib",
			"nom" => "Itol",
			"age" => 91
		]),
		time() + 60,
	)
?>
<!DOCTYPE html>
<html lang="fr">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Cookies</title>
</head>
<body>
	<nav>
		<ul>
			<li><a href="/profil">Profil</a></li>
		</ul>
	</nav>
</body>
</html>