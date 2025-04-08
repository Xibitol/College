import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 *
 * @author jviaud
 */
public class CollectionPersonnes {
    private ArrayList<Personne> groupe;
    
    public CollectionPersonnes() throws IOException {
        LecteurFichier lf = new LecteurFichier();
        lf.choisirFichier("desPersonnes.txt");
        groupe = lf.lecture();
    }

	// GETTERS
	/** Counts {@see Personne}(s) with the same age.
	 * @param anneeNaiss {@see Personne}'s age to search for.
	 * @return Number of {@see Personne} with the same age.
	 * @since 1.1
	 */
	public int effectifDeLAnnee(int anneeNaiss){
		return (int) groupe.stream()
			.filter(p -> p.getAnneeNaiss() == anneeNaiss).count();
	}

	/** Searches for the {@see Personne} with the best match score.
	 * @param pers Other {@see Personne} used to calculate the match score.
	 * @return The {@see Personne} with the best match score; Null if the
	 * collection is empty.
	 * @since 1.1
	 */
	public Personne laPlusProche(Personne pers){
		return groupe.stream().filter(p -> !p.equals(pers)).max(
			Comparator.comparingInt((p) -> p.matchScore(pers))
		).get();
	}

	/** Searches for {@see Personne}s with the best match score.
	 * @param pers Other {@see Personne} used to calculate the match score.
	 * @return A list of {@see Personne}s with the best match score.
	 * @since 1.2
	 */
	public ArrayList<Personne> lesPlusProches(Personne pers){
		return new ArrayList<>(
			groupe.stream().filter(p -> !p.equals(pers) &&
				laPlusProche(pers).matchScore(pers) == p.matchScore(pers)
			).collect(
				Collectors.toList()
			)
		);
	}

	/** Searches for {@see Personne}s in the specified {@code group}.
	 * @param group Searching group.
	 * @return A list of {@see Personne}s in the specified {@code group}.
	 * @since 1.3
	 */
	public ArrayList<Personne> personnesGroupe(String group){
		return new ArrayList<>(
			groupe.stream().filter(p -> p.getGroupe().equals(group)).collect(
				Collectors.toList()
			)
		);
	}

	// FUNCTIONS
    public void afficher(){
		System.out.println(groupe.stream().map(p -> p.toString()).collect(
			Collectors.joining(
				";\n\t", 
				"Personne du groupe :\n\t", 
				"."
			)
		));
	}
}