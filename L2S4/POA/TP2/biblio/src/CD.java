/**
 * La classe CD represente un objet CD. 
 * 
 */
public class CD extends EltMM
{
    private int nbPistes;

    /**
     * Initialise un CD.
     * 
     * @param unTitre Le titre du CD.
     * @param unArtiste Le nom de l'artiste du CD.
     * @param pistes Le nombre de pistes du CD.
     * @param uneDuree La duree du CD.
     */
    public CD(String unTitre, String unArtiste, int pistes, int uneDuree)
    {
        super(unTitre, unArtiste, uneDuree);

        this.nbPistes = pistes;
    }

    /**
     * Donne le nom de l'artiste
     * 
     * @return nom de l'artiste
	 * @deprecated
     */
    public String donneArtiste(){ return super.donneAuteur(); }

    /**
     * Donne le nombre de pistes du CD.
     * 
     * @return nombre de pistes
     */
    public int donneNbPistes()
    {
        return this.nbPistes;
    }

    /**
     * Renvoie une description textuelle de l'objet
     *
     * @return un chaine de caracteres decrivant l'objet
     */
    public String toString()
    {
		StringBuilder sb = new StringBuilder(super.toString());

		sb.append("\tPistes: %d\n".formatted(nbPistes));

        return sb.toString().replace("Auteur", "Artiste");
    }
}
