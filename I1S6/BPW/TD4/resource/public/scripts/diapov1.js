let indiceImage = 0;

// SETTERS
function imagePrecedente(){
	indiceImage = --indiceImage < 0 ? tabImages.length - 1 : indiceImage;
}
function imageSuivante(){
	indiceImage = ++indiceImage >= tabImages.length ? 0 : indiceImage;
}

// FUNCTIONS
/** @param {HTMLImageElement} element */
function afficheImage(element){
	element.src = tabImages[indiceImage];
}
// LISTENERS
window.addEventListener("load", () => {
	const imageElement = document.getElementById("diapo");

	document.getElementById("previous").addEventListener("click", () => {
		imagePrecedente();
		afficheImage(imageElement);
	});
	document.getElementById("next").addEventListener("click", () => {
		imageSuivante();
		afficheImage(imageElement);
	});
});