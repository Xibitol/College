package dev.pimous.l2s3sdn.td4;

import java.io.PrintStream;
import java.util.ArrayList;

import dev.pimous.l2s3sdn.td4.poly.Peuplier;
import dev.pimous.l2s3sdn.td4.poly.Pin;
import dev.pimous.l2s3sdn.td4.poly.Rosier;
import dev.pimous.l2s3sdn.td4.poly.Tree;

public class Main{

	private PrintStream out;

	public Main(PrintStream out){
		this.out = out;
	}

	public static void main(String[] args){
		Main main = new Main(System.out);

		main.testVille();
		main.testArrayListEtud();
		main.testTrees();
	}

	// TESTS
	private void testVille(){
		Commune c = new Commune(4_436, 69.13);
		Commune v = new Ville(4_436, 69.13);

		out.println(c);
		out.println(v);
		out.println(c.ratio());
		out.println(v.ratio());
	}

	private void testArrayListEtud(){
		ArrayList<Personne> list = new ArrayListEtud<>();
		Personne p1 = new Personne("John");
		Personne p2 = new Personne("Rulietta");

		list.add(p1);
		assert list.getFirst().equals(p1) : "First inserted not equal.";
		list.add(p2);
		assert list.getFirst().equals(p2) : "Second inserted not equal.";
	}

	private void testTrees(){
		ArrayList<Tree> list = new ArrayList<>();
		list.add(new Pin((short) 5000));
		list.add(new Peuplier(10.6f));
		list.add(new Rosier());
		list.add(new Pin((short) 4789));

		list.forEach(t -> t.afficher(out));

		try{
			new Rosier();
			assert false : "Runtime exception not thrown.";
		}catch(RuntimeException e){
			out.println(e.getMessage());
		}
	}
}
