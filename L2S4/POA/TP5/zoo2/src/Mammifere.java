public abstract non-sealed class Mammifere extends Animal{
	
	private boolean carnivore;

	protected Mammifere(String nom, boolean carnivore){
		super(nom);

		this.carnivore = carnivore;
	}

	// GETTERS
	public boolean isCarnivore(){ return carnivore; };
}
