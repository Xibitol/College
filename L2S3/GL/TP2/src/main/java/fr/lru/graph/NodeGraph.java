package fr.lru.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class NodeGraph<E> extends AbstractGraph<E>{

	private Set<Node> nodes = new TreeSet<>();

	public NodeGraph(){
		super();
	}

	// GETTERS
	@Override
	public Collection<E> getNeighbourhood(E element){
		Node node = nodes.stream().filter(n -> n.getValue().equals(element))
			.findFirst().orElse(null);

		return Objects.isNull(node) ? null : node.getNeighbourhood().stream()
			.map(n -> n.getValue()).toList();
	}
	@Override
	public int size(){ return nodes.size(); }

	// SETTERS
	@Override
	public boolean add(E element, Collection<E> neighbours){
		// TODO: Implement.
		return false;
	}
	@Override
	public boolean setNeighbourhood(E element, Collection<E> neighbours){
		// TODO: Implement.
		return false;
	}

	// FUNCTIONS
	@Override
	public Iterator<E> iterator(){
		Set<E> elements = new TreeSet<>();

		elements.addAll(nodes.stream().map(n -> n.getValue()).toList());

		return elements.iterator();
	}

	// INNER CLASSES
	public class Node{

		private E value;
		private List<Node> neighbours;

		public Node(E value, Collection<Node> neighbours){
			this.value = value;
			this.neighbours = new ArrayList<>(neighbours);
		}

		// GETTERS
		public E getValue(){ return value; }
		public List<Node> getNeighbourhood(){ return neighbours; }

		// SETTERS
		public boolean addNeighbour(Node neighbour){
			boolean added = false;

			if(!neighbours.contains(neighbour)){
				neighbours.add(neighbour);
				added = true;
			}

			return added;
		}
		public void setNeighbourhoud(Collection<Node> neighbours){
			this.neighbours.clear();
			this.neighbours.addAll(neighbours);
		}
		public boolean removeNeighbour(Node neighbour){
			boolean removed = false;

			if(neighbours.contains(neighbour)){
				neighbours.remove(neighbour);
				removed = true;
			}

			return removed;
		}

		// FUNCTIONS
		@Override
		public int hashCode(){
			return value.hashCode();
		}
	}
}
