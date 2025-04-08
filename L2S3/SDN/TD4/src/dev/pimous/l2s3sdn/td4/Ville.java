package dev.pimous.l2s3sdn.td4;

public class Ville extends Commune{
	
	public Ville(int nbH, double s){
		super(nbH, s);
	}

	// GETTERS
	@Override
	public double ratio(){
		return getSupericie()/getNbH();
	}
}
