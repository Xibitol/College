import java.util.ArrayList;

public class Fichier extends Noeud{

	private long taille; // In bytes

	public Fichier(String nom, long taille){
		super(nom);

		this.taille = taille;
	}

	// GETTERS
	@Override
	public long donneTaille(){ return taille; }
	@Override
	public ArrayList<Noeud> donneElementsFils(){
		return new ArrayList<>();
	}

	// SETTERS
	@Override
	public boolean ajouteElt(Noeud nouveau){
		throw new UnsupportedOperationException();
	}
	@Override
	public boolean supprimeElt(Noeud existant){
		throw new UnsupportedOperationException();
	}
}
