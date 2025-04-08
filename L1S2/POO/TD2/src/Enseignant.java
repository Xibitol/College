public class Enseignant extends AutoToString {
	private final String NOM;
	private final String PRENOM;
	private Cours cours;

	public Enseignant(String nom, String prenom){
		this.NOM = nom;
		this.PRENOM = prenom;
	}

	// Getters
	public String getNom(){ return NOM; }
	public String getPrenom(){ return PRENOM; }
	public Cours getCours(){ return cours; }

	// Setters
	public void setCours(Cours cours){
		this.cours = cours;
		this.cours.setEnseignant(this);
	}
}
