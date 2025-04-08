<?php
	use L2S3PWI\App\Entity\Ville;

	/** @var Ville */
	$ville = $model;
?>
<?php if(isset($ville)): ?>
	<p><?= strval($ville); ?></p>
<?php else: ?>
	<p style="color: red">
		Aucune ville n'a l'identifiant <?= $identifier; ?>.
	</p>
<?php endif; ?>