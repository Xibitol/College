import java.util.ArrayList;

public abstract class Noeud implements Comparable<Noeud>{

	private String nom;

	protected Noeud(String nom){
		this.nom = nom;
	}

	// GETTERS
	public String donneNom(){ return nom; }
	public abstract ArrayList<Noeud> donneElementsFils();

	// SETTERS
	public abstract boolean ajouteElt(Noeud nouveau);
	public abstract boolean supprimeElt(Noeud existant);

	// FUNCTIONS
	@Override
	public int compareTo(Noeud o){
		return donneNom().compareTo(o.donneNom());
	}
}