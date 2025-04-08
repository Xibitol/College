package fr.lru;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.lru.decorator.monstre.Enrage;
import fr.lru.decorator.monstre.PeauRenforcee;
import fr.lru.decorator.personnage.Bouclier;
import fr.lru.decorator.personnage.Epee;
import fr.lru.exception.FormatInvalideException;
import fr.lru.exception.TypeEntiteException;
import fr.lru.exception.TypeMonstreException;
import fr.lru.exception.TypePersonnageException;
import fr.lru.fabrique.Fabrique;
import fr.lru.jeu.Entite;
import fr.lru.jeu.Monstre;
import fr.lru.jeu.Personnage;
import fr.lru.jeu.TypeMonstre;
import fr.lru.jeu.TypePersonnage;

public class AppTest {
    private Personnage personnage1;
    private Personnage personnage2;
    private Monstre monstre1;
    private Monstre monstre2;
    private Fabrique fabrique;

    @BeforeEach
    public void setUp() {
        personnage1 = new Personnage("Arthas", 150, 20, 10, TypePersonnage.GUERRIER, "Coup de bouclier");
        personnage2 = new Personnage("Arthas", 150, 20, 10, TypePersonnage.GUERRIER, "Coup de bouclier");
        monstre1 = new Monstre("GolemDePierre", 250, 15, 30, TypeMonstre.GOLEM, "Eau", "Phsyique");
        monstre2 = new Monstre("GolemDePierre", 250, 15, 30, TypeMonstre.GOLEM, "Eau", "Phsyique");
        fabrique = Fabrique.getInstance();
    }

    @Test
    void testPersonnage1() {
        assertFalse(personnage1 == personnage2);
    }

    @Test
    void testPersonnage2() {
        assertEquals(personnage1, personnage2);
    }

    @Test
    void testPersonnage3() {
        Personnage personnage3 = new Epee(personnage1);
        assertEquals(30, personnage3.getAttaque());
        
        Personnage personnage4 = ((Epee)personnage3).supprimerEquipement();
        assertEquals(20, personnage4.getAttaque());
        assertTrue(personnage1 == personnage4);
        assertEquals(personnage1, personnage4);
    }

    @Test
    void testPersonnage4() {
        Personnage personnage3 = new Epee(new Epee(new Bouclier(personnage1)));
        assertEquals(40, personnage3.getAttaque());
        assertEquals(15, personnage3.getDefense());
        assertEquals(150, personnage3.getHp());

        Personnage personnage4 = ((Epee)personnage3).supprimerEquipement();
        assertEquals(30, personnage4.getAttaque());
        assertEquals(15, personnage4.getDefense());
        assertEquals(150, personnage4.getHp());

        Personnage personnage5 = ((Epee)personnage4).supprimerEquipement();
        assertEquals(20, personnage5.getAttaque());
        assertEquals(15, personnage5.getDefense());
        assertEquals(150, personnage5.getHp());

        Personnage personnage6 = ((Bouclier)personnage5).supprimerEquipement();
        assertEquals(20, personnage6.getAttaque());
        assertEquals(10, personnage6.getDefense());
        assertEquals(150, personnage6.getHp());

        assertTrue(personnage1 == personnage6);
        assertEquals(personnage1, personnage6);
    }

    @Test
    void testMonstre1() {
        assertFalse(monstre1 == monstre2);
    }

    @Test
    void testMonstre2() {
        assertEquals(monstre1, monstre2);
    }

    @Test
    void testMonstre3() {
        Monstre monstre3 = new Enrage(monstre1);
        assertEquals(30, monstre3.getAttaque());
        assertEquals(25, monstre3.getDefense());

        Monstre monstre4 = ((Enrage)monstre3).supprimerAmelioration();
        assertEquals(15, monstre4.getAttaque());
        assertEquals(30, monstre4.getDefense());
        assertTrue(monstre1 == monstre4);
        assertEquals(monstre1, monstre4);
    }

