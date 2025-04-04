package fr.lru.strategy;

import fr.lru.jeu.Monstre;
import fr.lru.jeu.Personnage;

public interface MonstreStrategie{

	// FUNCTIONS
	public void attaquer(Monstre monstre, Personnage victime);
}