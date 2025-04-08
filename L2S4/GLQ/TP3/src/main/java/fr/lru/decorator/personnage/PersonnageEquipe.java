package fr.lru.decorator.personnage;

import fr.lru.jeu.Personnage;
import fr.lru.jeu.TypePersonnage;
import fr.lru.observer.Observable;

public abstract class PersonnageEquipe extends Personnage{

	private Personnage personnage;

	protected PersonnageEquipe(Personnage personnage,
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

	// SETTERS
	@Override
	public void degat(int health, int attack, int defense){
		super.degat(
			Math.max(0, health - personnage.getHp()),
			Math.max(0, attack - personnage.getAttaque()),
			Math.max(0, defense - personnage.getDefense())
		);

		personnage.degat(health, attack, defense);
	}

	// LISTENERS
	@Override
	public void update(Observable observable, Object object){
		personnage.update(observable, object);
	}
}
