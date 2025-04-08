<?php
	const SESSION_COOKIE_NAME = "PHPSESSID";
	const SESSION_DATA_KEY = "users";

	// Dans le cas ou il n'y a pas de session, j'ai décidé de renvoyer
	// l'utilisateur à la page principale (/).
	if(!isset($_COOKIE[SESSION_COOKIE_NAME])){
		header("Location: /");
		exit;
	}
	session_start();

	$complement = array(
		array("prenom"=>"johann","email"=>"johann@gmail.com", "majorite"=>0),
		array("prenom"=>"perle","email"=>"perle@gmail.com", "majorite"=>1)
	);
	if(isset($_SESSION[SESSION_DATA_KEY]))
		$complement = array_merge($_SESSION[SESSION_DATA_KEY], $complement);

	$majorCount = array_reduce($complement, function($carry, $user){
		return $user["majorite"] ? $carry + 1 : $carry;
	}, 0);
?>
<!doctype html>
<html lang="fr">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Données - CC1 - PWI</title>
</head>
<body>
	<main>
		<ul>
			<?php foreach($complement as $user): ?>
				<?php if(isset($user["prenom"])
					&& strlen($user["prenom"]) > 5
				): ?>
					<li><?= $user["prenom"]; ?> <?= $user["email"]; ?></li>
				<?php endif; ?>
			<?php endforeach; ?>
		</ul>

		<p>nb de majeure = <?= $majorCount; ?></p>
	</main>
</body>
</html>
