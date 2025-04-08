<?php
	session_start();

	$data = array(
		array("prenom"=>"bob","email"=>"bob@gmail.com", "majorite"=>1),
		array("prenom"=>"marguerite","email"=>"marguerite@gmail.com", "majorite"=>0),
		array("prenom"=>"lucie","email"=>"lucie@gmail.com", "majorite"=>1),
		array("prenom"=>"john","email"=>"john@gmail.com", "majorite"=>0),
		array("prenom"=>"gulliver","email"=>"gulliver@gmail.com", "majorite"=>1),
		array("prenom"=>"tarzan","email"=>"tarzan@gmail.com", "majorite"=>1),
	);

	if(!isset($_SESSION["users"])) $_SESSION["users"] = $data;
?>
<!doctype html>
<html lang="fr">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Exercice 1 - CC1 - PWI</title>
</head>
<body>
	<nav>
		<ul>
			<li><a href="/">Retour;</a></li>
			<li><a href="/exo1/donnees.php">Données</a></li>
		</ul>
	</nav>
</body>
</html>
