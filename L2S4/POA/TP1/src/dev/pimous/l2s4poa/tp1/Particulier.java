package dev.pimous.l2s4poa.tp1;

public class Particulier extends Client{

	private String prenom;
	
	public Particulier(String nom, String prenom, String adresse){
		super(nom, adresse);

		this.prenom = prenom;
	}

	// GETTERS
	public String donnePrenom(){ return prenom; }
}
