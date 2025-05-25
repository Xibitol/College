/**
 * Classe représentant un mammifere terrestre caracterise par :
 * - un nom d'espece,
 * - un indicateur booleen precisant si le mammifere est carnivore
 * - un nom d'habitat (biotope).
 */
public class MammifereTerrestre extends Mammifere implements Terrestre{

	private String habitat;

	public MammifereTerrestre(String nom, boolean carnivore, String habitat){
		super(nom, carnivore);

		this.habitat = habitat;
	}

	// GETTERS
	@Override
	public String getHabitat(){ return habitat; }
}
