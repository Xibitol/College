package fr.lru.jeu;

import fr.lru.observer.Observable;

public class Monstre extends Entite{

	public static final String ID_STRING = "Monstre";

	private TypeMonstre type;
	private String resistance;
	private String weakness;

	public Monstre(String name, int health, int attack, int defense,
		TypeMonstre type, String weakness, String resistance
	){
		super(name, health, attack, defense);

		this.type = type;
		this.resistance = resistance;
		this.weakness = weakness;
	}

	// GETTERS
	public TypeMonstre getType(){ return type; }
	public String getResistance(){ return resistance; }
	public String getWeakness(){ return weakness; }

	// FUNCTIONS
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof Monstre m)) return false;

		return super.equals(m)
			&& getType().equals(m.getType())
			&& getResistance().equals(m.getResistance())
			&& getWeakness().equals(m.getWeakness());
	}

	// LISTENERS
	@Override
	public void update(Observable observable, Object object){
		System.out.println("Monster %s received %s event.".formatted(
			getNom(), object
		));
	}
}
