/* ---- EXERCICE 1 ---- */;
const ulEx1 = document.querySelector("#ex1 > ul");
document.getElementById("boutonSupprimerEx1").addEventListener("click", e => {
	if(ul.childElementCount > 0) ul.firstElementChild.remove();
});

/* ---- EXERCICE 2 ---- */
const ulEx2 = document.querySelector("#ex2 > ul");
document.getElementById("boutonDeplacerEx2").addEventListener("click", e =>
	ulEx2.append(ulEx2.firstElementChild)
);
document.getElementById("boutonCreerEx2").addEventListener("click", e => {
	const liNew = document.createElement("li");
	liNew.textContent = "nouveau";
	ulEx2.append(liNew)
});

/* ---- EXERCICE 3 ---- */
const ulEx3 = document.getElementById("ingredients");
ulEx3.addEventListener("click", e => {
	const first = document.querySelector("#ingredients > li:not(.invisible)");
	if(first) first.classList.add("invisible");
});
document.querySelector("#ex3 span.reaf").addEventListener("click", e =>
	Array.from(ulEx3.children).forEach(el => el.classList.remove("invisible"))
);

/* ---- EXERCICE 4 ---- */
const ulEx4 = document.getElementById("lesPrenoms");
Array.from(ulEx4.children).forEach(el => el.addEventListener("click", e => {
	console.log(`Vous avez cliqué sur ${el.textContent}`);
	el.classList.add("invisible");
}));
document.querySelector("#ex4 span.reaf").addEventListener("click", e =>
	Array.from(ulEx4.children).forEach(el => el.classList.remove("invisible"))
);

/* ---- EXERCICE 5 ---- */
const elementsEx5 = document.querySelectorAll("#ex5 > ul > li");
elementsEx5.forEach(el => el.addEventListener("click", e =>
	el.parentElement.append(el)
));

/* ---- EXERCICE 6 ---- */
const elementsEx6 = document.querySelectorAll("#lettresEtNombres > li");
elementsEx6.forEach(clicked => clicked.addEventListener("click", e =>
	Array.from(elementsEx6)
		.filter(el => el.classList.contains(clicked.classList.item(0)))
		.forEach(el => el.classList.add("invisible"))
));
document.querySelector("#ex6 span.reaf").addEventListener("click", e =>
	elementsEx6.forEach(el => el.classList.remove("invisible"))
);