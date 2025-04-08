package fr.lru.decorator.monstre;

import fr.lru.jeu.Monstre;
import fr.lru.jeu.TypeMonstre;
import fr.lru.observer.Observable;

public abstract class MonstreAmeliore extends Monstre{
	
	private Monstre monstre;

	public MonstreAmeliore(Monstre monstre, int attack, int defense){
		super(null,
			0, attack, defense,
			null, null, null
		);

		this.monstre = monstre;
	}

	// GETTERS
	public int getAttaqueAjoute(){ return super.getAttaque(); }
	public int getDefenseAjoute(){ return super.getDefense(); }

	@Override
	public String getNom(){ return monstre.getNom(); }
	@Override
	public int getHp(){ return monstre.getHp(); }
	@Override
	public int getAttaque(){
		return monstre.getAttaque() + getAttaqueAjoute();
	}
	@Override
	public int getDefense(){
		return monstre.getDefense() + getDefenseAjoute();
	}
	@Override
	public TypeMonstre getType(){ return monstre.getType(); }
	@Override
	public String getResistance(){ return monstre.getResistance(); }
	@Override
	public String getWeakness(){ return monstre.getWeakness(); }

	public Monstre supprimerAmelioration(){ return monstre; }

	// SETTERS
	@Override
	public void degat(int health, int attack, int defense){
		super.degat(
			Math.max(0, health - monstre.getHp()),
			Math.max(0, attack - monstre.getAttaque()),
			Math.max(0, defense - monstre.getDefense())
		);

		monstre.degat(health, attack, defense);
	}

	// LISTENERS
	@Override
	public void update(Observable observable, Object object){
		monstre.update(observable, object);
	}
}
