// VIEWS SIDE
const hintsListView = document.getElementById("indices");
const suspectsNumberView = document.getElementById("nbSuspects");
const suspectsListView = document.querySelector("#jeuReel > .personnages");
const endGameTextView = document.querySelector("#endGameText")

function getRemainingSuspects(){
	return suspectsListView
		.querySelectorAll(".personnage:not(.personnage--innocent)");
}

// CONTROLLERS SIDE
class GameController{
	/**
	 * @type {App}
	 */
	#app;

	/**
	 * @param {App} app 
	 */
	constructor(app){
		this.#app = app;
	}

	// GETTERS
	static get BEST_SCORE_LSNAME(){ return "bestscore"; }

	// GENERAL FUNCTIONS
	/**
	 * @param {MouseEvent} event 
	 */
	populate(){
		fetch("https://prodrigu.lpmiaw.univ-lr.fr/prog_web_novice/api/quiz/")
			.then(res => {
				res.json().then(json => {
					console.log(json);

					json.indices.forEach(indice => {
						const li = document.createElement("li");
						li.classList.add("indice", "invisible");
						li.textContent = indice;
						hintsListView.appendChild(li);
					});

					json.suspects.forEach(suspect => {
						const fig = document.createElement("figure");
						fig.classList.add("personnage");
						if(json.idCoupable == suspect.id)
							fig.classList.add("coupable");
						fig.dataset.id = suspect.id;
						suspectsListView.appendChild(fig);

						// Content
						const img = document.createElement("img");
						img.classList.add("personnage__image");
						img.src = json.dossierImagesSuspects
							+ `${suspect.id}.png`;

						const figCap = document.createElement("figcaption");
						figCap.classList.add("personnage__nom");
						figCap.textContent = `${suspect.prenom} ${suspect.nom}`;

						fig.append(img, figCap);
					});

					suspectsNumberView.textContent = json.suspects.length;

					this.#app.reBindListeners("personnage", "click");
				}).catch(r =>
					console.error(`Cannot parse the new fetched quiz (${r})`)
				)
			})
			.catch(r => console.error(`Cannot fetch a new quiz (${r})`));
	}
	
	endGame(){
		const remainingSuspects = getRemainingSuspects();

		if(remainingSuspects.length === 1
			&& remainingSuspects.item(0).classList.contains("coupable")
		){
			const score = hintsListView
				.querySelectorAll(":not(.invisible)").length;

			if(!this.#app.bestscore || this.#app.bestscore > score)
				this.#app.bestscore = score;

			endGameTextView.textContent = [
				"You win!",
				`Points (Like in Golf): ${score};`,
				`Best score: ${this.#app.bestscore}`
			].join(" ");
		}
	}

	reset(){
		Array.from(hintsListView.children)
			.forEach(element => element.classList.add("invisible"));

		Array.from(suspectsListView.children)
			.forEach(element =>
				element.classList.remove("personnage--innocent")
			);

		suspectsNumberView.textContent = suspectsListView
			.querySelectorAll(".personnage:not(.personnage--innocent)").length;

		endGameTextView.textContent = "";
	}

	clear(){
		Array.from(hintsListView.children).forEach(element => element.remove());
		Array.from(suspectsListView.children)
			.forEach(element => element.remove());

		suspectsNumberView.textContent = "";
		endGameTextView.textContent = "";
	}

	// ACTION FUNCTIONS
	/**
	 * @param {MouseEvent} event 
	 */
	giveHint(event){
		hintsListView.querySelector(".invisible")?.classList
			.remove("invisible");
	}

	/**
	 * @param {MouseEvent} event 
	 */
	toggleCharacter(event){
		/**
		 * @type {HTMLElement}
		 */
		const characterView = event.currentTarget;
		const characterNameView = characterView.querySelector(
			".personnage__nom"
		);
		let remainingSuspects = getRemainingSuspects();
		
		if((remainingSuspects.length > 1
			|| (
				remainingSuspects.item(0) !== characterView
				&& !remainingSuspects.item(0).classList.contains("coupable"))
			)
		){
			characterView.classList.toggle("personnage--innocent");
			console.log(
				`Personne changé: ${characterNameView.textContent}`
				+ `(${characterView.dataset.id}).`
			)

			remainingSuspects = getRemainingSuspects();
			suspectsNumberView.textContent = remainingSuspects.length;

			this.endGame(event);
		}
	}

	/**
	 * @param {MouseEvent} event
	 */
	restart(){
		this.clear();
		this.populate();
	}
}

// ----
class App{

	/**
	 * @type {GameController}
	 */
	#controller;
	/**
	 * @type {Map<string, Function>}
	 */
	#actions;

	/**
	 * @param {Map<string, Function>} actions
	 */
	constructor(actions){
		this.#actions = actions;
		const controller = new GameController(this);
		this.#controller = controller;

		this.#actions.forEach(((func, action) => {
			const params = action.split(":");
			this.reBindListeners(params[0], params[1], func);
		}).bind(this));

		this.#controller.reset();
	}

	// GETTERS
	static get BEST_SCORE_LSNAME(){ return "bestscore"; }
	/**
	 * @returns {number | undefined}
	 */
	get bestscore(){
		return localStorage.getItem(App.BEST_SCORE_LSNAME) ?? undefined;
	}
	/**
	 * @param {number} v
	 */
	set bestscore(v){
		// Bug intended (LOL)
		if(v == 0) v = -1;

		return localStorage.setItem(App.BEST_SCORE_LSNAME, v);
	}

	// FUNCTIONS
	/**
	 * @param {string} className
	 */
	reBindListeners(className, type){
		document.querySelectorAll(`.${className}`).forEach(
			element => element.addEventListener(type,
				this.#actions.get(`${className}:${type}`).bind(this.#controller)
			),
			this
		);
	}
}

new App(new Map([
	["donnerIndice:click", GameController.prototype.giveHint],
	["personnage:click", GameController.prototype.toggleCharacter],
	["recommencer:click", GameController.prototype.restart],
]));