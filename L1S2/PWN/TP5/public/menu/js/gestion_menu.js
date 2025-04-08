/** fonctions utilitaires **/
const isHidden = elem => {
    const styles = window.getComputedStyle(elem)
    return styles.display === 'none' || styles.visibility === 'hidden'
}

function montrerMenuSiNecessaire() {
    if (isHidden(burger)) {
        ouvrirMenu();
    }
}

/* Initialisations ***/
const menu = document.querySelector('.menu-principal');
const burger = document.querySelector('.ouverture-menu');
const iconeFermetureMenu = document.querySelector('.fermeture-menu');

/** Si le burger n'est pas visible, montrer le menu */
console.log("Init");
console.log("visibility : " + burger.style.visibility);
montrerMenuSiNecessaire();

/** Mise en place des écouteurs d'événements **/
burger.addEventListener('click', ouvrirMenu);
iconeFermetureMenu.addEventListener('click', fermerMenu);
window.addEventListener("resize", () => montrerMenuSiNecessaire());

/** Création des gestionnaires d'événements **/
function ouvrirMenu() {
    menu.classList.remove("menu-principal--masque")
	burger.style.opacity = ".5";
}

function fermerMenu() {
    menu.classList.add("menu-principal--masque")
	burger.style.opacity = "1";
}