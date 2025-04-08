package dev.pimous.l2s3sdn.tp0ex2;

import java.io.PrintStream;

/** My answer to the TP0 Ex22.<br><br>
 *
 * Objectif : savoir ouvrir un projet, exécuter le projet, corriger des erreurs
 * dans le programme.
 *
 * @version 1.0.0
 * @author csempe
 * @author Xibitol
 */
public class TP0Ex22{

	private PrintStream out;

	/**
	 * @param output Where my answer will print its results.
	 */
	public TP0Ex22(PrintStream out){
		this.out = out;
	}

	// FUNCTIONS
    public static void main(String[] args){
		TP0Ex22 context = new TP0Ex22(System.out);
        int[] tab = {12, 15, 2, 6, 8, 7, 16, 19, 7, 3}; 
        int uneValeur = tab[0];

        context.out.println(uneValeur);

        tab[2] = 88;
		// We could have used StringBuilder here.
        for (int i = 0; i < tab.length; i++){
            context.out.print(tab[i] + " ");
        }
        context.out.append("\n");

        //tab[20] = 99;
    }
}
