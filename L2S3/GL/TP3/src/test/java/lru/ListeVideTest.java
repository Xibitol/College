package lru;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ListeVideTest{

	public static final String VALEUR_VIDE = "";

	private ListeVide listeVide;
	
	@BeforeEach
	void creerListeVide(){
		listeVide = new ListeVide();
	}

	// TESTS
	@Test
	void testImplementations(){
		assertInstanceOf(Liste.class, listeVide);
	}

	// Accesseurs
	@Test
	void testTaille(){
		assertEquals(0, listeVide.taille());
	}
	@Test
	void testEstVide(){
		assertEquals(true, listeVide.estVide());
	}
	@Test
	void testGetPremier(){
		assertNull(listeVide.getPremier());
	}
	@Test
	void testGetSuivant(){
		assertNull(listeVide.getSuivant());
	}
	@Test
	void testGetValeur(){
		assertNull(listeVide.getValeur());
	}
	@Test
	void testTrouver(){
		assertFalse(listeVide.trouver(""));
	}

	// Mutateurs
	@Test
	void testPlacerFin(){
		assertThrowsExactly(UnsupportedOperationException.class,
			() -> listeVide.placerFin("")
		);
	}

	// Fonctions
	@Test
	void testToString(){
		assertEquals(VALEUR_VIDE, listeVide.toString());
	}
}
