package fr.lru.jeu;

import dev.pimous.javautils.AutoToString;
import fr.lru.observer.Observer;

public abstract class Entite extends AutoToString implements Observer{

	private String name;
	private int health;
	private int attack;
	private int defense;

	public Entite(String name, int health, int attack, int defense){
		this.name = name;
		this.health = health;
		this.attack = attack;
		this.defense = defense;
	}

	// GETTERS
	public String getNom(){ return name; }
	public int getHp(){ return health; }
	public int getAttaque(){ return attack; }
	public int getDefense(){ return defense; }

	// SETTERS
	public void degat(int health, int attack, int defense){
		this.health -= Math.min(this.health, health);
		this.attack -= Math.min(this.attack, attack);
		this.defense -= Math.min(this.defense, defense);
	}
	public void degat(int health){
		degat(health, 0, 0);
	}

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