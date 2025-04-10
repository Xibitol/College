package fr.lru.strategy;

import fr.lru.jeu.Monstre;
import fr.lru.jeu.Personnage;

public class AttaqueMelee implements MonstreStrategie{
	
	// FUNCTIONS
	@Override
	public void attaquer(Monstre monstre, Personnage victime){
		victime.degat(
			monstre.getAttaque()*15/(victime.getDefense()*2),
			2, 0
		);
	}
}
