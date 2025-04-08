/** Représentation d'un guichet automatique et utilitaires aux comptes. Il
 * détient une réserve d'argent liquide qui fluctue en fonction des retraits et
 * des dépots.
 * @version 1.0.0
 * @author Xibitol
 * @see Compte
 * @see Utilisateur
 */
public class GuichetAutomatique extends AutoToString {

	/** Réserve du guichet automatique. */
	private Double reserve = 1000.;

	// SETTERS
	/** Effectue un retrait sur le guichet automatique. Le portefeuille de
	 * l'{@link Utilisateur} empoche le {@code montant} retiré du
	 * {@link Compte} et la réserve du guichet diminue.
	 * @param c Le compte débité.
	 * @param u L'{@link Utilisateur} empochant.
	 * @param montant Le montant du retrait.
	 * @see depot
	 */
	public void retrait(Compte c, Utilisateur u, Double montant){
		c.retrait(montant);
		reserve -= montant;

		u.empoche(montant);
	}
	/** Effectue un retrait sur le guichet automatique. Le portefeuille de
	 * l'{@link Utilisateur} empoche le {@code montant} retiré de son
	 * {@link Compte} courant et la réserve du guichet diminue.
	 * @param u L'{@link Utilisateur} débitant et empochant.
	 * @param montant Le montant du retrait.
	 * @see depot(Utilisateur, Double)
	 */
	public void retrait(Utilisateur u, Double montant){
		retrait(u.getCourant(), u, montant);
	}

	/** Effectue un dépôt sur le guichet automatique. Le portefeuille de
	 * l'{@link Utilisateur} dépense le {@code montant} crédité au
	 * {@link Compte} et la réserve du guichet augmente.
	 * @param c Le compte crédité.
	 * @param u L'{@link Utilisateur} dépensant.
	 * @param montant Le montant du dépôt.
	 * @see retrait
	 */
	public void depot(Compte c, Utilisateur u, Double montant){
		u.depense(montant);

		c.depot(montant);
		reserve += montant;
	}
	/** Effectue un dépôt sur le guichet automatique. Le portefeuille de
	 * l'{@link Utilisateur} dépense le {@code montant} crédité à son
	 * {@link Compte} courant et la réserve du guichet augmente.
	 * @param u L'{@link Utilisateur} dépensant et créditant.
	 * @param montant Le montant du dépôt.
	 * @see retrait(Utilisateur, Double)
	 */
	public void depot(Utilisateur u, Double montant){
		depot(u.getCourant(), u, montant);
	}
	
	// FUNCTIONS
	/** Effectue un virement entre deux {@link Compte}s.
	 * @param debit Le {@link Compte} débité.
	 * @param credit Le {@link Compte} crédité.
	 * @param montant Le montant du virement.
	 */
	public void virement(Compte debit, Compte credit, Double montant){
		debit.retrait(montant);
		credit.depot(montant);
	}
	/** Effectue un virement entre deux {@link Utilisateur}s. Depuis les comptes
	 * {@link Compte}s courants.
	 * @param debit L'{@link Utilisateur} débité.
	 * @param credit L'{@link Utilisateur} crédité.
	 * @param montant Le montant du virement.
	 */
	public void virement(Utilisateur debit, Utilisateur credit, Double montant){
		virement(debit.getCourant(), credit.getCourant(), montant);
	}
	/** Effectue un virement du {@link Compte} épargne au {@link Compte}
	 * courant.
	 * @param u L'{@link Utilisateur} sujet au virement.
	 * @param montant Le montant du virement
	 * @see virementEpargne
	 */
	public void virement(Utilisateur u, Double montant){
		virement(u.getEpargne(), u.getCourant(), montant);
	}
	/** Effectue un virement du {@link Compte} courant au {@link Compte}
	 * épargne.
	 * @param u L'{@link Utilisateur} sujet au virement.
	 * @param montant Le montant du virement
	 * @see virement
	 */
	public void virementEpargne(Utilisateur u, Double montant){
		u.getCourant().retrait(montant);
		u.getEpargne().depot(montant);
	}
}