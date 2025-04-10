package fr.lru.decorator.personnage;

import fr.lru.jeu.Personnage;

public class Bouclier extends PersonnageEquipe{
	
	private static final int HP = 0;
	private static final int ATTAQUE = 0;
	private static final int DEFENSE = 5;

	public Bouclier(Personnage personnage){
		super(personnage, HP, ATTAQUE, DEFENSE);
	}
}
