// 2.2
/**
 * @param {number} celsius
 * @returns {number}
 */
function toFahrenheit(celsius){
	return 9/5*celsius + 32;
}

// 2.3
/** @type {HTMLInputElement} */
const celsiusInput = document.getElementById("celsius");
/** @type {HTMLInputElement} */
const fahrenheitInput = document.getElementById("fahrenheit");

function reaction_cToF(){
	if(celsiusInput.value.length === 0) return;
	const converted = Number.parseFloat(celsiusInput.value);

	if(!Number.isNaN(converted))
		fahrenheitInput.value = toFahrenheit(converted).toFixed(2);
}

// 2.4
/** @type {HTMLButtonElement} */
const toFahrenheitButton = document.getElementById("cToF");

toFahrenheitButton.addEventListener("click", reaction_cToF);

// 2.6
/** @type {HTMLButtonElement} */
const toCelsiusButton = document.getElementById("fToC");

/**
 * @param {number} fahrenheit
 * @returns {number}
 */
function toCelsius(fahrenheit){
	return (fahrenheit - 32)*5/9
}

function reaction_fToC(){
	if(fahrenheitInput.value.length === 0) return;
	const converted = Number.parseFloat(fahrenheitInput.value);

	if(!Number.isNaN(converted))
		celsiusInput.value = toFahrenheit(converted).toFixed(2);
}

toCelsiusButton.addEventListener("click", reaction_fToC);

// 2.7
celsiusInput.addEventListener("input", reaction_cToF);
fahrenheitInput.addEventListener("input", reaction_fToC);
