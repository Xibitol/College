import java.util.ArrayList;

public class Fichier extends Noeud{

	public Fichier(String nom){
		super(nom);
	}

	// GETTERS
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
