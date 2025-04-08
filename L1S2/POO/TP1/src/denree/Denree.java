package denree;
/**
 * Modèle simple de denrée.
 * Pour information, changer proprement le nom du champ prix en pouicpouic
 * n'affecte en aucun cas l'implémentation. De plus, si nous changeons la porté
 * de ce dernier, private à public, la champ sera accessible à travers le
 * projet. Cela posera un problème si l'on change le nom du champ puisque
 * l'implémentation s'y réfère directement (Penser à refactoriser, et non à
 * modifier).
 * @author jviaud
 */
public class Denree {
    /**
     * La TVA est de 20% pour tous.
     */
    private static final Double TAUX = 0.2;

    /**
     * Nom de la denrée.
     */
    private String nom;

    /**
     * Prix TTC de la denrée.
	 * @since 1.1
     */
    private Double prix;

    /**
     * Accesseur du nom de la denrée.
     *
     * @return le nom de la denrée
     */
    public String getNom() {
        return nom;
    }

    /**
     * Accesseur du prix de la denrée.
     *
     * @return le prix HT.
     */
    public Double getPrixHT() {
        return prix / (1 + TAUX);
    }

    /**
     * Accesseur du prix de la denrée.
     *
     * @return le prix TTC.
     */
    public Double getPrixTTC() {
        return prix;
    }

    /**
     * Mutateur du nom de la denrée.
     *
     * @param nom : nouveau nom.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Mutateur du prix de la denrée.
     *
     * @param prix : nouveau prix HT.
     */
    public void setPrixHT(Double prix) {
        this.prix = prix * (1 + TAUX);
    }

    /**
     * Mutateur du prix de la denrée.
     *
     * @param prix : nouveau prix TTC.
     */
    public void setPrixTTC(Double prix) {
        this.prix = prix;
    }

    /**
     * Description textuelle de la denrée.
     *
     * @return texte relatif à la denrée.
     */
    @Override
    public String toString() {
        return "Denree{" + "nom=" + this.getNom() + 
                ", prixHT=" + this.getPrixHT() + 
                ", prixTTC=" + this.getPrixTTC() + '}';
    }    
}