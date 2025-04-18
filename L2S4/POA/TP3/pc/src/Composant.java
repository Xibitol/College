public abstract sealed class Composant
	permits Alimentation, Processeur, Memoire, DisqueDur
{
	
	private long identifiant;
	private float prix;

	protected Composant(long identifiant, float prix){
		this.identifiant = identifiant;
		this.prix = prix;
	}

	// GETTERS
	public long getIdentifiant(){ return identifiant; }
	public float getPrix(){ return prix; }
	public abstract double donneEnergie();
}
