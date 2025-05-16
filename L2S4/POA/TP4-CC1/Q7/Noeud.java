import java.util.ArrayList;

public abstract class Noeud implements Comparable<Noeud>{

	private String nom;
	private Noeud parent = null;

	protected Noeud(String nom){
		this.nom = nom;
	}

	// GETTERS
	public String donneNom(){ return nom; }
	public abstract long donneTaille();
	public Noeud donneParent(){ return parent; }
	public abstract ArrayList<Noeud> donneElementsFils();

	// SETTERS
	protected final void definiParent(Noeud parent){
		this.parent = parent;
	}
	public abstract boolean ajouteElt(Noeud nouveau);
	public abstract boolean supprimeElt(Noeud existant);

	// FUNCTIONS
	@Override
	public int compareTo(Noeud o){
		return donneNom().compareTo(o.donneNom());
	}
	@Override
	public boolean equals(Object obj){
		return obj instanceof Noeud n
			&& donneNom().equals(n.donneNom());
	}
}