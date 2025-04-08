package lru;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ListeChaineeTest{

	public static final String VALEUR = "Test";
	public static final String VALEUR_SUIVANTE = "tseT%d";

	private ListeChainee listeChainee;
	
	@BeforeEach
	void creerListeVide(){
		listeChainee = new ListeChainee(VALEUR, new ListeVide());
	}

	// TESTS
	@Test
	void testImplementations(){
		assertInstanceOf(Liste.class, listeChainee);
	}

	// Accesseurs
	@Test
	void testTaille(){
		assertEquals(1, listeChainee.taille());
	}
	@Test
	void testEstVide(){
		assertEquals(false, listeChainee.estVide());
	}
	@Test
	void testGetPremier(){
		assertEquals(VALEUR, listeChainee.getPremier());
	}
	@Test
	void testGetSuivant(){
		assertInstanceOf(ListeVide.class,
			listeChainee.getSuivant()
		);
	}
	@Test
	void testGetValeur(){
		assertEquals(VALEUR, listeChainee.getValeur());
	}
	@Test
	void testTrouver(){
		assertFalse(listeChainee.trouver(new String()));
		assertTrue(listeChainee.trouver(VALEUR));

		listeChainee.placerFin(VALEUR_SUIVANTE);
		assertTrue(listeChainee.trouver(VALEUR_SUIVANTE));
	}

	// Mutateurs
	@Test
	void testPlacerFin(){
		int nombreMod = 0;
		Liste lv = listeChainee.getSuivant();
		String valPrecedent = listeChainee.getValeur();
		Liste lcPrecedent = listeChainee;

		while(nombreMod < 2){
			nombreMod++;
			String valSuivant = VALEUR_SUIVANTE.formatted(nombreMod);
			Liste lcSuivant = listeChainee.placerFin(valSuivant);

			assertEquals(nombreMod + 1, listeChainee.taille());

			assertEquals(valPrecedent, lcPrecedent.getPremier());
			assertEquals(valPrecedent, lcPrecedent.getValeur());
			assertEquals(lcSuivant, lcPrecedent.getSuivant());

			assertInstanceOf(ListeChainee.class, lcSuivant);
			assertEquals(valSuivant, lcSuivant.getPremier());
			assertEquals(valSuivant, lcSuivant.getValeur());

			assertEquals(lv, lcSuivant.getSuivant());

			lcPrecedent = lcSuivant;
			valPrecedent = valSuivant;
		}
	}
	// Fonctions
	@Test
	void testToString(){
		assertEquals(VALEUR, listeChainee.toString());
	}
}
