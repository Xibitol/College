public class ReptileAquatique implements Reptile, Aquatique{

	private String nom;
	private boolean venimeux;
	private boolean eauDouce;

	public ReptileAquatique(String nom, boolean venimeux, boolean eauDouce){
		this.nom = nom;
		this.venimeux = venimeux;
		this.eauDouce = eauDouce;
	}

	// GETTERS
	@Override
	public String getNom(){ return nom; }
	@Override
	public boolean isVenimeux(){ return venimeux; }
	@Override
	public boolean isEauDouce(){ return eauDouce; }
}
