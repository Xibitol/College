/**
 *
 * @author P. Rodriguez
 */
public class Personne {

    // Définition des attributs d'une personne
    private int id;
    private String nom;
    private String prenom;
    private char genre;
    private int anneeNaiss;
    private String groupe;
    private int categorie;

    /**
     * Constructeur de personne.
     *
     * @param lid : identifiant de la personne
     * @param leNom : nom de famille
     * @param lePrenom : prénom
     * @param leGenre : 'h', 'f', ...
     * @param lAnnee : année de naissance
     * @param leGroupe : groupe "Bosseur", "Surfeur", ...
     * @param laCategorie : catégorie
     */
    Personne(int lid, String leNom, String lePrenom, char leGenre, int lAnnee, String leGroupe,  int laCategorie) {
        this.id = lid;
        this.nom = leNom;
        this.prenom = lePrenom;
        this.genre = leGenre;
        this.anneeNaiss = lAnnee;
        this.groupe = leGroupe;
        this.categorie = laCategorie;
    }

    /* Les getters */
    public int getId() {
        return this.id;
    }

    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

	/** Returns whether this {@code Personne} is male.
	 * @return True if is a male; false otherwise.
	 * @since 1.1
	 */
	public boolean estHomme(){ return genre == 'h'; }
	/** Returns whether this {@code Personne} is a female.
	 * @return True if is a female; false otherwise.
	 * @since 1.1
	 */
	public boolean estFemme(){ return genre == 'f'; }

    public int getAnneeNaiss() {
        return this.anneeNaiss;
    }
    public String getGroupe() {
        return this.groupe;
    }
    public int getCategorie() {
        return this.categorie;
    }

	/** @since 1.1 */
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Personne p && p.getId() == this.getId();
	}
	/** Calculates the match score with another {@code Personne}.
	 * <p>
	 * <b>Criterias :</b>
	 * <ul type="square">
	 * <li>3 points for the same group.
	 * <li>4 points for the same category; Or 2 points if the difference is of
	 * 1.
	 * <li>4 points if the age difference is less or equal to 2; Or 3 points if
	 * is less or equal to 5; Or 2 points if is less or equal to 8; Or 1 point if
	 * is less or equal to 11.
	 * </ul>
	 * @param other The other {@code Personne}.
	 * @return The match score between this and the other {@code Personne}.
	 * @since 1.2
	 */
	public int matchScore(Personne other){
		int score = this.getGroupe().equals(other.getGroupe()) ? 3 : 0;
		
		switch(Math.abs(this.getCategorie() - other.getCategorie())){
			case 0 -> score += 4;
			case 1 -> score += 2;
		}

		int ageDiff = Math.abs(this.getAnneeNaiss() - other.getAnneeNaiss());
		if(ageDiff <= 2) score += 4;
		else if(ageDiff <= 5) score += 3;
		else if(ageDiff <= 8) score += 2;
		else if(ageDiff <= 11) score += 1;

		return score;
	}

    /* Les setters */
    public void setGroupe(String leGroupe) {
        this.groupe = leGroupe;
    }
    public void setCategorie(int laCategorie) {
        this.categorie = laCategorie;
    }
    
    /* Affichage */
	/** @since 1.1 */
	@Override
	public String toString(){
		return this.prenom + " " + this.nom + ", né.e en "
		+ this.anneeNaiss + ", " + this.groupe + " de catégorie "
		+ this.categorie;
	}

    public void afficher() {
		System.out.println(this);
    }
}