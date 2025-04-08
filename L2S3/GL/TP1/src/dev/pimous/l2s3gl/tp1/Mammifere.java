package dev.pimous.l2s3gl.tp1;

/**
 * @author Xibitol;
 */
public final class Mammifere extends Animal{
	
	private boolean poilsLong;

	public Mammifere(int identifiant,
		String race,
		String dateDeNaiss,
		float prix,
		boolean poilsLong
	){
		super(identifiant, race, dateDeNaiss, prix);

		this.poilsLong = poilsLong;
	}
	public Mammifere(String race, String dateDeNaiss, float prix,
		boolean poilsLong
	){
		this(Animal.getProchainIdentifiant(), race, dateDeNaiss, prix,
			poilsLong
		);
	}

	// GETTERS
	public boolean estVenimeux(){ return poilsLong; }
}
