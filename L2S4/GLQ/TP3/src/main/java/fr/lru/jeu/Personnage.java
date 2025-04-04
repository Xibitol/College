package fr.lru.jeu;

import java.util.Objects;

import fr.lru.observer.Observable;
import fr.lru.strategy.AttaqueMelee;
import fr.lru.strategy.AttaqueNormale;

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

	// SETTERS
	public void attaquer(Monstre monstre){
		if(Objects.isNull(monstre.getStrategie())) return;

		switch(getType()){
			case GUERRIER:
				switch(monstre.getStrategie()){
					case AttaqueMelee __:
						monstre.degat(getAttaque()*7/monstre.getDefense());
						break;
					case AttaqueNormale __:
						monstre.degat(
							getAttaque()*2/(monstre.getDefense()*3),
							getAttaque()*2, 0
						);
						break;
					default:
						break;
				}
				break;
			case MAGE:
				switch(monstre.getStrategie()){
					case AttaqueMelee __:
						monstre.degat(
							getAttaque()*10/(monstre.getDefense()*2),
							10, 3
						);
						break;
					case AttaqueNormale __:
						monstre.degat(10);
						break;
					default:
						break;
				}
				break;
			case ARCHER:
				switch(monstre.getStrategie()){
					case AttaqueMelee __:
						monstre.degat(15, 3, 0);
						break;
					case AttaqueNormale __:
						monstre.degat(getAttaque()*6/(monstre.getDefense()*2));
						break;
					default:
						break;
				}
				break;
		}
	}

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
