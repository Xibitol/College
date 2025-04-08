const figure = document.querySelector("section.galerie figure.galerie__focus");
const imgFigure = figure.querySelector("img");
const captionFigure = figure.querySelector("figcaption");
const thumbnails = document.querySelectorAll(
	"section.galerie div.vignettes img.vignette"
);

function showImage(thumbnail){
	imgFigure.src = thumbnail.src.replace("petites", "grandes");
	imgFigure.alt = thumbnail.alt;
	captionFigure.textContent = thumbnail.alt;
}

thumbnails.forEach(th => th.addEventListener("mouseenter", e =>
	showImage(th)
));
showImage(thumbnails.item(0));