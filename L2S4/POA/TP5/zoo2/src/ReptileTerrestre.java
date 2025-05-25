public class ReptileTerrestre extends Reptile implements Terrestre{

	private String habitat;

	public ReptileTerrestre(String nom, boolean venimeux, String habitat){
		super(nom, venimeux);

		this.habitat = habitat;
	}

	// GETTERS
	@Override
	public String getHabitat(){ return habitat; }
}
