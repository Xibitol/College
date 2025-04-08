public class Cours{
	
	private final String INTITULE;
	private Integer ects;
	private Enseignant ens;

	public Cours(String intitule, Integer ects){
		this.INTITULE = intitule;
		this.ects = ects;
	}

	// Getters
	public String getIntitule(){ return INTITULE; }
	public Integer getECTS(){ return ects; }
	public Enseignant getEnseignant(){ return ens; }

	// Setters
	public void setECTS(Integer ects){
		this.ects = ects;
	}
	public void setEnseignant(Enseignant ens){
		this.ens = ens;
	}

	// Functions
	@Override
	public String toString() {
		return "Cours{intitule=" + INTITULE + ", ects=" + ects + ", ens="
			+ (ens != null ? ens.getNom() : ens) + "}";
	}
}
