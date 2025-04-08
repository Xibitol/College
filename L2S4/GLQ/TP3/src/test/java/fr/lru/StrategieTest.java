package fr.lru;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.lru.decorator.personnage.Potion;
import fr.lru.jeu.Monstre;
import fr.lru.jeu.Personnage;
import fr.lru.jeu.TypeMonstre;
import fr.lru.jeu.TypePersonnage;

public class StrategieTest {
    private Personnage arthas;
	private Personnage legolas;
    @SuppressWarnings("unused")
    private Personnage morgana;
    private Monstre golemDePierre;
    private Monstre zombie;
    @SuppressWarnings("unused")
    private Monstre orcSanguinaire;

    @BeforeEach
    public void setUp() {
        arthas = new Personnage("Arthas", 15, 20, 10, TypePersonnage.GUERRIER, "Coup de bouclier");
        legolas = new Personnage("Legolas", 120, 25, 8, TypePersonnage.ARCHER, "Tir perçant");
        morgana = new Personnage("Morgana", 100, 30, 5, TypePersonnage.MAGE, "Boule de feu");

        golemDePierre = new Monstre("GolemDePierre", 25, 15, 30, TypeMonstre.GOLEM, "Eau", "Physique");
        zombie = new Monstre("Zombie", 100, 10, 5, TypeMonstre.MORT_VIVANT, "Feu", "Poison");
        orcSanguinaire = new Monstre("OrcSanguinaire", 180, 25, 10, TypeMonstre.ORC, "Magie", "Physique");
    }

    @Test
    void attaqueMonstre1() {
        for(int i = 0; i < 15; i++) golemDePierre.attaquer(arthas);
        assertEquals(0, arthas.getHp());
    }
    @Test
    void attaqueMonstre2() {
        Potion p = new Potion(arthas);
        for(int i = 0; i < 15; i++) golemDePierre.attaquer(p, 20);
        assertEquals(20, p.getHp());

        arthas = p.supprimerEquipement();
        assertEquals(0, arthas.getHp());
    }

	@Test
    void defensePersonnage1() {
        zombie.attaquer(legolas, 20);
        assertEquals(111, legolas.getHp());
        assertEquals(23, legolas.getAttaque());

		legolas.attaquer(zombie);
        assertEquals(85, zombie.getHp());
        assertEquals(7, zombie.getAttaque());
    }
	@Test
    void defensePersonnage2() {
       	morgana.attaquer(orcSanguinaire);
        assertEquals(180, orcSanguinaire.getHp());
        assertEquals(25, orcSanguinaire.getAttaque());
        assertEquals(10, orcSanguinaire.getDefense());
    }
	@Test
    void defensePersonnage3() {
       	orcSanguinaire.attaquer(morgana, 10);
		assertEquals(98, morgana.getHp());

		morgana.attaquer(orcSanguinaire);
		assertEquals(170, orcSanguinaire.getHp());
    }
}
