package fr.lru.decorator.monstre;

import fr.lru.jeu.Monstre;

public class Enrage extends MonstreAmeliore{

	private static final int ATTAQUE = 15;
	private static final int DEFENSE = -5;

	public Enrage(Monstre monstre){
		super(monstre, ATTAQUE, DEFENSE);
	}
}
