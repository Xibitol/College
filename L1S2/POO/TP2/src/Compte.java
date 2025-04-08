import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/** Représente un compte bancaire. Il est identifié par un numero, lié à un
 * {@link Utilisateur} principal et secondaire et détient un montant d'argent.
 * @version 1.0.0
 * @author Xibitol
 * @see Utilisateur
 * @see GuichetAutomatique
 */
public class Compte extends AutoToString {

	/** Numéro du compte. */
	private final Integer numero;
	/** {@link Utilisateur} principal du compte. */
	private Utilisateur principal;
	/** {@link Utilisateur} secondaire du compte. */
	private Utilisateur secondaire;
	/** Montant d'argent du compte. */
	private Double montant = 0.;

	/** Créé un {@code Compte} bancaire avec pour montant 0, sans utilisateurs.
	 * @param numero Numéro.
	 */
	public Compte(Integer numero){
		this.numero = numero;
	}

	/** Créé un {@code Compte} bancaire sans utilisateur secondaire.
	 * @param numero Numéro.
	 * @param principal {@link Utilisateur} principal.
	 * @param montant Montant d'argent.
	 */
	public Compte(Integer numero, Utilisateur principal, Double montant){
		this(numero);
		this.principal = principal;
		this.montant = montant;
	}

	// GETTERS
	/** Accesseur du numéro du compte.
	 * @return Le numéro.
	 */
	public Integer getNumero(){ return numero; }
	/** Accesseur de l'{@link Utilisateur} principal du compte.
	 * @return L'{@link Utilisateur} principal.
	 */
	public Utilisateur getPrincipal(){ return principal; }
	/** Accesseur de l'{@link Utilisateur} secondaire du compte.
	 * @return  L'{@link Utilisateur} secondaire.
	 */
	public Utilisateur getSecondaire(){ return secondaire; }
	/** Accesseur du montant d'argent du compte.
	 * @return Montant d'argent.
	 */
	public Double consulte(){ return montant; }

	// SETTERS
	/** Mutateur de l'{@link Utilisateur} principal du compte.
	 * @param principal Nouveau {@link Utilisateur} principal.
	 */
	public void setPrincipal(Utilisateur principal){
		this.principal = principal;
	}
	/** Mutateur de l'{@link Utilisateur} secondaire du compte.
	 * @param secondaire Nouveau {@link Utilisateur} secondaire.
	 */
	public void setSecondaire(Utilisateur secondaire){
		this.secondaire = secondaire;
	}
	/** Dépose une certaine {@code somme} au montant d'argent du compte.
	 * @param somme Somme à additionner.
	 */
	public void depot(Double somme){
		this.montant += somme;
	}
	/** Retire une certaine {@code somme} au montant d'argent du compte.
	 * @param somme Somme à soustraire.
	 */
	public void retrait(Double somme){
		this.montant -= somme;
	}

	// FUNCTIONS
	@Override
	public String toString() {
		return super.createString(
			(Field[]) Arrays.stream(
				this.getClass().getDeclaredFields()
			).filter(f -> List.of("numero", "montant").contains(
				f.getName()
			)).toArray(Field[]::new)
		);
	}
}