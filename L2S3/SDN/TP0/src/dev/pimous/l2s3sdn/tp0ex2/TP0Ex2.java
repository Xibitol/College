package dev.pimous.l2s3sdn.tp0ex2;

import java.io.PrintStream;

/** My answer to the TP0 Ex21.<br><br>
 *
 * Objectif : savoir ouvrir un projet, exécuter le projet, corriger des erreurs
 * dans le programme.
 *
 * @version 1.0.0
 * @author csempe
 * @author Xibitol
 */
public class TP0Ex2{

	private PrintStream out;

	/**
	 * @param out Where my answer will print its results.
	 */
	public TP0Ex2(PrintStream out){
		this.out = out;
	}

	// FUNCTIONS
    public static void main(String[] args){
		TP0Ex2 context = new TP0Ex2(System.out);
        int mavaleur = 25;

        context.out.println(calcul1(mavaleur));
        context.out.println(calcul2(12, 23.6));
    }

    public static double calcul1(int val){
        //double resultat = val*1.25;
        return val*1.25;
    }
    public static double calcul2(double hauteur, double largueur){
        //double resultat = hauteur*largueur;
        return hauteur*largueur;
    }
}
