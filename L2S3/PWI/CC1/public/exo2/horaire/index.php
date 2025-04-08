<?php
	require("../include/connexion.php");
	$dbConnection = connectDB();

	$sessions = [];
	$courses = [];
	try{
		// SESSIONS (cours)
		$sessionSQL = "SELECT id_cours, date, nom, heure FROM cours NATURAL INNER JOIN typecours";

		if(isset($_GET["id"])){
			$stmt = $dbConnection->prepare(
				$sessionSQL." WHERE id_typecours = :id"
			);
			$stmt->execute(["id" => $_GET["id"]]);
			$sessions = $stmt->fetchAll();
		}else{
			$sessions = $dbConnection->query($sessionSQL)->fetchAll();
		}

		foreach($sessions as $key => $session){
			$relMeridiem = (new DateTime($session["heure"]))->format("a");
			$sessions[$key]["onMorning"] = $relMeridiem == "am";
		}

		// COURSES (typecours)
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
		<?php require("../include/header.php"); ?>

        <section class="typeCours">
            <div class="wrap">
                <ul>
					<?php foreach($courses as $course): ?>
						<li>
							<a href="/exo2/horaire?id=<?= $course["id_typecours"]; ?>">
								<?= $course["nom"]; ?>
							</a>
						</li>
					<?php endforeach; ?>
				</ul>
            </div>
        </section>

        <main class="planning">
            <?php foreach($sessions as $session): ?>
				<section class="<?= $session["onMorning"] ? "gris" : "noir"; ?>">
					<p><?= $session["date"]; ?></p>
					<h2><?= $session["nom"]; ?></h2>
					<p><?= $session["heure"]; ?></p>

					<p class="reservation">
						<a href="/exo2/reservation?id_cours=<?= $session["id_cours"]; ?>">Réserver</a>
					</p>
				</section>
			<?php endforeach; ?>
        </main>

		<?php require("../include/footer.php"); ?>
    </body>
</html>
