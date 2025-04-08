<form method="post">
	<fieldset>
		<legend>Ajout d'une ville</legend>

		<div>
			<label for="id">Identifiant:</label>
			<input id="id" type="number" name="id" min="1">
		</div>

		<div>
			<label for="nom">Nom*:</label>
			<input id="nom" type="text" name="nom" required>
		</div>

		<div>
			<label for="pop">Population*:</label>
			<input id="pop" type="number" name="pop"
				min="0"
				required
			>
		</div>

		<div>
			<label for="long">Longitude*:</label>
			<input id="long" type="number" name="long" required>
		</div>

		<div>
			<label for="lat">Latitude*:</label>
			<input id="lat" type="number" name="lat" required>
		</div>

		<div>
			<label for="pays_id">Identifiant du pays*:</label>
			<input id="pays_id" type="number" name="pays_id"
				min="1"
				required
			>
		</div>

		<div>
			<input type="submit" name="submit"
				min="1"
				value="Sauvegarder"
			>
		</div>
	</fieldset>
</form>