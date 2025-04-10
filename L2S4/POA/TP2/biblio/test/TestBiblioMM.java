import java.util.ArrayList;

public class TestBiblioMM {
    public static void testRecherche() {
        BiblioMM biblio = new BiblioMM();
        CD unCD = new CD("The Joshua Tree", "U2", 12, 60);
        biblio.ajouter(unCD);
        ArrayList<EltMM> recherche = biblio.rechercherTitre("The Joshua Tree");
        if (recherche.size() != 1) {
            throw new Error("Echec sur la recherche du titre \"The Joshua Tree\"");
        } else if (!recherche.get(0).donneTitre().equals("The Joshua Tree")) {
            throw new Error("Probleme sur la recherche du titre \"The Joshua Tree\"");
        }

        CD unAutreCD = new CD("Rattle and Hum", "U2", 12, 60);
        DVD unAutreDVD = new DVD("Rattle and Hum", "Phil Joanou", 99);
        biblio.ajouter(unAutreCD);
        biblio.ajouter(unAutreDVD);

        recherche = biblio.rechercherTitre("Rattle and Hum");
        if (recherche.size() != 2) {
            throw new Error("Echec sur la recherche du titre \"Rattle and Hum\"");
        } else if (!recherche.get(0).donneTitre().equals("Rattle and Hum") ||
                !recherche.get(1).donneTitre().equals("Rattle and Hum")) {
            throw new Error("Probleme sur la recherche du titre \"Rattle and Hum\"");
        }
    }

    public static void testEmprunt() {
        BiblioMM biblio = new BiblioMM();
        CD unCD = new CD("The Joshua Tree", "U2", 12, 60);
        biblio.ajouter(unCD);
        EltMM eltEmprunte = biblio.emprunterTitre("The Joshua Tree");
        if (eltEmprunte == null) {
            throw new Error("L'emprunt du titre \"The Joshua Tree\" n'a pas ete effectue...");
        }
        // test pour voir si on peut emprunter 2 fois le meme element
        if (biblio.emprunterTitre("The Joshua Tree") != null) {
            throw new Error("L'emprunt du titre \"The Joshua Tree\" a deja ete effectue...");
        }
        // l'element est restitue...
        if (! biblio.rendreTitre(eltEmprunte)) {
            throw new Error("La restitution du titre \"The Joshua Tree\" n'a pas ete effectuee...");
        }
        // ... et donc il est a nouveau empruntable
        if (biblio.emprunterTitre("The Joshua Tree") == null) {
            throw new Error("L'emprunt du titre \"The Joshua Tree\" n'a pas ete effectue...");
        }
    }

    public static void testRechercheTypee() {
        BiblioMM biblio = new BiblioMM();
        CD unAutreCD = new CD("Rattle and Hum", "U2", 12, 60);
        DVD unAutreDVD = new DVD("Rattle and Hum", "Phil Joanou", 99);
        biblio.ajouter(unAutreCD);
        biblio.ajouter(unAutreDVD);
        ArrayList<EltMM> recherche = biblio.rechercherTitre("Rattle and Hum");
        for (EltMM elt : recherche) {
            if (elt.donneType().equals("CD")) {
                CD cd = (CD) elt;
                if (! cd.donneArtiste().equals("U2")) {
                    throw new Error("Probleme sur l'artiste du titre \"Rattle and Hum\"");
                }
            } else if (elt.donneType().equals("DVD")) {
                DVD dvd = (DVD) elt;
                if (! dvd.donneRealisateur().equals("Phil Joanou")) {
                    throw new Error("Probleme sur le realisateur du titre \"Rattle and Hum\"");
                }
            } else {
                throw new Error("Probleme sur le type de l'element associe au titre \"Rattle and Hum\"");
            }
        }
    }

    public static void main(String[] args) {
        testRecherche();
        testEmprunt();
        testRechercheTypee();
        System.out.println("Classe BiblioMM OK...");
    }
}