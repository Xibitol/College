<?php
	require_once "functions.php";

	const FILENAME = "srilanka.json";

	// LOADING
	if(file_exists(FILENAME))
		$srilanka = json_decode(file_get_contents(FILENAME), true);
	else
		require_once "srilanka.php";

	// FORM PROCESSING
	const FIELDS = ["name", "long", "lat", "pop"];
	$missingFields = array_values(array_filter(FIELDS, function($field){
		$v = $_POST[$field] ?? $_FILE[$field] ?? null;

		return !(match(gettype($v)){
			"string" => strlen($v) > 0,
			"array" => sizeof($v) > 0,
			default => isset($v)
		});
	}));

	if(sizeof($missingFields) === 0){
		foreach($_POST as $n => $val)
			${$n} = match(gettype($val)){
				"NULL" => "",
				"array" => array_map(
					fn($v) => htmlspecialchars($v),
					$val
				),
				default => htmlspecialchars($val)
			};
		foreach($_FILES as $n => $val)
			${$n} = $val;

		$srilanka[$name] = [
			"long" => $long,
			"lat" => $lat,
			"pop" => $pop
		];

		move_uploaded_file($thumbnail["tmp_name"], getThumbnailPath($name));
	}

	// SAVING
	file_put_contents(FILENAME, json_encode($srilanka))
?>
<!DOCTYPE html>
	<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Cities of Sri Lanka</title>

		<style>
			img{
				width: 250px;

				object-fit: cover;
			}
		</style>
	</head>
	<body>
		<form method="post" enctype="multipart/form-data">
			<fieldset>
				<legend>New City at Sri Lanka</legend>
				<p>
					<label for="name">Name:</label>
					<input id="name" type="text" name="name" required>
				</p>
				<p>
					<label for="long">Longitude:</label>
					<input id="long" type="number"
						name="long" step="0.00000000000000001" required
					>
				</p>
				<p>
					<label for="lat">Latitude:</label>
					<input id="lat" type="number"
						name="lat" step="0.00000000000000001" required
					>
				</p>
				<p>
					<label for="pop">Population:</label>
					<input id="pop" type="number"
						name="pop" min="0" required
					>
				</p>
				<p>
					<label for="thumbnail">Thumbnail:</label>
					<input id="thumbnail" type="file"
						name="thumbnail"
						accept="image/jpeg"
						required
					>
				</p>
				<p><input type="submit" value="Add"></p>
			</fieldset>
		</form>

		<section>
			<h2>Current cities</h2>
			<p>
				Path distance of Negombo -> Anuradhapura -> Kandy -> Arugam Bay
				as the crow flies:
				<?=
					distance($srilanka,
						["Negombo", "Anuradhapura", "Kandy", "Arugam Bay"]
					);
				?> km.
			</p>
			<ul>
			<?php foreach($srilanka as $city => $caracteristics): ?>
				<li><?= $city; ?>
					<ul>
						<?php foreach($caracteristics as $name => $value): ?>
							<li><?= $name; ?>: <?= $value; ?></li>
						<?php endforeach; ?>
					</ul>
					<p><img src="<?= getThumbnailPath($city); ?>"></p>
				</li>
			<?php endforeach; ?>
			</ul>
		</section>
	</body>
</html>