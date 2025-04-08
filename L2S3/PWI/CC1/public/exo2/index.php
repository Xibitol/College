<?php
	require("include/connexion.php");
	$dbConnection = connectDB();

	$courses = [];
	try{
		$courses = $dbConnection->query("SELECT * FROM typecours;")->fetchAll();
	}catch(\PDOException $ignored){}
?>
<!DOCTYPE html>
<html>
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
		<?php require("include/header.php"); ?>

        <main class="home">
			<?php foreach($courses as $course): ?>
				<article>
					<div class="wrap content">
						<img src="<?= "/exo2/".$course["photo"]; ?>">

						<section>
							<h2><?= $course["nom"]; ?></h2>
							<p><?= $course["description"]; ?></p>
						</section>
					</div>
				</article>
			<?php endforeach; ?>
        </main>

		<?php require("include/footer.php"); ?>
    </body>
</html>
