package fr.lru.jeu;

import dev.pimous.javautils.AutoToString;
import fr.lru.observer.Observer;

public abstract class Entite extends AutoToString implements Observer{

	private String name;
	private int health;
	private int defense;
	private int attack;

	public Entite(String name, int health, int attack, int defense){
		this.name = name;
		this.health = health;
		this.defense = defense;
		this.attack = attack;
	}

	// GETTERS
	public String getNom(){ return name; }
	public int getHp(){ return health; }
	public int getAttaque(){ return attack; }
	public int getDefense(){ return defense; }

	// LISTENERS
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Entite e)) return false;

		return getNom().equals(e.getNom())
			&& getHp() == e.getHp()
			&& getAttaque() == e.getAttaque()
			&& getDefense() == e.getDefense();
	}
}