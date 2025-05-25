public class MammifereTerrestre implements Mammifere, Terrestre{

	private String nom;
	private boolean carnivore;
	private String habitat;

	public MammifereTerrestre(String nom, boolean carnivore, String habitat){
		this.nom = nom;
		this.carnivore = carnivore;
		this.habitat = habitat;
	}

	// GETTERS
	@Override
	public String getNom(){ return nom; }
	@Override
	public boolean isCarnivore(){ return carnivore; }
	@Override
	public String getHabitat(){ return habitat; }
}
