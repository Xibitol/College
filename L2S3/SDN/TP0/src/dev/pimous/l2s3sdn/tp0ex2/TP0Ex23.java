package dev.pimous.l2s3sdn.tp0ex2;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/** My answer to the TP0 Ex23.<br><br>
 *
 * Objectif : savoir ouvrir un projet, exécuter le projet, corriger des erreurs
 * dans le programme.
 *
 * @version 1.0.0
 * @author csempe
 * @author Xibitol
 */
public class TP0Ex23{

	private InputStream in;
	private PrintStream out;

	/**
	 * @param in Where my answer will retrieve its data.
	 * @param out Where my answer will print its results.
	 */
	public TP0Ex23(InputStream in, PrintStream out){
		this.in = in;
		this.out = out;
	}

	// FUNCTIONS
    public static void main(String[] args) {
		TP0Ex23 context = new TP0Ex23(System.in, System.out);
        int[] tab = new int[5];

        try(Scanner sc = new Scanner(context.in)){
			for(int i = 0; i < tab.length; i++){
				context.out.print("Entrez la %d valeur : ".formatted(i));
				tab[i] = sc.nextInt();
			}
	
			for(int i = 0; i < tab.length; i++){
				context.out.println("Val %d : %d".formatted(i, tab[i]));
			}
		}
    }
}
