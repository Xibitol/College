<?php
	const FIELDS = ["prenom", "nom", "age", "sports"];
	$missingFields = array_values(array_filter(FIELDS, function($field){
		$v = $_POST[$field];

		return !(match(gettype($v)){
			"string" => strlen($v) > 0,
			"array" => sizeof($v) > 0,
			default => isset($v)
		});
	}));

	if(!isset($_POST["send"])){
		header("Location: /");
		exit;
	}elseif(sizeof($missingFields) === 0){
		foreach($_POST as $name => $value)
			${$name} = match(gettype($value)){
				"NULL" => "",
				"array" => array_map(
					fn($v) => htmlspecialchars($v),
					$value
				),
				default => htmlspecialchars($value)
			};

		$age = (new DateTime($age))->diff(new DateTime())->format("%r%y");
	}
?>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>Votre profil</title>
	</head>
	<body>
		<?php if(sizeof($missingFields) > 0): ?>
			<script>
				alert(
					"Attention loustique! Tu as oublié le champ "
					+ "<?= $missingFields[0] ?> ... Vas-y, réessaie mon grand."
				);

				window.history.back();
			</script>
		<?php else: ?>
			<p>
				Bonjour cher <?= $prenom; ?> <?= $nom; ?>. Vous avez
				<?= $age; ?> années d'existence. Votre email est <?= $email; ?>.
			</p>

			<p>Vous aimez les sports:</p>
			<ul>
				<?php foreach($sports as $sport): ?>
					<li><?= $sport ?>;</li>
				<?php endforeach; ?>
			</ul>
		<?php endif; ?>
	</body>
</html>