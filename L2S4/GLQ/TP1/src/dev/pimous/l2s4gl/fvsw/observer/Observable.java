package dev.pimous.l2s4gl.fvsw.observer;

public interface Observable{

	// SETTERS
	abstract boolean addObserver(Observer observer);
	abstract boolean removeObserver(Observer observer);

	// FUNCTIONS
	abstract void notifyObservers(Object object);
}
