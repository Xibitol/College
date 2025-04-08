package fr.lru.graph;

import java.util.Collection;
import java.util.Set;

public interface Graph<E> extends Set<E>{

	// GETTERS
	Collection<E> getNeighbourhood(E element);

	// SETTERS
	boolean add(E element, Collection<E> neighbours);
	boolean setNeighbourhood(E element, Collection<E> neighbours);
}