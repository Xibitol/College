<?php
	foreach($_FILES as $file){
		if($file["error"] == UPLOAD_ERR_OK)
			move_uploaded_file($file["tmp_name"], "./upload/".$file["name"]);
	}
?>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>File tests</title>
	</head>
	<body>
		<form method="post" enctype="multipart/form-data">
			<fieldset>
				<legend>Transfert de fichier</legend>

				<p>
					<label for="file">Fichier</label>
					<input id="file" type="file"
						name="file"
						accept="image/jpeg"
					>
				</p>

				<p><input type="submit" name="submitted" value="Envoyer"></p>
			</fieldset>
		</form>
	</body>
</html>