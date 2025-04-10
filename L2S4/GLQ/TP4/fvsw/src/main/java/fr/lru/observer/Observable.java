package fr.lru.observer;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Observable{

	private final Collection<Observer> observers = new ArrayList<>();

	// SETTERS
	public boolean ajouterObserver(Observer observer){
		if(!observers.contains(observer)){
			observers.add(observer);
			return true;
		}else
			return false;
	}
	public boolean supprimerObserver(Observer observer){
		return observers.remove(observer);
	}

	// functions
	public void notify(Object object){
		observers.forEach(o -> o.update(this, object));
	}
}