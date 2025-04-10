package fr.lru;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.lru.decorator.monstre.Enrage;
import fr.lru.decorator.monstre.MonstreAmeliore;
import fr.lru.decorator.monstre.PeauRenforcee;
import fr.lru.decorator.personnage.Bouclier;
import fr.lru.decorator.personnage.Epee;
import fr.lru.decorator.personnage.PersonnageEquipe;
import fr.lru.decorator.personnage.Potion;
import fr.lru.jeu.Monstre;
import fr.lru.jeu.Personnage;
import fr.lru.jeu.TypeMonstre;
import fr.lru.jeu.TypePersonnage;

class StrategieTest {

    private Personnage arthas;
    private Monstre golemDePierre;

    @BeforeEach
    public void setUp() {
        arthas = new Personnage("Arthas", 15, 20, 10, TypePersonnage.GUERRIER, "Coup de bouclier");
        golemDePierre = new Monstre("GolemDePierre", 25, 15, 30, TypeMonstre.GOLEM, "Eau", "Physique");
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
	void typeUnknown(){
		Monstre m = new Monstre(
			"Ent1", 100, 20, 2,
			TypeMonstre.UNKNOWN, "", ""
		);

		Personnage p = new Personnage(
			"Ent2", 10, 2, 2,
			TypePersonnage.UNKNOWN, ""
		);

        m.attaquer(p);
        assertEquals(10, p.getHp());
        assertEquals(2, p.getAttaque());
        assertEquals(2, p.getDefense());

		p.attaquer(m);
        assertEquals(100, m.getHp());
        assertEquals(20, m.getAttaque());
        assertEquals(2, m.getDefense());
	}

	@Test
	void attaqueDistance(){
		Personnage p = new Personnage(
			"Ent1", 100, 20, 4,
			TypePersonnage.ARCHER, ""
		);

		Monstre md = new Monstre(
			"Ent2", 41, 10, 2,
			TypeMonstre.DRAGON, "", ""
		);
		Monstre mo = new Monstre(
			"Ent3", 10, 10, 2,
			TypeMonstre.ORC, "", ""
		);
		Monstre mm1 = new Monstre(
			"Ent4", 61, 10, 2,
			TypeMonstre.MORT_VIVANT, "", ""
		);
		Monstre mm2 = new Monstre(
			"Ent4", 60, 10, 15,
			TypeMonstre.MORT_VIVANT, "", ""
		);

		md.attaquer(p, 16);
        assertEquals(94, p.getHp());
        assertEquals(20, p.getAttaque());
        assertEquals(3, p.getDefense());

		mo.attaquer(p, 11);
        assertEquals(86, p.getHp());
        assertEquals(20, p.getAttaque());
        assertEquals(2, p.getDefense());

		mm1.attaquer(p, 21);
        assertEquals(73, p.getHp());
        assertEquals(20, p.getAttaque());
        assertEquals(1, p.getDefense());

		mm2.attaquer(p);
        assertEquals(47, p.getHp());
        assertEquals(20, p.getAttaque());
        assertEquals(0, p.getDefense());
	}
	@Test
	void attaqueMelee(){
		Personnage p = new Personnage(
			"Ent1", 100, 20, 4,
			TypePersonnage.ARCHER, ""
		);

		Monstre md = new Monstre(
			"Ent2", 40, 10, 2,
			TypeMonstre.DRAGON, "", ""
		);
		Monstre mm = new Monstre(
			"Ent3", 61, 10, 2,
			TypeMonstre.MORT_VIVANT, "", ""
		);

		md.attaquer(p);
        assertEquals(82, p.getHp());
        assertEquals(18, p.getAttaque());
        assertEquals(4, p.getDefense());

		mm.attaquer(p, 20);
        assertEquals(64, p.getHp());
        assertEquals(16, p.getAttaque());
        assertEquals(4, p.getDefense());
	}
	@Test
	void attaqueNormale(){
		Personnage p = new Personnage(
			"Ent1", 100, 20, 4,
			TypePersonnage.ARCHER, ""
		);

		Monstre md = new Monstre(
			"Ent2", 41, 10, 2,
			TypeMonstre.DRAGON, "", ""
		);
		Monstre mo = new Monstre(
			"Ent3", 10, 10, 2,
			TypeMonstre.ORC, "", ""
		);
		Monstre mm = new Monstre(
			"Ent4", 60, 10, 16,
			TypeMonstre.MORT_VIVANT, "", ""
		);
		Monstre mg = new Monstre(
			"Ent4", 10, 10, 2,
			TypeMonstre.GOLEM, "", ""
		);
		Monstre ms = new Monstre(
			"Ent4", 10, 10, 2,
			TypeMonstre.SPECTRE, "", ""
		);

		md.attaquer(p);
        assertEquals(98, p.getHp());
        assertEquals(20, p.getAttaque());
        assertEquals(4, p.getDefense());

		mo.attaquer(p);
        assertEquals(96, p.getHp());
        assertEquals(20, p.getAttaque());
        assertEquals(4, p.getDefense());

		mm.attaquer(p);
        assertEquals(94, p.getHp());
        assertEquals(20, p.getAttaque());
        assertEquals(4, p.getDefense());

		mg.attaquer(p);
        assertEquals(92, p.getHp());
        assertEquals(20, p.getAttaque());
        assertEquals(4, p.getDefense());

		ms.attaquer(p);
        assertEquals(90, p.getHp());
        assertEquals(20, p.getAttaque());
        assertEquals(4, p.getDefense());
	}
	@Test
	void monstreAmelioree(){
		Monstre m = new Monstre(
			"Ent1", 100, 10, 10,
			TypeMonstre.DRAGON, "", ""
		);
		MonstreAmeliore ma = new Enrage(new PeauRenforcee(m));

		ma.degat(10, 5, 2);
        assertEquals(90, ma.getHp());
        assertEquals(20, ma.getAttaque());
        assertEquals(13, ma.getDefense());

		m = ma.supprimerAmelioration();
		assertEquals(90, m.getHp());
        assertEquals(5, m.getAttaque());
        assertEquals(18, m.getDefense());
		m = ((MonstreAmeliore) m).supprimerAmelioration();
		assertEquals(90, m.getHp());
        assertEquals(5, m.getAttaque());
        assertEquals(8, m.getDefense());
	}

	@Test
	void defenseSansStrategie(){
		Monstre m = new Monstre(
			"Ent1", 10, 10, 1,
			TypeMonstre.DRAGON, "", ""
		);
		Personnage p = new Personnage(
			"Ent2", 10, 10, 1,
			null, ""
		);

		p.attaquer(m);
        assertEquals(10, m.getHp());
        assertEquals(10, m.getAttaque());
        assertEquals(1, m.getDefense());
	}
	@Test
    void defenseDistance(){
		Monstre m = new Monstre(
			"Ent1", 41, 10, 1,
			TypeMonstre.DRAGON, "", ""
		);

		Personnage pa = new Personnage(
			"Ent2", 10, 10, 1,
			TypePersonnage.ARCHER, ""
		);
		Personnage pg = new Personnage(
			"Ent3", 10, 10, 1,
			TypePersonnage.GUERRIER, ""
		);
		Personnage pm = new Personnage(
			"Ent4", 10, 10, 1,
			TypePersonnage.MAGE, ""
		);

        m.attaquer(pa, 16);

		pa.attaquer(m);
        assertEquals(41, m.getHp());
        assertEquals(10, m.getAttaque());
        assertEquals(1, m.getDefense());

		pg.attaquer(m);
        assertEquals(41, m.getHp());
        assertEquals(10, m.getAttaque());
        assertEquals(1, m.getDefense());

		pm.attaquer(m);
        assertEquals(41, m.getHp());
        assertEquals(10, m.getAttaque());
        assertEquals(1, m.getDefense());
    }
	@Test
    void defenseMelee(){
		Monstre m = new Monstre(
			"Ent1", 40, 10, 10,
			TypeMonstre.DRAGON, "", ""
		);

		Personnage pa = new Personnage(
			"Ent2", 10, 2, 2,
			TypePersonnage.ARCHER, ""
		);
		Personnage pg = new Personnage(
			"Ent3", 10, 2, 2,
			TypePersonnage.GUERRIER, ""
		);
		Personnage pm = new Personnage(
			"Ent4", 10, 2, 2,
			TypePersonnage.MAGE, ""
		);

        m.attaquer(pa);

		pa.attaquer(m);
        assertEquals(25, m.getHp());
        assertEquals(7, m.getAttaque());
        assertEquals(10, m.getDefense());

		pg.attaquer(m);
        assertEquals(24, m.getHp());
        assertEquals(7, m.getAttaque());
        assertEquals(10, m.getDefense());

		pm.attaquer(m);
        assertEquals(23, m.getHp());
        assertEquals(0, m.getAttaque());
        assertEquals(7, m.getDefense());
    }
	@Test
    void defenseNormale(){
		Monstre m = new Monstre(
			"Ent1", 100, 20, 2,
			TypeMonstre.DRAGON, "", ""
		);

		Personnage pa = new Personnage(
			"Ent2", 10, 2, 2,
			TypePersonnage.ARCHER, ""
		);
		Personnage pg = new Personnage(
			"Ent3", 10, 2, 2,
			TypePersonnage.GUERRIER, ""
		);
		Personnage pm = new Personnage(
			"Ent4", 10, 2, 2,
			TypePersonnage.MAGE, ""
		);

        m.attaquer(pa);

		pa.attaquer(m);
        assertEquals(97, m.getHp());
        assertEquals(20, m.getAttaque());
        assertEquals(2, m.getDefense());

		pg.attaquer(m);
        assertEquals(97, m.getHp());
        assertEquals(16, m.getAttaque());
        assertEquals(2, m.getDefense());

		pm.attaquer(m);
        assertEquals(87, m.getHp());
        assertEquals(16, m.getAttaque());
        assertEquals(2, m.getDefense());
    }
	@Test
	void personnageEquipee(){
		Personnage p = new Personnage(
			"Ent1", 100, 10, 2,
			TypePersonnage.ARCHER, ""
		);
		PersonnageEquipe pe = new Potion(new Bouclier(new Epee(p)));

		pe.degat(10, 5, 2);
        assertEquals(110, pe.getHp());
        assertEquals(15, pe.getAttaque());
        assertEquals(5, pe.getDefense());

		p = pe.supprimerEquipement();
		assertEquals(90, p.getHp());
        assertEquals(15, p.getAttaque());
        assertEquals(5, p.getDefense());
		p = ((PersonnageEquipe) p).supprimerEquipement();
		assertEquals(90, p.getHp());
        assertEquals(15, p.getAttaque());
        assertEquals(0, p.getDefense());
		p = ((PersonnageEquipe) p).supprimerEquipement();
		assertEquals(90, p.getHp());
        assertEquals(5, p.getAttaque());
        assertEquals(0, p.getDefense());
	}
}
