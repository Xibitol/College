package fr.lru.jeu;

import java.util.Objects;
import java.util.logging.Logger;

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
					case AttaqueMelee am:
						monstre.degat(getAttaque()*7/monstre.getDefense());
						break;
					case AttaqueNormale an:
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
					case AttaqueMelee am:
						monstre.degat(
							getAttaque()*10/(monstre.getDefense()*2),
							10, 3
						);
						break;
					case AttaqueNormale an:
						monstre.degat(10);
						break;
					default:
						break;
				}
				break;
			case ARCHER:
				switch(monstre.getStrategie()){
					case AttaqueMelee am:
						monstre.degat(15, 3, 0);
						break;
					case AttaqueNormale an:
						monstre.degat(getAttaque()*6/(monstre.getDefense()*2));
						break;
					default:
						break;
				}
				break;
			default:
				break;
		}
	}

	// FUNCTIONS
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof Personnage p)) return false;

		return super.entiteEquals(p)
			&& getType().equals(p.getType())
			&& getSkill().equals(p.getSkill());
	}
	@Override
	public int hashCode(){
		return getNom().hashCode();
	}

	// LISTENERS
	@Override
	public void update(Observable observable, Object object){
		Logger log = observable instanceof GameEventManager gem ?
			gem.getLogger() : Logger.getGlobal();

			log.info(() -> "Character %s received %s event.".formatted(
				getNom(), object
			));
	}
}
