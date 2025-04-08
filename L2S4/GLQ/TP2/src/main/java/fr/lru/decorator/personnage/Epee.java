package fr.lru.decorator.personnage;

import fr.lru.jeu.Personnage;

public class Epee extends Equipement{

	private static final int HP = 0;
	private static final int ATTAQUE = 10;
	private static final int DEFENSE = 0;

	public Epee(Personnage personnage){
		super(personnage, HP, ATTAQUE, DEFENSE);
	}
}
