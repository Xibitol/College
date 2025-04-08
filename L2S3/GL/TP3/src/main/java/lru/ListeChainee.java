package lru;

public class ListeChainee implements Liste{

	private String valeur;
	private Liste eltSuivant;

	public ListeChainee(String val, Liste liste){
		this.valeur = val;
		this.eltSuivant = liste;
	}

	// ACCESSEURS
	@Override
	public int taille(){ return 1 + eltSuivant.taille(); }
	@Override
	public Liste getSuivant(){ return eltSuivant; }
	@Override
	public String getValeur(){ return valeur; }

	@Override
	public boolean trouver(String val){
		return valeur.equals(val) || eltSuivant.trouver(val);
	}

	// MUTATEURS
	@Override
	public Liste placerFin(String val){
		if(eltSuivant instanceof ListeVide){
			eltSuivant = new ListeChainee(val, eltSuivant);
			return eltSuivant;
		}else
			return eltSuivant.placerFin(val);
	}

	// FONCTIONS
	@Override
	public String toString(){
		return valeur;
	}
}
