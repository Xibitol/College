package dev.pimous.l2s4sdi.td1tp1.vegetal;

import dev.pimous.l2s4sdi.td1tp1.RecursiveFenetre;

public class VegetalFenetre extends RecursiveFenetre<Vegetal>{

	// CONSTRUCTEUR
    public VegetalFenetre(String titre, int largeur, int hauteur){
        super(titre, largeur, hauteur, VegetalFenetre::getNewDefaultVegetal);
    }

	// SETTERS
	private static Vegetal getNewDefaultVegetal(int x, int y){
		return new Vegetal(x, y);
	}
}

