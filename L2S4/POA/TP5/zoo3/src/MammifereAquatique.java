public class MammifereAquatique implements Mammifere, Aquatique{

	private String nom;
	private boolean carnivore;
	private boolean eauDouce;

	public MammifereAquatique(String nom, boolean carnivore, boolean eauDouce){
		this.nom = nom;
		this.carnivore = carnivore;
		this.eauDouce = eauDouce;
	}

	// GETTERS
	@Override
	public String getNom(){ return nom; }
	@Override
	public boolean isCarnivore(){ return carnivore; }
	@Override
	public boolean isEauDouce(){ return eauDouce; }
}
