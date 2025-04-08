package dev.pimous.l2s3sdn.tp0ex4;

import java.io.PrintStream;

/** My answer to the TP0 Ex4.<br><br>
 *
 * Objectif : Programmation objet : classe, création d'objets, manipulation
 * d'objet. Création d'une classe.
 *
 * @version 1.0.0
 * @author Xibitol
 */
public class TP0Ex4{

	private PrintStream out;

	/**
	 * @param out Where my answer will print its results.
	 */
	public TP0Ex4(PrintStream out){
		this.out = out;
	}
	
	// FUNCTIONS
	public static void main(String[] args){
		TP0Ex4 context = new TP0Ex4(System.out);

		Personne p1 = new Personne("Dusson", 22);
		Personne p2 = new Personne("Augusta Ada Byron", 36);
		Personne p3 = new Personne("Brian Wilson Kernighan", 1942);

		context.testPersonne(p1, "James", 18);
		context.testPersonne(p2, "Ada Lovelace", 208);
		context.testPersonne(p3, "Brian Kernighan", 82);
	}

	/** Makes some test to a {@link Personne} object.
	 * @param p Object to test.
	 * @param name A new name to set to the object.
	 * @param age A new age to set to the object.
	 */
	public void testPersonne(Personne p, String name, int age){
		out.println(p.toString());
		printsln("describe() ->", p.describe());

        out.println("setName(\"%s\") + setAge(%d)".formatted(name, age));
        p.setName(name);
		assert p.getName().equals(name) : "Invalid name.";
        p.setAge(age);
		assert p.getAge() == age : "Invalid age.";

    	out.println(p.toString());
		printsln("describe() ->", p.describe());
	}

	/** Prints to {@code out} serveral objects concatenated with spaces.
	 * @param args Objects to concatenate and print.
	 */
	public void printsln(Object ...args){
		StringBuilder sb = new StringBuilder();

		for(Object arg : args){
			sb.append(arg.toString());
			sb.append(" ");
		}

		out.println(sb);
	}
}
