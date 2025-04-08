package fr.lru.jeu;

import fr.lru.observer.Observable;
import fr.lru.strategy.AttaqueDistante;
import fr.lru.strategy.AttaqueMelee;
import fr.lru.strategy.AttaqueNormale;
import fr.lru.strategy.MonstreStrategie;

public class Monstre extends Entite{

	public static final String ID_STRING = "Monstre";

	private TypeMonstre type;
	private String resistance;
	private String weakness;

	private MonstreStrategie strategie = null;

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

	public MonstreStrategie getStrategie(){ return strategie; }

	// SETTERS
	public void attaquer(Personnage personnage, float distance){
		strategie = switch(getType()){
			case DRAGON:
				if(getHp() > 40 && distance > 15) yield new AttaqueDistante();
				else if(getHp() <= 40) yield new AttaqueMelee();
				else yield new AttaqueNormale();
			case ORC:
				if(distance > 10) yield new AttaqueDistante();
				else yield new AttaqueNormale();
			case MORT_VIVANT:
				if(getHp() > 60){
					if(distance > 20) yield new AttaqueDistante();
					else yield new AttaqueMelee();
				}else{
					if(getDefense() > 15) yield new AttaqueNormale();
					else yield new AttaqueDistante();
				}
			default:
				yield new AttaqueNormale();
		};

		strategie.attaquer(this, personnage);
	}
	public void attaquer(Personnage personnage){
		attaquer(personnage, 0f);
	}

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
