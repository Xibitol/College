package fr.lru.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class HashGraph<E> extends AbstractGraph<E>{

	private HashMap<E, List<E>> elements = new HashMap<>();

	public HashGraph(){
		super();
	}

	// GETTERS
	@Override
	public Collection<E> getNeighbourhood(E element){
		return contains(element) ? elements.get(element) : null;
	}
	@Override
	public int size(){ return elements.size(); }

	// SETTERS
	@Override
	public boolean add(E element, Collection<E> neighbours){
		return Objects.isNull(elements.putIfAbsent(element,
			new ArrayList<>(neighbours)
		));
	}
	@Override
	public boolean setNeighbourhood(E element, Collection<E> neighbours){
		return Objects.isNull(elements.replace(element,
			new ArrayList<>(neighbours)
		));
	}

	// FUNCTIONS
	@Override
	public Iterator<E> iterator(){
		return elements.keySet().iterator();
	}
}
