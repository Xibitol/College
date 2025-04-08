package dev.pimous.l2s3gl.tp1;

/**
 * @author Xibitol;
 */
public final class Reptile extends Animal{
	
	private boolean venimeux;

	public Reptile(int identifiant,
		String race,
		String dateDeNaiss,
		float prix,
		boolean venimeux
	){
		super(identifiant, race, dateDeNaiss, prix);

		this.venimeux = venimeux;
	}
	public Reptile(String race, String dateDeNaiss, float prix,
		boolean venimeux
	){
		this(Animal.getProchainIdentifiant(), race, dateDeNaiss, prix,
			venimeux
		);
	}

	// GETTERS
	public boolean estVenimeux(){ return venimeux; }
}
