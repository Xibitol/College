package lru;

public class ListeVide implements Liste{

	// ACCESSEURS
	@Override
	public int taille(){ return 0; }
	@Override
	public Liste getSuivant(){ return null; }
	@Override
	public String getValeur(){ return null; }

	@Override
	public boolean trouver(String val){ return false; }

	// MUTATEURS
	@Override
	public Liste placerFin(String val){
		throw new UnsupportedOperationException(
			"Impossible de placer une valeur à la suite d'une liste vide."
		);
	}

	// FUNCTIONS
	@Override
	public String toString(){
		return "";
	}
}
