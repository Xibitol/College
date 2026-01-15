import disappearTemplate from "@r/template/disappear.html?string";

const PENGUIN_CONTAINER_ID = "penguin";
const DISAPPEAR_CLASS_NAME = "disappear";
const FLAG_PROPERTY = "penguin";
const TRUE_FLAG_VALUE = "shown";

function main(element: HTMLElement): void{
	if(localStorage.getItem(FLAG_PROPERTY) === TRUE_FLAG_VALUE) return;

	element.addEventListener("animationend", event =>
		Array.from((event.target as HTMLElement).children)
			.forEach(child => {
				child.remove();
				localStorage.setItem(FLAG_PROPERTY, TRUE_FLAG_VALUE);
			})
	);

	element.innerHTML = disappearTemplate;
	element.classList.add(DISAPPEAR_CLASS_NAME);
}

// MAIN
{
	const disappearElement = document.getElementById(PENGUIN_CONTAINER_ID);

	if(disappearElement !== null)
		main(disappearElement);
}