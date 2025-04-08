package fr.lru;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import fr.lru.decorator.monstre.Enrage;
import fr.lru.decorator.monstre.MonstreAmeliore;
import fr.lru.decorator.monstre.PeauRenforcee;
import fr.lru.decorator.personnage.Bouclier;
import fr.lru.decorator.personnage.Epee;
import fr.lru.decorator.personnage.PersonnageEquipe;
import fr.lru.exception.FormatInvalideException;
import fr.lru.fabrique.Fabrique;
import fr.lru.jeu.GameEventManager;
import fr.lru.jeu.Monstre;
import fr.lru.jeu.Personnage;
import fr.lru.jeu.TypeMonstre;
import fr.lru.jeu.TypePersonnage;
import fr.lru.mock.MockHandler;

class SQTest{

	@Test
	void testUnusedGetters(){
		PersonnageEquipe pe = new Epee(new Personnage(
			"Ent1", 0, 0, 0,
			TypePersonnage.ARCHER, "s"
		));
		assertEquals("Ent1", pe.getNom());
		assertEquals(TypePersonnage.ARCHER, pe.getType());
		assertEquals("s", pe.getSkill());

		MonstreAmeliore ma = new Enrage(new Monstre(
			"Ent2", 0, 0, 0,
			TypeMonstre.DRAGON, "w", "r"
		));
		assertEquals("Ent2", ma.getNom());
		assertEquals(TypeMonstre.DRAGON, ma.getType());
		assertEquals("w", ma.getWeakness());
		assertEquals("r", ma.getResistance());
	}

	@Test
	void testPersonnageEquals(){
		Personnage p1 = new Personnage(
			"Ent1", 0, 0, 0,
			TypePersonnage.ARCHER, ""
		);
		Personnage p11 = new Personnage(
			"Ent1", 1, 0, 0,
			TypePersonnage.ARCHER, ""
		);
		Personnage p12 = new Personnage(
			"Ent1", 0, 1, 0,
			TypePersonnage.ARCHER, ""
		);
		Personnage p13 = new Personnage(
			"Ent1", 0, 0, 1,
			TypePersonnage.ARCHER, ""
		);
		Personnage p14 = new Personnage(
			"Ent1", 0, 0, 0,
			TypePersonnage.GUERRIER, ""
		);
		Personnage p15 = new Personnage(
			"Ent1", 0, 0, 0,
			TypePersonnage.ARCHER, "null"
		);
		Personnage p2 = new Personnage(
			"Ent2", 0, 0, 0,
			TypePersonnage.ARCHER, ""
		);

		assertEquals(p1, p1);
		assertNotEquals(p1, -1);
		assertNotEquals(p1, p11);
		assertNotEquals(p1, p12);
		assertNotEquals(p1, p13);
		assertNotEquals(p1, p14);
		assertNotEquals(p1, p15);
		assertNotEquals(p1, p2);
	}
	@Test
	void testMonstreEquals(){
		Monstre m1 = new Monstre(
			"Ent1", 0, 0, 0,
			TypeMonstre.DRAGON, "", ""
		);
		Monstre m11 = new Monstre(
			"Ent1", 0, 0, 0,
			TypeMonstre.GOLEM, "", ""
		);
		Monstre m12 = new Monstre(
			"Ent1", 0, 0, 0,
			TypeMonstre.DRAGON, "null", ""
		);
		Monstre m13 = new Monstre(
			"Ent1", 0, 0, 0,
			TypeMonstre.DRAGON, "", "null"
		);
		Monstre m2 = new Monstre(
			"Ent2", 0, 0, 0,
			TypeMonstre.DRAGON, "", ""
		);

		assertEquals(m1, m1);
		assertNotEquals(m1, -1);
		assertNotEquals(m1, m11);
		assertNotEquals(m1, m12);
		assertNotEquals(m1, m13);
		assertNotEquals(m1, m2);
	}
	@Test
	void testPersonnageEquipeEquals(){
		Personnage p = new Personnage(
			"Ent1", 0, 0, 0,
			TypePersonnage.ARCHER, ""
		);
		Personnage pbis = new Personnage(
			"Ent2", 0, 0, 0,
			TypePersonnage.ARCHER, ""
		);

		PersonnageEquipe pe1 = new Bouclier(p);
		PersonnageEquipe pe11 = new Epee(p);
		PersonnageEquipe pe2 = new Bouclier(pbis);

		assertEquals(pe1, pe1);
		assertNotEquals(pe1, -1);
		assertNotEquals(pe1, pe11);
		assertNotEquals(pe1, pe2);
	}
	@Test
	void testMonstreAmelioreEquals(){
		Monstre m = new Monstre(
			"Ent1", 0, 0, 0,
			TypeMonstre.DRAGON, "", ""
		);
		Monstre mbis = new Monstre(
			"Ent2", 0, 0, 0,
			TypeMonstre.DRAGON, "", ""
		);

		MonstreAmeliore ma1 = new Enrage(m);
		MonstreAmeliore ma11 = new PeauRenforcee(m);
		MonstreAmeliore ma2 = new Enrage(mbis);

		assertEquals(ma1, ma1);
		assertNotEquals(ma1, -1);
		assertNotEquals(ma1, ma11);
		assertNotEquals(ma1, ma2);
	}

