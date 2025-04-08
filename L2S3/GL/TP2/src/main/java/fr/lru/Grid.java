package fr.lru;

import java.util.AbstractList;

import fr.lru.graph.NodeGraph;

public class Grid<E> extends AbstractList<E>{

	private int width;
	private int height;
	private NodeGraph<E> graph = new NodeGraph<>();

	public Grid(int width, int height){
		this.width = width;
		this.height = height;
	}
	
	// GETTERS
	@Override
	public E get(int index){
		return null;
	}
	@Override
	public int size(){
		return graph.size();
	}
}
