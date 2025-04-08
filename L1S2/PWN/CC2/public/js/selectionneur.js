const ALL_CATEGORY = "tous";

/** Shows cards based on their category and return the number of shown cards.
 * @param {undefined|string} category
 * @returns {number}
 */
function showCards(category = undefined){
	let shownCount = 0;

	document.querySelectorAll(".set-of-cards > .card").forEach(
		/**
		 * @param {HTMLElement} card 
		 */
		card => {
			if(category !== undefined && card.dataset.categorie !== category)
				card.classList.add("card--invisible")
			else{
				card.classList.remove("card--invisible")
				shownCount++;
			}
		}
	);

	return shownCount;
}


/** Changes the text relative to the `number` of shown cards in a `category`.
 * @param {number} number 
 * @param {undefined|string} category
 */
function showNumberOfProjects(number, category = undefined){
	document.querySelector(".titre-liste-projets")
		.textContent = `Les ${number} projet(s)`
			+ (category ? ` ${category}` : "");
}

/** Changes chip active status, show related cards and change list's title.
 * @param {HTMLElement} chip 
 */
function onChipClicked(chip){
	chip.classList.add("chip--actif");

	let category;
	if(chip.dataset.categorie !== ALL_CATEGORY)
			category = chip.dataset.categorie;

	const shownCount = showCards(category);
	showNumberOfProjects(shownCount, category);
}

// ==== MAIN ===================================================================
const chips = document.querySelectorAll(".set-of-chips > .chip");
chips.forEach((chip, i) =>
	chip.addEventListener("click", ev =>{
		/**
		 * @type {HTMLElement}
		 */
		const target = ev.currentTarget;

		if(!target.classList.contains("chip--actif")){
			console.log(`${i} chip clicked (${target.textContent}).`);

			chip.parentElement.querySelectorAll(".chip.chip--actif")
				.forEach(chip => chip.classList.remove("chip--actif"));

			onChipClicked(chip);
		}
	})
);
chips.item(0)?.dispatchEvent(new PointerEvent("click"));