    @Test
    void testMonstre4() {
        Monstre monstre3 = new Enrage(new PeauRenforcee(new Enrage(monstre1)));
        assertEquals(45, monstre3.getAttaque());
        assertEquals(30, monstre3.getDefense());
        assertEquals(250, monstre3.getHp());

        Monstre monstre4 = ((Enrage)monstre3).supprimerAmelioration();
        assertEquals(30, monstre4.getAttaque());
        assertEquals(35, monstre4.getDefense());
        assertEquals(250, monstre4.getHp());

        Monstre monstre5 = ((PeauRenforcee)monstre4).supprimerAmelioration();
        assertEquals(30, monstre5.getAttaque());
        assertEquals(25, monstre5.getDefense());
        assertEquals(250, monstre5.getHp());

        Monstre monstre6 = ((Enrage)monstre5).supprimerAmelioration();
        assertEquals(15, monstre6.getAttaque());
        assertEquals(30, monstre6.getDefense());
        assertEquals(250, monstre6.getHp());

        assertTrue(monstre1 == monstre6);
        assertEquals(monstre1, monstre6);
    }

    @Test
    public void testTypeEntiteException() {
        String ligneInvalide = "Alien;E.T.;Extra;999;99;99";

        Exception exception = assertThrows(TypeEntiteException.class, () -> {
            fabrique.creerEntite(ligneInvalide);
        });

        assertTrue(exception.getMessage().contains("Le premier paramètre doit être Personnage ou Monstre"));
    }

    @Test
    public void testFormatInvalideException_Personnage() {
        String ligneInvalide = "Personnage;Arthas;Guerrier;150;20;10"; // Manque la compétence

        Exception exception = assertThrows(FormatInvalideException.class, () -> {
            fabrique.creerEntite(ligneInvalide);
        });

        assertTrue(exception.getMessage().contains("Le format des données est invalide."));
    }

    @Test
    public void testFormatInvalideException_Monstre() {
        String ligneInvalide = "Monstre;Dragon;Dragon;300;50;30;Glace"; // Manque la résistance

        Exception exception = assertThrows(FormatInvalideException.class, () -> {
            fabrique.creerEntite(ligneInvalide);
        });

        assertTrue(exception.getMessage().contains("Le format des données est invalide."));
    }

    @Test
    public void testChampInvalideException_TypePersonnageInconnu() {
        String ligneInvalide = "Personnage;Arthas;Inconnu;150;20;10;Coup de bouclier"; // TypePersonnage inexistant

        Exception exception = assertThrows(TypePersonnageException.class, () -> {
            fabrique.creerEntite(ligneInvalide);
        });

        assertTrue(exception.getMessage().contains("Le type de personnage n'est pas reconnu"));
    }

    @Test
    public void testChampInvalideException_TypeMonstreInconnu() {
        String ligneInvalide = "Monstre;Dragon;Inexistant;300;50;20;Glace;Feu"; // TypeMonstre inexistant

        Exception exception = assertThrows(TypeMonstreException.class, () -> {
            fabrique.creerEntite(ligneInvalide);
        });

        assertTrue(exception.getMessage().contains("Le type de monstre n'est pas reconnu"));
    }

    @Test
    public void testCreationValidePersonnage() throws Exception {
        String ligneValide = "Personnage;Arthas;Guerrier;150;20;10;Coup de bouclier";

        Entite personnage = fabrique.creerEntite(ligneValide);

        assertNotNull(personnage);
        assertEquals("Arthas", personnage.getNom());
    }

    @Test
    public void testCreationValideMonstre() throws Exception {
        String ligneValide = "Monstre;Dragon;Dragon;300;50;30;Glace;Feu";

        Entite monstre = fabrique.creerEntite(ligneValide);

        assertNotNull(monstre);
        assertEquals("Dragon", monstre.getNom());
    }
}
