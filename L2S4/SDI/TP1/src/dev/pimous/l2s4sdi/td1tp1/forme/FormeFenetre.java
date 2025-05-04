package dev.pimous.l2s4sdi.td1tp1.forme;

import dev.pimous.l2s4sdi.td1tp1.RecursiveFenetre;

public class FormeFenetre extends RecursiveFenetre<Forme>{

	public FormeFenetre(String titre, int largeur, int longueur){
		super(titre, largeur, longueur, FormeFenetre::getNewDefaultForme);
	}

	// GETTERS
	private static Forme getNewDefaultForme(int x, int y){
		return new Forme(x, y);
	}
}