package fr.lru.jeu;

import fr.lru.observer.Observable;

public class Personnage extends Entite{

	public static final String ID_STRING = "Personnage";

	private TypePersonnage type;
	private String skill;

	public Personnage(String name, int health, int attack, int defense,
		TypePersonnage type, String skill
	){
		super(name, health, attack, defense);

		this.type = type;
		this.skill = skill;
	}

	// GETTERS
	public TypePersonnage getType(){ return type; }
	public String getSkill(){ return skill; }

	// FUNCTIONS
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof Personnage p)) return false;

		return super.equals(p)
			&& getType().equals(p.getType())
			&& getSkill().equals(p.getSkill());
	}

	// LISTENERS
	@Override
	public void update(Observable observable, Object object){
		System.out.printf("Character %s received %s event.\n",
			getNom(), object
		);
	}
}
