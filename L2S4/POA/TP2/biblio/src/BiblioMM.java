import java.util.ArrayList;
import java.util.List;

/**
 * La classe BiblioMM fournit un moyen de stocker des objets
 * CD et DVD. Une liste de tous les CD et DVD peut etre affichee
 * en mode texte.
 * 
 */
public class BiblioMM
{
    private ArrayList<EltMM> elts = new ArrayList<>();

    /**
     * Construit une bibliotheque vide.
     */
    public BiblioMM()
    {
    }

	// GETTERS
	public ArrayList<EltMM> rechercherTitre(String titre){
		return new ArrayList<>(elts.stream()
			.filter(e -> e.donneTitre().equals(titre))
			.toList()
		);
	}
	public EltMM emprunterTitre(String titre){
		List<EltMM> eltsSearch = rechercherTitre(titre);

		if(eltsSearch.size() != 1 || !eltsSearch.get(0).donneEtatRayon())
			return null;

		elts.removeIf(e -> e.donneTitre().equals(titre));

		return eltsSearch.get(0);
	}
	public boolean rendreTitre(EltMM elt){
		return elts.add(elt);
	}

	
	// SETTERS
    public boolean ajouter(EltMM eltMM){ return this.elts.add(eltMM); }

    /**
     * Ajoute un CD a la bibliotheque.
     * @param unCD Le CD a ajouter.
	 * @deprecated
     */
    public void ajouterCD(CD unCD)
    {
        this.elts.add(unCD);
    }

    /**
     * Ajoute un DVD a la bibliotheque.
     * @param unDVD Le DVD a ajouter.
	 * @deprecated
     */
    public void ajouterDVD(DVD unDVD)
    {
        this.elts.add(unDVD);
    }

	// FUNCTIONS
    /**
     * Affiche une liste de tous les CD et DVD actuellement dans
     * la bibliotheque.
     */
    public void affiche()
    {
        for(EltMM eltMM : this.elts) {
            System.out.println(eltMM);
        }
    }
}
