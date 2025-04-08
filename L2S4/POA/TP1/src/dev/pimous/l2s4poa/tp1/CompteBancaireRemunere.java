package dev.pimous.l2s4poa.tp1;

public class CompteBancaireRemunere extends CompteBancaire{
	
	public static double interetParDefaut = 3;

	private double tauxInteret = interetParDefaut;

	public CompteBancaireRemunere(
		int numero, double soldeInitial, double tauxInteret, Client c
	){
		super(numero, soldeInitial, c);

		this.tauxInteret = tauxInteret;
	}
	public CompteBancaireRemunere(int numero, double soldeInitial, Client c){
		super(numero, soldeInitial, c);
	}
	public CompteBancaireRemunere(int numero, Client c){
		super(numero, c);
	}
	public CompteBancaireRemunere(
		double soldeInitial, double tauxInteret, Client c
	){
		super(soldeInitial, c);

		this.tauxInteret = tauxInteret;
	}

	// SETTERS
	public void crediterInteretMensuel(){
		crediter(consulter()*tauxInteret/100/12);
	}
}
