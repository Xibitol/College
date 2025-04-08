package dev.pimous.l2s4poa.tp1;

import java.util.ArrayList;

import dev.pimous.javautils.AutoToString;

public class Client extends AutoToString{

	private String nom;
	private String adresse;
	private ArrayList<CompteBancaire> comptes = new ArrayList<>();

	public Client(String nom, String adresse){
		this.nom = nom;
		this.adresse = adresse;
	}

	// GETTERS
	public String donneNom(){ return nom; }
	public String donneAdresse(){ return adresse; }
	public ArrayList<CompteBancaire> donneComptes(){
		return new ArrayList<CompteBancaire>(comptes);
	}

	// SETTERS
	public boolean ajouteCompte(CompteBancaire compte){
		return !comptes.contains(compte) && comptes.add(compte);
	}
	public boolean supprimeCompte(int numCompte){
		return comptes.removeIf(cb -> cb.donneNumero() == numCompte);
	}
}
