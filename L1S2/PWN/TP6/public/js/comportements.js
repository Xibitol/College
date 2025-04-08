const BASE_URL = "https://prodrigu.lpmiaw.univ-lr.fr/mineure-info-1/ajax"

console.log("Exécution du programme comportement.js");

document.querySelector("#recupObjet .bouton").addEventListener('click', recupererUnObjet);

function recupererUnObjet() {
	// Tentative de récupération d'une donnée externe
	const promesseRecupUnePersonne = axios.get(BASE_URL + "/recupererUnePersAuHasard/");

	// Si la promesse est tenue, exécuter la fonction afficherPersonneAuHazard
	promesseRecupUnePersonne.then(afficherPersonneAuHasard);

	// Si la promesse n'est pas tenue, exécuter la fonction afficherErreurAjax
	promesseRecupUnePersonne.catch(afficherErreurAjax);
}

// Fonction de traitement de l'appel ajax à recupererUnePersAuHasard
function afficherPersonneAuHasard(reponseAjax) {
	let data = reponseAjax.data;

	console.log(data);
	console.log(data.nom);
	document.querySelector("#recupObjet .resultat span").textContent = data.nom;

} //afficherPersonneAuHasard

function afficherErreurAjax(erreur) {
	console.log(erreur);
} //afficherErreurAjax

// ---- Displaying tables ----
document.querySelector("#recupTableau .bouton")
	.addEventListener("click", async e => {
		const req = await axios.get(BASE_URL + "/recupererLesPers/");

		if(req.code !== undefined){
			console.error(req);
			return;
		}

		const lesPersonnes = req.data;
		console.log(`${lesPersonnes[0].prenom} ${lesPersonnes[0].nom}`);

		const resultContainer = document.querySelector(
			"#recupTableau ol.resultat"
		);
		lesPersonnes.forEach(p => {
			console.log(`${p.prenom} ${p.nom}`);

			let li = document.createElement("li");
			li.textContent = `${p.prenom} ${p.nom}`;
			resultContainer.appendChild(li);
		}) 
	});