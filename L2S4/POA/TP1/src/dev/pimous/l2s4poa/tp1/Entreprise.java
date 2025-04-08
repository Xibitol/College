package dev.pimous.l2s4poa.tp1;

public class Entreprise extends Client{

	private int numSIRET;
	
	public Entreprise(String nom, int numSIRET, String adresse){
		super(nom, adresse);

		this.numSIRET = numSIRET;
	}

	// GETTERS
	public long donneNumSIRET(){ return numSIRET; }
}