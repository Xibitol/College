package dev.pimous.l2s4gl.fvsw.game;

import dev.pimous.l2s4gl.fvsw.observer.Observable;

public class Character extends Entity{

	public static final String ENTITY_ID = "Personnage";

	private CharacterType type;
	private String skill;

	public Character(String name, int health, int defense, int attack,
		CharacterType type, String skill
	){
		super(name, health, defense, attack);

		this.type = type;
		this.skill = skill;
	}

	// GETTERS
	public CharacterType getType(){ return type; }
	public String getSkill(){ return skill; }

	// FUNCTIONS
	@Override
	public void update(Observable observable, Object object){
		System.out.printf("Character %s received %s event.\n",
			getName(), object
		);
	}
}
