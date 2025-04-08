/** Représente un utilisateur du système bancaire. Il est identifié par un nom,
 * est associté un {@link Compte} courant et épargne et détient une somme
 * d'argent dans un portefeuille.
 * @version 1.0.0
 * @author Xibitol
 * @see Compte
 * @see GuichetAutomatique
 */
public class Utilisateur extends AutoToString {

	/** Nom de l'utilisateur. */
	private final String nom;
	/** {@link Compte} courant de l'utilisateur. */
	private Compte courant;
	/** {@link Compte} épargne de l'utilisateur. */
	private Compte epargne;
	/** Somme d'argent dans le portefeuille de l'utilisateur. */
	private Double portefeuille = 0.;

	/** Créé un {@code Utilisateur} avec pour portefeuille 0, sans comptes
	 * bancaires.
	 * @param nom Nom.
	 */
	public Utilisateur(String nom){
		this.nom = nom;
	}

	// GETTERS
	/** Accesseur du nom de l'utilisateur.
	 * @return Le nom.
	 */
	public String getNom(){ return nom; }
	/** Accesseur du {@link Compte} courant de l'utilisateur.
	 * @return Le {@link Compte} courant.
	 */
	public Compte getCourant(){ return courant; }
	/** Accesseur du {@link Compte} epargne de l'utilisateur.
	 * @return Le {@link Compte} epargne.
	 */
	public Compte getEpargne(){ return epargne; }

	// SETTERS
	/** Mutateur du {@link Compte} courant de l'utilisateur. Définit aussi
	 * l'utilisateur principal du {@link Compte} à lui même.
	 * @param compte Nouveau {@link Compte} courant.
	 */
	public void setCompteCourantPrincipal(Compte compte){
		this.courant = compte;
		compte.setPrincipal(this);
	}
	/** Mutateur du {@link Compte} courant de l'utilisateur. Définit aussi
	 * l'utilisateur secondaire du {@link Compte} à lui même.
	 * @param compte Nouveau {@link Compte} courant.
	 */
	public void setCompteCourantSecondaire(Compte compte){
		this.courant = compte;
		compte.setSecondaire(this);
	}
	/** Mutateur du {@link Compte} épargne de l'utilisateur. Définit aussi
	 * l'utilisateur principal du {@link Compte} à lui même.
	 * @param compte Nouveau {@link Compte} épargne.
	 */
	public void setCompteEpargnePrincipal(Compte compte){
		this.epargne = compte;
		compte.setPrincipal(this);
	}
	/** Mutateur du {@link Compte} épargne de l'utilisateur. Définit aussi
	 * l'utilisateur secondaire du {@link Compte} à lui même.
	 * @param compte Nouveau {@link Compte} épargne.
	 */
	public void setCompteEpargneSecondaire(Compte compte){
		this.epargne = compte;
		compte.setSecondaire(this);
	}

	/** Dépense un certain {@code montant} depuis le portefeuille de
	 * l'utilisateur.
	 * @param montant Montant à soustraire.
	 */
	public void depense(Double montant){
		this.portefeuille -= montant;
	}
	/** Empoche un certain {@code montant} depuis le portefeuille de
	 * l'utilisateur.
	 * @param montant Montant à additionner.
	 */
	public void empoche(Double montant){
		this.portefeuille += montant;
	}
}