package dev.pimous.l2s4gl.fvsw.game;

import dev.pimous.javautils.AutoToString;
import dev.pimous.l2s4gl.fvsw.observer.Observer;

public abstract class Entity extends AutoToString implements Observer{

	private String name;
	private int health;
	private int defense;
	private int attack;

	public Entity(String name, int health, int defense, int attack){
		this.name = name;
		this.health = health;
		this.defense = defense;
		this.attack = attack;
	}

	// GETTERS
	public String getName(){ return name; }
	public int getHealth(){ return health; }
	public int getDefense(){ return defense; }
	public int getAttack(){ return attack; }
}