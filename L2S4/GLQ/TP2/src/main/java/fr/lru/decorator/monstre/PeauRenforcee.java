package fr.lru.decorator.monstre;

import fr.lru.jeu.Monstre;

public class PeauRenforcee extends Amelioration{

	private static final int ATTAQUE = 0;
	private static final int DEFENSE = 10;

	public PeauRenforcee(Monstre monstre){
		super(monstre, ATTAQUE, DEFENSE);
	}
}
