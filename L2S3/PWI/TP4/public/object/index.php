<?php
	spl_autoload_register(function($class){
		require_once sprintf("../../src/%s.php", $class);
	});

	$db = new \Database();
	$man = new \PersonneManager();
	$man->setDb($db);

	$pers = $man->get(0);
?>
<!DOCTYPE html>
<html lang="fr">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Objet</title>
</head>
<body>
	<pre><?php
		var_dump($man->get(6));
		var_dump($man->add(new \Personne([
			"id" => 6,
			"prenom" => "Paul",
			"nom" => "Smith"
		])));
		$p = $man->get(6);

		var_dump($man->getAll());
		var_dump($p);

		$p->setPrenom("John");
		var_dump($man->update($p));
		var_dump($man->get(6));

		var_dump($man->delete($p));
		var_dump($man->getAll());
	?></pre>

	<p>Bonsoir <?= strval($pers); ?></p>
</body>
</html>