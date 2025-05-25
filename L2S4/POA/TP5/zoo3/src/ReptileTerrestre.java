public class ReptileTerrestre implements Reptile, Terrestre{

	private String nom;
	private boolean venimeux;
	private String habitat;

	public ReptileTerrestre(String nom, boolean venimeux, String habitat){
		this.nom = nom;
		this.venimeux = venimeux;
		this.habitat = habitat;
	}

	// GETTERS
	@Override
	public String getNom(){ return nom; }
	@Override
	public boolean isVenimeux(){ return venimeux; }
	@Override
	public String getHabitat(){ return habitat; }
}
