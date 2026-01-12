// 1.1
const pano = document.getElementById('panoramique');
console.log(pano);

// 1.4
const lamer = document.getElementById("lamer");
console.log(`lamer.alt = "${lamer.alt}";`);

lamer.alt = "vague s'écrasant sur les rochers";

// 1.5
const intro = document.getElementById("intro");
console.log(`intro.textContent = "${intro.textContent}";`);

// 1.6
console.log(`intro.innerHTML = "${intro.innerHTML}";`);

// 1.7
intro.style.color = "blue";
intro.style.fontSize = "18px";

// 1.8
/**
 * @param {HTMLImageElement} image
 * @param {number} width
 */
function changeWidth(image, width){
    image.style.width = `${width}px`;
}

changeWidth(pano, 400);

// 1.9
/**
 * @param {HTMLElement} element
 */
function loupe(element){
    const size = Number.parseFloat(window.getComputedStyle(element).fontSize);
    element.style.fontSize = `${size*2}px`;
}

loupe(document.getElementById("gr34"));
loupe(document.getElementById("milieu"));
loupe(document.getElementById("timoleon"));

// -----------------------------------------------------------------------------
// 2.1
const lesElements = document.getElementsByTagName("div");

console.log(`lesElements[0] = ${lesElements[0]};`);
lesElements[0].style.fontWeight = "bold";

// 2.2 2.3 2.4
/**
 * @param {string} tagName
 * @param {Element} root
 */
function tousVerts(tagName, root = document){
    Array.from(root.getElementsByTagName(tagName))
        .forEach(v => v.style.color = "green");
}

const root = document.getElementById("intro");
tousVerts("a");
tousVerts("em", root);

// -----------------------------------------------------------------------------
// 3.1
Array.from(document.getElementsByClassName("droite"))
    .forEach(v => v.style.padding = "20px");

// 3.2
document.querySelectorAll("#ajoncs .par")
    .forEach(v => v.style.border = "solid 1px blue");

// 3.3
/**
 * @param {string} clazz
 */
function cache(clazz){
    Array.from(document.getElementsByClassName(clazz))
        .forEach(v => v.style.display = "none");
}

// cache("intro");

// -----------------------------------------------------------------------------
// 4.2
const lesElements2 = document.querySelectorAll('div#ajoncs div.par');
console.log(lesElements2.length);
lesElements2[1].style.backgroundColor = 'rgba(0,128,0,0.5)';

// 4.3
document.querySelectorAll("div.par p:nth-of-type(1)")
    .forEach(v => v.style.fontWeight = "800");

// 4.4
const lesElements3 = document.querySelectorAll('div.par img.droite');
console.log(`lesElements2.length = ${lesElements3.length};`);

const premiere = document.querySelector('div.par img.droite');
premiere.style.width = '50%';
console.log(premiere);