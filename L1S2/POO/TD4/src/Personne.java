public class Personne {

	private int numero;
	private String nom;
	private String prenom;
	private int age;
	
	public Personne(int numero, String nom, String prenom, int age){
		this.numero = numero;
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
	}

	// Getters
	public int getNumero(){ return numero; }
	public String getNom(){ return nom; }
	public String getPrenom(){ return prenom; }
	public int getAge(){ return age; }

	// Functions
	@Override
	public String toString() {
		return getNom() + " " + getPrenom();
	}
}
