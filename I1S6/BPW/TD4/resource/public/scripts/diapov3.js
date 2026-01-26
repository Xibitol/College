const TRANSITION_DURATION = 2;

// SETTERS
/** @param {Context} context */
function previousPicture(context){
	context.index = --context.index < 0 ? tabImages.length - 1 : context.index;
}
/** @param {Context} context */
function nextPicture(context){
	context.index = ++context.index >= tabImages.length ? 0 : context.index;
}

// FUNCTIONS
/** @param {Context} context */
function reloadPicture(context){
	const img = tabImages[context.index];

	context.indexSpan.textContent = context.index + 1;
	context.filenameParagraph.textContent = img.split("/").reverse()[0];
	context.pictureImage.src = img;
}

/** @param {Context} context */
function doOncePerDelay(context){
	context.delayTimeoutID = null;
	document.addEventListener("transitionend",
		doDuringTransition.bind(null, context),
		{once: true}
	);
	context.pictureImage.classList.add("attenue");

}
/** @param {Context} context */
function doDuringTransition(context){
	nextPicture(context);
	reloadPicture(context);

	document.addEventListener("transitionend",
		doAfterTransition.bind(null, context),
		{once: true}
	);
	context.pictureImage.classList.remove("attenue");
}
/** @param {Context} context */
function doAfterTransition(context){
	context.transitionTimeoutID = null;

	if(context.delayTimeoutID === null)
		context.delayTimeoutID = setTimeout(
			doOncePerDelay.bind(null, context),
			context.delayInput.value*1000
		);
}

// INNER CLASSES
class Context{

	/** @type {number} */
	index = 0;
	/** @type {number|null|undefined} */
	delayTimeoutID = null;
	/** @type {number|null} */
	transitionTimeoutID = null;

	/** @type {HTMLImageElement} */
	#pictureImage;
	/** @type {HTMLSpanElement} */
	#indexSpan;
	/** @type {HTMLSpanElement} */
	#totalSpan;
	/** @type {HTMLParagraphElement} */
	#filenameParagraph;
	/** @type {HTMLInputElement} */
	#delayInput;
	/** @type {HTMLButtonElement} */
	#playButton;

	constructor(){
		this.#pictureImage = document.getElementById("diapo");
		this.#indexSpan = document.getElementById("index");
		this.#totalSpan = document.getElementById("total");
		this.#filenameParagraph = document.getElementById("filename");
		this.#delayInput = document.getElementById("delay");
		this.#playButton = document.getElementById("play");
	}

	// GETTERS
	get pictureImage(){ return this.#pictureImage; }
	get indexSpan(){ return this.#indexSpan; }
	get totalSpan(){ return this.#totalSpan; }
	get filenameParagraph(){ return this.#filenameParagraph; }
	get delayInput(){ return this.#delayInput; }
	get playButton(){ return this.#playButton; }
}

// LISTENERS
window.addEventListener("load", () => {
	const context = new Context();

	context.totalSpan.textContent = tabImages.length;
	reloadPicture(context);

	context.playButton.addEventListener("click", () => {
		if(context.delayTimeoutID !== undefined){
			if(context.delayTimeoutID !== null)
				clearInterval(context.delayTimeoutID);

			context.delayTimeoutID = undefined;
			context.playButton.textContent = "▶";
		}else{
			context.delayTimeoutID = setTimeout(
				doOncePerDelay.bind(null, context),
				context.delayInput.value*1000
			);
			context.playButton.textContent = "⏸";
		}
	});
});