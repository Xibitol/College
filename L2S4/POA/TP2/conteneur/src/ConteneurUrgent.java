public class ConteneurUrgent extends Conteneur{

	private int poidsMax;

	public ConteneurUrgent(int distance, int volumeMax, int poidsMax){
		super(distance, volumeMax);

		this.poidsMax = poidsMax;
	}
	
	// GETTERS
	@Override
	public boolean conditionChargement(Colis c){
		return super.conditionChargement(c)
			&& donnePoids() + c.donnePoids() <= poidsMax;
	}
	@Override
	public int cout(){
		return super.cout()*2;
	}
}
