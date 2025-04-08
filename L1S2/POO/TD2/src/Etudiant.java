public class Etudiant extends AutoToString{
	
	private final Integer NUMERO;
	private final String NOM;
	private String prenom;
	private Cours majeur;
	private Cours mineur;
	private String mention;

	public Etudiant(Integer numero, String nom, String prenom){
		this.NUMERO = numero;
		this.NOM = nom;
		this.prenom = prenom;
	}
	public Etudiant(
		Integer numero, String nom, String prenom,
		String mention, Cours majeur, Cours mineur
	){
		this(numero, nom, prenom);
		this.mention = mention;
		this.majeur = majeur;
		this.mineur = mineur;
	}

	// Getters
	public Integer getNumero(){ return NUMERO; }
	public String getNom(){ return NOM; }
	public String getPrenom(){ return prenom; }
	public Cours getMajeur(){ return majeur; }
	public Cours getMineur(){ return mineur; }
	public String getMention(){ return mention; }

	public Boolean memeCours(Etudiant that){
		return this.getMajeur().getIntitule().equals(
			that.getMajeur().getIntitule()
		) && this.getMineur().getIntitule().equals(
			that.getMineur().getIntitule()
		);
	}

	// Setters
	public void setPrenom(String prenom){ this.prenom = prenom; }
	public void SInscrire(Cours majeur, Cours mineur){
		this.majeur = majeur;
		this.mineur = mineur;
	}
	public void SInscrire(
		String intituleMaj, Integer ectsMaj,
		String intituleMin, Integer ectsMin
	){
		SInscrire(
			new Cours(intituleMaj, ectsMaj),
			new Cours(intituleMin, ectsMin)
		);
	}
	public void setMention(String mention){ this.mention = mention; }

	// Functions
	public String mesProfs(){
		return this.getMajeur().getEnseignant().getNom() + " "
			+ this.getMineur().getEnseignant().getNom();
	}
}