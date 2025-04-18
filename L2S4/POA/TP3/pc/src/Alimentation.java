public non-sealed class Alimentation extends Composant{
	
	private double puissance;

	public Alimentation(long identifiant, float prix, double puissance){
		super(identifiant, prix);

		this.puissance = puissance;
	}

	// GETTERS
	public double getPuissance(){ return puissance; }
	@Override
	public double donneEnergie(){ return getPuissance(); }
}
