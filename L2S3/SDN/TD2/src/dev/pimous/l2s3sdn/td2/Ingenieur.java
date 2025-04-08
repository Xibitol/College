package dev.pimous.l2s3sdn.td2;

import dev.pimous.javautils.AutoToString;

public class Ingenieur extends AutoToString{
	
	private String nom;
	private String service;

	public Ingenieur(String nom, String service){
		this.nom = nom;
		this.service = service;
	}

	// GETTERS
	public String getNom(){ return nom; }
	public String getService(){ return service; }
}
