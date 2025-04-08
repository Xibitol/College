package fr.lru.strategy;

import fr.lru.jeu.Monstre;
import fr.lru.jeu.Personnage;

public class AttaqueNulle implements MonstreStrategie{

	// FUNCTIONS
	@Override
	public void attaquer(Monstre monstre, Personnage victime){
		// Does nothing.
	}
}