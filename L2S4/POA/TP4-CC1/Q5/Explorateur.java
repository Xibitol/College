import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Explorateur{
	
	// FUNCTIONS
	public List<String> listeParNom(Repertoire repertoire){
		ArrayList<Noeud> noeuds = repertoire.donneElementsFils();
		noeuds.sort(Comparator.naturalOrder());

		return noeuds.stream().map(n -> n.donneNom()).toList();
	}
	public List<String> listeParTaille(Repertoire repertoire){
		ArrayList<Noeud> noeuds = repertoire.donneElementsFils();
		noeuds.sort(Comparator.comparingLong(Noeud::donneTaille));

		return noeuds.stream().map(Noeud::donneNom).toList();
	}
}
