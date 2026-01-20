// 1.1
const FIRST_PANO_IMAGE = "images/panoramique.jpg";
const SECOND_PANO_IMAGE = "images/panoramique2.jpg";
/** @type {HTMLImageElement} */
const panoramiqueImg = document.getElementById("panoramique");

function changePano(){
	if(panoramiqueImg.src.endsWith(FIRST_PANO_IMAGE))
		panoramiqueImg.src = SECOND_PANO_IMAGE;
	else
		panoramiqueImg.src = FIRST_PANO_IMAGE;
}

// 1.2
panoramiqueImg.addEventListener("mouseover", changePano);

// 1.3
panoramiqueImg.addEventListener("mouseout", changePano);