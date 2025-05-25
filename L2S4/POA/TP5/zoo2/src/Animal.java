public abstract sealed class Animal permits Mammifere, Reptile{
	
	private String nom;

	protected Animal(String nom){
		this.nom = nom;
	}

	// GETTERS
	public String getNom(){ return nom; }
}
