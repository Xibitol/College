<?php
	$session;

	if(isset($_COOKIE["PHPSESSID"])){
		session_start();
		$session = $_SESSION;
	}else if(isset($_COOKIE["SESSION"]))
		$session = json_decode($_COOKIE["SESSION"], true);
?>
<!DOCTYPE html>
<html lang="fr">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Profil</title>
</head>
<body>
	<?php if(isset($session)): ?>
		<p>Bonjour <?= $session["prenom"]; ?> <?= $session["nom"]; ?>.</p>
		<p>Votre age est probablement <?= $session["age"]; ?>.</p>
		<p>
			Cette session provient d'un(e)
			<?= isset($_COOKIE["PHPSESSID"]) ? "session" : "cookie" ?>.
		</p>
	<?php else: ?>
		<p>Bonjour ...................... Aucune session.</p>
	<?php endif; ?>
</body>
</html>