	@Test
	void testHashcode(){
		String name = "Test";

		Personnage p = new Personnage(
			name, 0, 0, 0,
			null, null
		);
		Monstre m = new Monstre(
			name, 0, 0, 0,
			null, null, null
		);
		PersonnageEquipe pe = new Bouclier(p);
		MonstreAmeliore ma = new Enrage(m);

		assertEquals(p.hashCode(), name.hashCode());
		assertEquals(m.hashCode(), m.hashCode());
		assertEquals(pe.hashCode(), pe.hashCode());
		assertEquals(ma.hashCode(), ma.hashCode());
	}

	@Test
	void testGameEventManager(){
		Logger l = Logger.getGlobal();
		GameEventManager gem = new GameEventManager(l);

		MockHandler mh = new MockHandler();
		l.addHandler(mh);

		gem.genererEvenement("Test");
		assertEquals("⚡ Événement du jeu : Test",
			mh.getLastMessage()
		);

		gem.start();
		synchronized(this){
			assertDoesNotThrow(() -> wait(500));
		}

		gem.stop();
		assertEquals("🛑 La boucle d'événements a été arrêtée.",
			mh.getLastMessage()
		);
	}
	@Test
	void testObserverAddRem(){
		GameEventManager gem = new GameEventManager(Logger.getGlobal());
		Personnage p = new Personnage(
			"Ent1", 0, 0, 0,
			TypePersonnage.ARCHER, ""
		);

		assertTrue(gem.ajouterObserver(p));
		assertFalse(gem.ajouterObserver(p));
		assertFalse(gem.supprimerObserver(null));
		assertTrue(gem.supprimerObserver(p));
		assertFalse(gem.supprimerObserver(p));
	}
	@Test
	void testObserverUpdate(){
		Logger l = Logger.getGlobal();
		GameEventManager gem = new GameEventManager(l);

		MockHandler mh = new MockHandler();
		l.addHandler(mh);

		Personnage p = new Personnage(
			"", 0, 0, 0,
			null, null
		);
		PersonnageEquipe pe = new Epee(p);
		Monstre m = new Monstre(
			"", 0, 0, 0,
			null, null, null
		);
		MonstreAmeliore ma = new Enrage(m);

		p.update(null, "Test");
		assertEquals("Character  received Test event.",
			mh.getLastMessage()
		);
		p.update(gem, "Test");
		assertEquals("Character  received Test event.",
			mh.getLastMessage()
		);

		pe.update(null, "Test");
		assertEquals("Character  received Test event.",
			mh.getLastMessage()
		);
		pe.update(gem, "Test");
		assertEquals("Character  received Test event.",
			mh.getLastMessage()
		);

		m.update(null, "Test");
		assertEquals("Monster  received Test event.",
			mh.getLastMessage()
		);
		m.update(gem, "Test");
		assertEquals("Monster  received Test event.",
			mh.getLastMessage()
		);

		ma.update(null, "Test");
		assertEquals("Monster  received Test event.",
			mh.getLastMessage()
		);
		ma.update(gem, "Test");
		assertEquals("Monster  received Test event.",
			mh.getLastMessage()
		);
	}

	@Test
	void testFrabriqueReader(){
		Fabrique f = Fabrique.getInstance();

		assertThrowsExactly(FileNotFoundException.class,
			() -> f.fromResource("notfound.txt")
		);
		assertThrowsExactly(OutOfMemoryError.class,
			() -> f.fromResource("toowide.txt")
		);
		assertThrowsExactly(FormatInvalideException.class,
			() -> f.fromResource("invalid.txt")
		);

		assertDoesNotThrow(() -> {
			assertEquals(2, f.fromResource("4096.txt").size());
			assertEquals(
				9,
				f.fromResource("characters_and_monsters.txt").size()
			);
		});
	}
}
