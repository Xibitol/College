package dev.pimous.l2s4gl.fvsw.game;

import dev.pimous.l2s4gl.fvsw.observer.Observable;

public class Monster extends Entity{

	public static final String ENTITY_ID = "Monstre";

	private MonsterType type;
	private String resistance;
	private String weakness;

	public Monster(String name, int health, int defense, int attack,
	MonsterType type, String resistance, String weakness
	){
		super(name, health, defense, attack);

		this.type = type;
		this.resistance = resistance;
		this.weakness = weakness;
	}

	// GETTERS
	public MonsterType getType(){ return type; }
	public String getResistance(){ return resistance;}
	public String getWeakness(){ return weakness;}

	// FUNCTIONS
	@Override
	public void update(Observable observable, Object object){
		System.out.println("Monster %s received %s event.".formatted(
			getName(), object
		));
	}
}
