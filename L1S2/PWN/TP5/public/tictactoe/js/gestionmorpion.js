const SIZE = 3;

/* ==== GAME STATES ==== */
class Player{

	static BASE_SELECTION_CLASS = "case--joueur";

	// ENUMERATIONS
	static #A = new Player("A");
	static #B = new Player("B");

	#value;

	constructor(value){
		this.#value = value;
	}

	// GETTERS
	/** @return {Array<Player>} */
	static get values(){ return [Player.#A, Player.#B]; }

	static get A(){ return Player.#A; }
	static get B(){ return Player.#B; }

	get value(){ return this.#value; }
	get class(){ return `${Player.BASE_SELECTION_CLASS}${this.#value}`; }

	/** @returns {Player} */
	next(){
		if(this.value == Player.A.value) return Player.B;
		else return Player.A;
	}
}

/** @type {Boolean} */
let playing = true;
/** @type {Player} */
let currentPlayer = Player.B;

/* ==== INTERACTION ===== */
const playerIDSpan = document.getElementById("currentPlayerID");
const gridContainer = document.querySelector("section.grille");
const resultParagraph = document.querySelector("p.resultat");

/**
 * @param {HTMLTableCellElement} cell
 * @param {Player} player
 */
function isCellFilled(cell, player){
	return Array.from(cell.classList).some(
		v => (!player || player.class == v) &&
			v.startsWith(Player.BASE_SELECTION_CLASS)
	);
}

/**
 * @param {HTMLTableElement} grid
 * @return {Player | null}
 */
function whichWon(grid){
	const rows = grid.querySelectorAll("tbody > tr");
	let draw = true;

	// We try to find a player that won (Three symbol aligned). If no one won,
	// we look at "draw", completed during first players iteration, telling if
	// all cells are filled; i.e. this is a draw, we return "null". In other
	// cases, the game can continue by returning "undefined".
	return Player.values.find((p, i) => {
		// Creation of variables telling if associated grid lines are filled.
		const rowsCompleted = new Array(SIZE).fill(true);
		const colsCompleted = new Array(SIZE).fill(true);
		let lDiagCompleted = true;
		let rDiagCompleted = true;

		// We explore the grid and for each cells, we complete above variables
		// to make them tell the thruth. If one cell in a line isn't completed
		// by this player (or by anyone), the line becomes "false" (Not filled).
		Array.from(rows).forEach((row, y) => {
			Array.from(row.children).forEach((cell, x) => {
				const filled = isCellFilled(cell, p);

				rowsCompleted[y] &&= filled;
				colsCompleted[x] &&= filled;
				if(x == y) lDiagCompleted &&= filled;
				if(x == SIZE - y - 1) rDiagCompleted &&= filled;

				if(i == 0) draw &&= isCellFilled(cell);
			});
		});

		// Every lines that wasn't completed by this player correspond to
		// variables set to "false".
		return rowsCompleted.some(v => v) || colsCompleted.some(v => v) ||
			lDiagCompleted || rDiagCompleted;
	}) ?? (draw ? null : undefined);
}

/** @param {HTMLTableCellElement} cell */
function clickCell(grid, cell){
	if(!playing) return;

	if(cell){
		if(isCellFilled(cell)) return;
		cell.classList.add(currentPlayer.class);
	}

	currentPlayer = currentPlayer.next();
	playerIDSpan.textContent = currentPlayer.value;

	if((winner = grid ? whichWon(grid) : undefined) !== undefined){
		playing = false;
		
		if(winner == null)
			resultParagraph.textContent = "Draw!";
		else
			resultParagraph.textContent = `Le joueur ${winner.value} a gagné !`;
	}
}
clickCell();

// -- Table generation --
const gridTable = document.createElement("table");
const gridBody = document.createElement("tbody");
gridContainer.append(gridTable);
gridTable.append(gridBody);

for(let x = 0; x < Math.pow(SIZE, 2); x++) {
	if(x%SIZE == 0)
		gridBody.append(document.createElement("tr"));

	const td = document.createElement("td");
	td.classList.add("case");
	gridBody.lastElementChild.append(td);

	td.addEventListener("click", e => clickCell(gridTable, e.target));
}