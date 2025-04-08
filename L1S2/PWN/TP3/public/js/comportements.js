/* ---- INTRODUCTION ---- */
console.log("Exécution du programme principal");

/* ---- EXERCICE 1 ---- */
const ingredientNodes = document.querySelectorAll("#ingredients > li");
console.log(`J'ai trouvé ${ingredientNodes.length} ingrédients.`);

/* ---- EXERCICE 2 ---- */
const ex2Element = document.getElementById("elemEx2");
console.log(`Classes de elemEx2 : \"${ex2Element.className}\".`);
ex2Element.classList.remove("control");
ex2Element.classList.add("inverse");

/* ---- EXERCICE 3 ---- */
document.getElementById("reussite").textContent = "ha ok, j'ai compris !";

const dog = document.getElementById("dog");
console.log(`Alt de l'image du petit chien trop trop mignon : ${dog.alt}.`);
document.getElementById("legendeDog").textContent = dog.alt;

/* ---- EXERCICE 4 ---- */
const ex4Figure = document.getElementById("figureEx4");
function toggleInvisibility(force){
	return ex4Figure.classList.toggle("invisible", force);
}

document.getElementById("boutonEffacerEx4").addEventListener("click", _ => {
	console.log("Évènement détecté !");
	toggleInvisibility(true);
});
document.getElementById("boutonAfficherEx4").addEventListener("click",
	toggleInvisibility.bind(undefined, false)
);

const buttonToggle = document.getElementById("boutonDoubleEx4");
function buttonToggleClicked(el, force){
	el.textContent = toggleInvisibility(force) ? "Montrer" : "Masquer";
}
buttonToggle.addEventListener("click", e => buttonToggleClicked(e.target));
buttonToggleClicked(buttonToggle, false);

/* ---- EXERCICE 5 ---- */
document.querySelectorAll("#ex5 > .liste_tetes img").forEach(el =>
	el.classList.add("penche")
);

document.querySelectorAll("h2").forEach(el =>
	el.classList.add("elargi")
);

/* ---- EXERCICE 6 ---- */
document.querySelector("#ex6 > p > img[src=\"images/dog.png\"]")
	.addEventListener("mouseover", e => {
		e.target.addEventListener("mouseenter", e =>
			e.target.classList.add("penche")
		);
		e.target.addEventListener("mouseleave", e =>
			e.target.classList.remove("penche")
		);
	});

document.querySelectorAll("#ex6 > .liste_tetes img").forEach(el => {
	el.addEventListener("mouseenter", e => el.classList.add("penche"));
	el.addEventListener("mouseleave", e => el.classList.remove("penche"));
});