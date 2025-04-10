package fr.lru.strategy;

import fr.lru.jeu.Monstre;
import fr.lru.jeu.Personnage;

public class AttaqueDistante implements MonstreStrategie{
	
	// FUNCTIONS
	@Override
	public void attaquer(Monstre monstre, Personnage victime){
		victime.degat(
			monstre.getAttaque()*8/(victime.getDefense()*3),
			0, 1
		);
	}
}