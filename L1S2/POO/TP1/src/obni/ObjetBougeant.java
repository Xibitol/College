package obni;

/** Représentation d'un objet bougeant non identifié (OBNI ou UMO en anglais).
 * @version 1.0.0
 * @author Xibitol
 */
public class ObjetBougeant extends AutoToString {

	private static final Double DELTA_R = 5.;
	
	/** Nom de l'OBNI. */
	private String nom;
	/** Position en abscisse de l'OBNI. */
	private Integer x;
	/** Position en ordonnée de l'OBNI. */
	private Integer y;
	/** Rayon de l'OBNI. */
	private Double r;

	// GETTERS
	/** Accesseur du {@code nom} de l'OBNI.
	 * @return Le nom de l'OBNI.
	 */
	public String getNom(){ return nom; }
	/** Accesseur de la position en abscisse {@code x} de l'OBNI.
	 * @return Position en abscisse de l'OBNI.
	 */
	public Integer getX(){ return x; }
	/** Accesseur de la position en ordonnée {@code y} de l'OBNI.
	 * @return Position en ordonnée de l'OBNI.
	 */
	public Integer getY(){ return y; }
	/** Accesseur du {@code rayon} de l'OBNI.
	 * @return Rayon de l'OBNI.
	 */
	public Double getR(){ return r; }

	/** Indique si l'OBNI est un point. C.a.d si il a un {@code rayon} de 0.
	 * @return Vrai s'il est un point, faux sinon.
	 */
	public Boolean estPoint(){ return this.getR().equals(0.); }

	/** Indique si l'OBNI est en collision avec un autre OBNI.
	 * @param that L'autre OBNI
	 * @return Vrai s'ils sont en collision, faux sinon.
	 */
	private Boolean collision(ObjetBougeant that){
		return Math.sqrt(
			Math.pow(this.getX() - that.getY(), 2)
			+ Math.pow(this.getY() - that.getY(), 2)
		) <= this.getR() + that.getR();
	}

	// SETTERS
	/** Mutateur du nom de l'OBNI.
	 * @param nom Nouveau nom de l'OBNI.
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/** Mutateur de la position en abscisse de l'OBNI.
	 * @param x Nouvelle position en abscisse de l'OBNI.
	 */
	public void setX(Integer x) {
		this.x = x;
	}
	/** Mutateur de la position en ordonnée de l'OBNI.
	 * @param y Nouvelle position en ordonnée de l'OBNI.
	 */
	public void setY(Integer y) {
		this.y = y;
	}
	/** Mutateur du rayon de l'OBNI.
	 * @param r Nouveau rayon de l'OBNI.
	 */
	public void setR(Double r) {
		this.r = r;
	}

	/** Déplace l'OBNI d'une certaine distance en abscisse et d'une certaine
	 * distance en ordonnée.
	 * @param dx Distance en abscisse.
	 * @param dy Distance en ordonnée.
	 */
	public void deplace(Integer dx, Integer dy){
		this.setX(this.getX() + dx);
		this.setY(this.getY() + dy);
	}
	/** Augmente le {@code rayon} de l'OBNI d'un rayon fixe. */
	public void grossir(){
		this.setR(this.getR() + DELTA_R);
	}
	/** Augmente le {@code rayon} de l'OBNI d'un certain rayon.
	 * @param delta Rayon du grossissement.
	 */
	public void grossir(Double delta){
		this.setR(this.getR() + delta);
	}
	/** Diminue le {@code rayon} de l'OBNI d'un rayon fixe. */
	public void retrecir(){
		this.setR(this.getR() - DELTA_R);
	}
	/** Diminue le {@code rayon} de l'OBNI d'un certain rayon.
	 * @param delta Rayon du rétrécissement.
	 */
	public void retrecir(Double delta){
		this.setR(this.getR() - delta);
	}

	// FUNCTIONS
	/** Absorbe un autre OBNI s'ils sont en collision. Cet OBNI grossira du
	 * {@code rayon} de l'autre OBNI et puis ce dernier perdra son {@code rayon}
	 * qui sera mit à {@code 0}.
	 * @param that L'autre OBNI absorbé.
	 * @throws IllegalArgumentException Si l'autre OBNI est identique à
	 * l'absorbant.
	 */
	public void absorbe(ObjetBougeant that){
		if(that.equals(this))
			throw new IllegalArgumentException(
				"L'autre OBNI ne peut pas être identique à l'absorbant."
			);
		else if(this.collision(that)){
			this.grossir(that.getR());
			that.setR(0.);
		}
	}
	/** Se fait absober par un autre OBNI s'ils sont en collision. L'autre OBNI
	 * grossira du {@code rayon} de cet OBNI et puis ce dernier perdra son
	 * {@code rayon} qui sera mit à {@code 0}.
	 * @param that L'autre OBNI absorbant.
	 * @throws IllegalArgumentException Si l'autre OBNI est identique à
	 * l'absorbé.
	 */
	public void estAbsorbe(ObjetBougeant that){
		try{
			that.absorbe(this);
		}catch(IllegalArgumentException ignored){
			throw new IllegalArgumentException(
				"L'autre OBNI ne peut pas être identique à l'absorbé"
			);
		}
	}

	/** Produit un nouvel OBNI enfant. Il sera positionné juste derrière en
	 * diagonal de cet OBNI parent dont son {@code rayon} est la moitié de
	 * ce dernier.
	 * @return Le nouvel OBNI enfant.
	 */
	public ObjetBougeant pond(){
		ObjetBougeant child = new ObjetBougeant();
		child.setR(this.getR()/2);

		child.setX(Double.valueOf(
			this.getX() - this.getR() - 1 - child.getR()
		).intValue());
		child.setY(Double.valueOf(
			this.getY() - this.getR() - 1 - child.getR()
		).intValue());

		return child;
	}
	/** Produit un nouvel OBNI enfant. Il aura pour position et {@code rayon} la
	 * somme des caractéristiques de ses deux parents, cet OBNI et l'autre OBNI.
	 * Si les deux parents sont le même OBNI, aucune erreur se produit - rien
	 * n'est dit que les OBNI ne peuvent pas pondre sans un autre.
	 * @param that Le deuxième parent OBNI du nouvel OBNI.
	 * @return Le nouvel OBNI enfant.
	 */
	public ObjetBougeant pond(ObjetBougeant that){
		ObjetBougeant child = new ObjetBougeant();
		child.setR(this.getR() + that.getR());
		child.setX(this.getX() + that.getX());
		child.setY(this.getY() + that.getY());
		return child;
	}
}
