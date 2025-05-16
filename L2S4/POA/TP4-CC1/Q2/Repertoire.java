import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class Repertoire extends Noeud{

	private final SortedSet<Noeud> fils = new TreeSet<>();

	public Repertoire(String nom){
		super(nom);
	}

	// GETTERS
	@Override
	public long donneTaille() {
		return fils.stream().reduce(0L,
			(taille, n) -> taille + n.donneTaille(),
			Long::sum
		);
	}
	@Override
	public ArrayList<Noeud> donneElementsFils(){
		// For security; Nobody should modify this list outside of this class.
		return new ArrayList<>(fils);
	}

	// SETTERS
	@Override
	public boolean ajouteElt(Noeud nouveau){
		return !equals(nouveau) && fils.add(nouveau);
	}
	@Override
	public boolean supprimeElt(Noeud existant) {
		return fils.remove(existant);
	}
}
