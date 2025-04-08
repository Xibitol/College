package dev.pimous.l2s3sdn.td4;

import dev.pimous.javautils.AutoToString;

public class Personne extends AutoToString{

    private String nom;

    public Personne(String nom) {
        this.nom = nom;
    }

	// GETTERS
    public String getNom() {
        return nom;
    }

	// SETTERS
    public void setNom(String nom) {
        this.nom = nom;
    }
}