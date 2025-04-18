public non-sealed class Processeur extends Composant
	implements Consommeur, Cadence
{
	
	private double consommation;
	private double frequence;
	private String socket;

	public Processeur(long identifiant, float prix,
		double consommation, double frequence,
		String socket
	){
		super(identifiant, prix);

		this.consommation = consommation;
		this.frequence = frequence;
		this.socket = socket;
	}

	// GETTERS
	@Override
	public double getConsommation(){ return consommation; }
	@Override
	public double getFrequence(){ return frequence; }
	public String getSocket(){ return socket; }
	@Override
	public double donneEnergie(){ return -getConsommation(); }
}
