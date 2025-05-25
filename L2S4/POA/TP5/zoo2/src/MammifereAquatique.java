public class MammifereAquatique extends Mammifere implements Aquatique{

	private boolean eauDouce;

	public MammifereAquatique(String nom, boolean carnivore, boolean eauDouce){
		super(nom, carnivore);

		this.eauDouce = eauDouce;
	}

	// GETTERS
	public boolean isEauDouce(){ return eauDouce; }
}
