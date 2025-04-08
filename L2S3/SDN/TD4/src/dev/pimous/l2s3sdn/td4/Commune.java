package dev.pimous.l2s3sdn.td4;

import dev.pimous.javautils.AutoToString;

class Commune extends AutoToString{
	
	protected int nbHabitants;
	protected double superficie;

	public Commune(int nbH, double s){
		this.nbHabitants = nbH;
		this.superficie = s;
	}

	// GETTERS
	public int getNbH(){ return this.nbHabitants; }
	public double getSupericie(){ return this.superficie; }
	public double ratio(){
		return this.nbHabitants/this.superficie;
	}
}
