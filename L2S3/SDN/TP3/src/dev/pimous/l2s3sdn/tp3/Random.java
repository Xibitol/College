package dev.pimous.l2s3sdn.tp3;

public class Random{

	private Random(){}

	// FUNCTIONS
	public static int randint(int start, int end){
		return (int) Math.floor(Math.random()*(end - start) + start);
	}
}