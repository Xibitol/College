package dev.pimous.l2s3sdn.tp2;

import java.io.PrintStream;

import dev.pimous.l2s3sdn.tp2.coloc.Etudiant;

/**
 * Test du Logo via la classe Decors
 * @author csempe
 * @author Xibitol
 */
public class Main{

	private PrintStream out;

	public Main(PrintStream out){
		this.out = out;
	}

    public static void main(String [] args){
		Main main = new Main(System.out);
        // Decors tapisserie = new Decors();

		// main.testMotifBase(tapisserie);
		// main.testLigne(tapisserie);
		// main.testMemo(tapisserie);
		// main.testBackward(tapisserie);
		main.testRoommates();
    }

	// TESTS
	@SuppressWarnings("unused")
	private void testMotifBase(Decors d){
		for(int i = 0; i < 10; i++) d.motifBase();
	}

	@SuppressWarnings("unused")
	private void testLigne(Decors d){
		d.ligne(100);
	}

	@SuppressWarnings("unused")
	private void testMemo(Decors d){
		d.testExempleMemo();
		d.perspective();
	}

	@SuppressWarnings("unused")
	private void testBackward(Decors d){
		d.testRetourArriere();
	}

	private void testRoommates(){
		Etudiant p = new Etudiant("Paul", 500);
		Etudiant e = new Etudiant("Emma", 600);
		Etudiant t = new Etudiant("Théo", 200);
		Etudiant.afficherColoc();

		p.verserPotCommun(100);
		e.afficherMessages();
		out.printf("Shared pot: %s €.\n", Etudiant.getSharedPot());
		out.println(p);

		p.retirerPotCommun(100);
		t.verserPotCommun(75);
		t.verserPotCommun(150);
		e.afficherMessages();
		out.printf("Shared pot: %s €.\n", Etudiant.getSharedPot());

		p.afficherMessages();
		t.afficherMessages();
		Etudiant.afficherColoc();
		Etudiant.afficherDecomptes();
	}
}