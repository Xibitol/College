package lru;

public interface Liste{
	
	// ACCESSEURS
	public int taille();
	public default boolean estVide(){ return taille() == 0; }
	public default String getPremier(){ return getValeur(); }
	public Liste getSuivant();
	public String getValeur();

	public boolean trouver(String val);

	// MUTATEURS
	public Liste placerFin(String val);

	// FONCTIONS
	@Override
	public String toString();
}