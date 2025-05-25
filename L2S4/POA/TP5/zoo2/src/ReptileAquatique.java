public class ReptileAquatique extends Reptile implements Aquatique{

	private boolean eauDouce;

	public ReptileAquatique(String nom, boolean venimeux, boolean eauDouce){
		super(nom, venimeux);

		this.eauDouce = eauDouce;
	}

	// GETTERS
	public boolean isEauDouce(){ return eauDouce; }
}
