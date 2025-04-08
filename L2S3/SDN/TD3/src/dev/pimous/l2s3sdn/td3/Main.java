package dev.pimous.l2s3sdn.td3;

import java.io.PrintStream;
import java.util.List;

public class Main{

	private PrintStream out;

	public Main(PrintStream out){
		this.out = out;
	}

	public static void main(String[] args){
		Main main = new Main(System.out);

		main.testFile();
		main.testFilePrio();
		main.testPlanning();
	}

	private void testFile(){
		File f = new File();

		// Test getters
		assert f.empty() : "Pile not empty.";

		// Test setters
		assert f.add("x") : "Cannot add.";
		f.addLast("y");
		assert f.getFirst().equals("x") : "Wrong added element.";
		assert f.getLast().equals("y")
			: "Wrong last added element.";
		f.addAll(List.of("z"));
		assert f.get(2).equals("z")
			: "Wrong all added element.";
		f.removeFirst();
		assert f.get().equals("y") : "Wrong got and removed element.";
		assert f.size() == 1 : "Cannot remove.";
		// TODO: Test exceptions.
	}

	private void testFilePrio(){
		FilePrio f = new FilePrio();
		f.add(new Personne("Michel", 6));
		f.add(new Personne("Foo", 2));
		f.add(new Personne("Bar", 1));

		out.println(f);

		Personne p = f.get();
		p.setPrio(2);
		f.add(p);

		out.println(f);
	}

	private void testPlanning(){
		Planning p = new Planning(List.of(
			new Personne("Paul", 0,
				new byte[]{1, 16, 28, 29, 43}
			),
			new Personne("Mathieu", 0,
				new byte[]{52, 16, 30, 31, 44}
			),
			new Personne("Jean", 0,
				new byte[]{2, 16, 36, 37, 51}
			),
			new Personne("Pierre", 0,
				new byte[]{51, 10, 26, 27, 40}
			),
			new Personne("Luc", 0,
				new byte[]{3, 16, 32, 33, 47}
			)
		));

		p.repartition();
		System.out.println(p.getPersonnes());
	}
}