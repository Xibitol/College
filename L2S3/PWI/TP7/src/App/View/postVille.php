<?php if(isset($error) && $error): ?>
	<p>
		Une erreur est survenue, veuillez rééssayer; Les valeurs peuvent être
		invalides.
	</p>
<?php else:
	include("getOneVille.php");
endif; ?>