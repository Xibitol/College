<?php
	use L2S3PWI\App\Entity\Ville;

	/** @var Ville[] */
	$villes = $models;
?>
<ul>
	<?php foreach($villes as $ville): ?>
		<li><?= strval($ville); ?></li>
	<?php endforeach; ?>
</ul>