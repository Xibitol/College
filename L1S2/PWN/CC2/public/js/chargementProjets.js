const PROJECTS_BASE_URL = "https://tinyurl.com/42bkw5cw/projets.php";
const IMAGES_BASE_URL = "https://tinyurl.com/bdd4kxmr/images";

fetch(PROJECTS_BASE_URL)
	.then(v => v.json())
	.then(v => {
		const set = document.querySelector(".set-of-cards");

		if(set instanceof HTMLElement && set.childElementCount > 0){
			for(const entry of v.projets){
				const card = document.querySelector(
					".set-of-cards > .card.card--modele"
				).cloneNode(true);
	
				if(card instanceof HTMLElement){
					card.classList.remove("card--modele");

					card.dataset.categorie = entry.categorie;

					if(title = card.querySelector(".card__titre"))
						title.textContent = entry.titre;
					if(img = card.querySelector(".card__image"))
						img.src = `${IMAGES_BASE_URL}/${entry.id}.jpg`;
	
					set.appendChild(card);
				}
			}
		}
	})
	.catch(console.error);