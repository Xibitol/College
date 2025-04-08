package fr.lru.decorator.personnage;

import fr.lru.jeu.Personnage;

public class Potion extends PersonnageEquipe{
	
	private static final int HP = 20;
	private static final int ATTAQUE = 0;
	private static final int DEFENSE = 0;

	public Potion(Personnage personnage){
		super(personnage, HP, ATTAQUE, DEFENSE);
	}
}
