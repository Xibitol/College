console.log('Exécution du programme carte.js');

/**** Programme principal ***/
// création de la carte
maCarte = L.map('carte').setView([43.599535, 1.43], 11);

// Jeton d'accès à MapBox, qui fournit le fond de carte 
const mapBoxAccessToken = 'pk.eyJ1IjoicGVkcm9kYWN0eWxlIiwiYSI6IjVmdHRmUjgifQ.Cl1waAaPYaOY9qJr14rCew';

// Identifiant du projet MapBox
const mapBoxProjectId = 'pedrodactyle.hgfj5llg';

// Création de la couche de fond de carte
const fondDeCarte = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://mapbox.com">Mapbox</a>',
    maxZoom: 18,
    id: mapBoxProjectId,
    accessToken: mapBoxAccessToken
});
// Ajout de la couche de fond à la carte
fondDeCarte.addTo(maCarte);

// Voici comment on affiche un marqueur
afficherUnMarqueur(43.58507893338048, 1.435919445061078, "Marqueur d'essai", "Adresse", "Implantation");


/**** Récupération et traitement des défibrillateurs ****/
// Où aller chercher les données
const urlDefibrillateurs = "https://data.toulouse-metropole.fr/api/records/1.0/search?dataset=defibrillateurs&rows=10";

// Effectuer l'appel Ajax
axios.get(urlDefibrillateurs).then(displayMarkers);
/******** /Fin du programme principal *****************/

/******** Fonctions utilisées par le prog principal ********/
function afficherUnMarqueur(long, lat, title, address, impl){
	L.marker([long, lat], {
		"title": `${title ? `${title}, ` : ""}${address} | ${impl}`,
	}).addTo(maCarte);
}


/******* DEFIBRILLATEURS ***********************************/
// Fonction dont l'exécution est déclenchée si l'appel Ajax réussit
function displayMarkers(req){
	console.log(req);
	req.data.records.forEach(r => afficherUnMarqueur(
		r.geometry.coordinates[1],
		r.geometry.coordinates[0],
		r.fields.nom_site,
		r.fields.adresse,
		r.fields.emplacement
	));
}