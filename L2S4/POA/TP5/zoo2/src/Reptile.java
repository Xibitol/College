public abstract non-sealed class Reptile extends Animal{
	
	private boolean venimeux;

	protected Reptile(String nom, boolean venimeux){
		super(nom);

		this.venimeux = venimeux;
	}

	// GETTERS
	public boolean isVenimeux(){ return venimeux; }
}
