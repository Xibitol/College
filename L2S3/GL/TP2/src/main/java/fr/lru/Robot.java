package fr.lru;

import java.util.Collection;

public class Robot{
	
	private int position;

	public Robot(){}
	public Robot(int pos){
		this.position = pos;
	}

	// SETTERS
	public void setPosition(int position){
		this.position = position;
	}

	// FUNCTIONS
	public Collection<Collection<Integer>> findPath(Grid<Boolean> grid,
		int from, int to
	){
		// TODO: Implement.
		return null;
	}
}
