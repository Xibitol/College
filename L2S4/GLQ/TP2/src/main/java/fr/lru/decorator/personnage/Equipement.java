package fr.lru.decorator.personnage;

import fr.lru.jeu.Personnage;
import fr.lru.jeu.TypePersonnage;
import fr.lru.observer.Observable;

public abstract class Equipement extends Personnage{

	private Personnage personnage;

	protected Equipement(Personnage personnage,
		int health, int attack, int defense
	){
		super(null,
			health, attack, defense,
			null, null
		);

		this.personnage = personnage;
	}

	// GETTERS
	public int getHpAjoute(){ return super.getHp(); }
	public int getAttaqueAjoute(){ return super.getAttaque(); }
	public int getDefenseAjoute(){ return super.getDefense(); }

	@Override
	public String getNom(){ return personnage.getNom(); }
	@Override
	public int getHp(){
		return personnage.getHp() + getHpAjoute();
	}
	@Override
	public int getAttaque(){
		return personnage.getAttaque() + getAttaqueAjoute();
	}
	@Override
	public int getDefense(){
		return personnage.getDefense() + getDefenseAjoute();
	}
	@Override
	public TypePersonnage getType(){ return personnage.getType(); }
	@Override
	public String getSkill(){ return personnage.getSkill(); }

	public Personnage supprimerEquipement(){ return personnage; }

	// LISTENERS
	@Override
	public void update(Observable observable, Object object){
		personnage.update(observable, object);
	}
}
