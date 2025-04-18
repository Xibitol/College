public non-sealed class DisqueDur extends Composant implements Consommeur{
	
	private double consommeur;
	private long capacite;

	public DisqueDur(long identifiant, float prix,
		double consommeur, long capacite
	){
		super(identifiant, prix);

		this.consommeur = consommeur;
		this.capacite = capacite;
	}

	// GETTERS
	@Override
	public double getConsommation(){ return consommeur; }
	public long getCapacite(){ return capacite; }
	@Override
	public double donneEnergie(){ return -getConsommation(); }
}
