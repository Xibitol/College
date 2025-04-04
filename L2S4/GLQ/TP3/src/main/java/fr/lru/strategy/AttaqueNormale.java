package fr.lru.strategy;

import fr.lru.jeu.Monstre;
import fr.lru.jeu.Personnage;

public class AttaqueNormale implements MonstreStrategie{
	
	// FUNCTIONS
	@Override
	public void attaquer(Monstre monstre, Personnage victime){
		victime.degat(10/victime.getDefense());
	}
}
