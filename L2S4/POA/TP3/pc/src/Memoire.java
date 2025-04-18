public non-sealed class Memoire extends Composant
	implements Consommeur, Cadence
{
	
	private double consommation;
	private double frequence;

	public Memoire(long identifiant, float prix,
		double consommation, double frequence
	){
		super(identifiant, prix);

		this.consommation = consommation;
		this.frequence = frequence;
	}

	// GETTERS
	@Override
	public double getConsommation(){ return consommation; }
	@Override
	public double getFrequence(){ return frequence; }
	@Override
	public double donneEnergie(){ return -getConsommation(); }
}
