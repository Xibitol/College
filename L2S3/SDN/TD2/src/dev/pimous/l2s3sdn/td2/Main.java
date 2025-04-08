package dev.pimous.l2s3sdn.td2;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Stack;

public class Main{

	private PrintStream out;

	public Main(PrintStream out){
		this.out = out;
	}

	public static void main(String[] args){
		Main main = new Main(System.out);

		main.testPile();
		main.testStack();
		main.testHashMap();
		main.testProject();
	}

	private void testPile(){
		Pile p = new Pile();
		for(int i = 0x61; i <= 0x6A; i++) p.add(String.valueOf((char) i));

		// Test getters
		assert !p.estVide() : "Pile not empty.";
	
		Iterator<String> it = p.iterator();
		assert it.next().equals(p.getLast()) :
			"Wrong element when advancing iterator (Should be last one).";
		for(int i = p.size() - 1; i > 0; i--) it.next();
		assert !it.hasNext() : "Iterator not ended.";
		// TODO: Test exceptions.

		ListIterator<String> lit = p.listIterator();
		assert !lit.hasPrevious() : "Iterator not at end of list.";
		lit.next();
		assert lit.previous().equals(p.getLast()) :
			"Wrong element when stepping back iterator (Should be last one)";
		assert lit.previousIndex() == -1 : "Wrong previous index (start).";
		assert lit.nextIndex() == p.size() - 2 : "Wrong next index (last).";
		for(int i = p.size() - 1; i > 0; i--) lit.next();
		assert lit.previousIndex() == 1 : "Wrong previous index (first).";
		assert lit.nextIndex() == p.size() : "Wrong next index (end).";
		// FIXME: Test exceptions.

		lit = p.listIterator(0);
		assert lit.nextIndex() == p.size() : "Wrong next index (end).";

		p.spliterator().tryAdvance(s -> {
			assert s.equals(p.getLast()) : "Spliterator not inverted";
		});
		assert p.stream().findFirst().get().equals(p.getLast())
			: "Stream not inverted";
		assert p.parallelStream().findFirst().get().equals(p.getLast())
			: "Stream not inverted";

		// Test setters
		assert p.add("x") : "Cannot add.";
		p.addLast("y");
		assert p.getLast().equals("y") : "Wrong last added element.";
		assert p.get(p.size() - 2).equals("x")
			: "Wrong added element.";
		p.addAll(List.of("z"));
		p.empiler("aa");
		assert p.get(p.size() - 2).equals("z")
			: "Wrong all added element.";
		assert p.getLast().equals("aa") : "Wrong pushed element.";
		p.removeLast();
		p.dépiler();
		assert p.size() == 12 : "Cannot remove.";
		// TODO: Test exceptions.
	}

	private void testStack(){
		Stack<Double> s = new Stack<>();

		for(int i = 0; i < 10; i++) s.push(Math.pow(2, i));

		ListIterator<Double> it = s.listIterator();
		while(it.hasNext()){
			out.printf("%s, ", it.next());
			it.remove();
		}
		out.println();
	}

	private void testHashMap(){
		HashMap<String, Float> notes = new HashMap<>();

		notes.putAll(Map.of(
			"Paul", 15.5f,
			"Tom", 10.5f,
			"Ambre", 12f
		));

		notes.put("Tom", 10f);

		notes.forEach((s, n) -> out.printf("%s got %s\n", s, n));
	}

	private void testProject(){
		Project p = new Project();
		
		p.addTask("Conception première du produit",
			new Ingenieur("ASPART", "Conception")
		);
		p.addTask("Conception R&D",
			new Ingenieur("TRALON", "Conception")
		);
		p.addTask("Fabrication",
			new Ingenieur("BLON", "Production")
		);

		out.println(p);

		p.removeTask(new Ingenieur("SANS", "Qualité"));

		out.println(p);
	}
}