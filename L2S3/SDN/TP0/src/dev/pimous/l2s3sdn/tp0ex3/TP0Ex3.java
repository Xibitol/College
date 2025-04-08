package dev.pimous.l2s3sdn.tp0ex3;

import java.io.PrintStream;

/** My answer to the TP0 Ex3.<br><br>
 *
 * Objectif : savoir ouvrir un projet, exécuter le projet, corriger des erreurs
 * dans le programme.
 *
 * @version 1.0.0
 * @author Xibitol
 */
public class TP0Ex3{

	private PrintStream out;

	/**
	 * @param out Where my answer will print its results.
	 */
	public TP0Ex3(PrintStream out){
		this.out = out;
	}
	
	// FUNCTIONS
	public static void main(String[] args){
		TP0Ex3 context = new TP0Ex3(System.out);
		double[] tab = {1.2, 1.6, 4.2, 6.8, 7.5, 9.4, 9.1};

		// 6 like the number of years I've been a programmer.
		context.filtre1(tab, 6);
		context.filtre1(tab, 0);
		context.filtre1(tab, 9.4);

		context.printsln("trouver(1.31) ->",
			context.trouver(tab, 1.31)
		);
		context.printsln("trouver(6.8) ->",
			context.trouver(tab, 6.8)
		);
		context.printsln("trouver(9.1) ->",
			context.trouver(tab, 9.1)
		);

		context.printsln("indiceRech(1.31) ->",
			context.indiceRech(tab, 1.31)
		);
		context.printsln("indiceRech(6.8) ->",
			context.indiceRech(tab, 6.8)
		);
		context.printsln("indiceRech(9.1) ->",
			context.indiceRech(tab, 9.1)
		);
	}

	/** Filters with the exclusive minimum {@code val} and prints out kept
	 * values of the table {@code tab}.
	 * @param tab The values
	 * @param val The exclusive minimum used in filtering.
	 */
	public void filtre1(double[] tab, double val){
		StringBuilder sb = new StringBuilder(
			"Filter1 with %s: ".formatted(val)
		);

		for(double tabVal : tab)
			if(tabVal > val)
				sb.append("%s ".formatted(tabVal));
		sb.deleteCharAt(sb.length() - 1);

		out.println(sb);
	}
	/** Searches if {@code val} is in {@code tab}.
	 * @param tab The search place.
	 * @param val The value to search.
	 * @return {@code true} if it exists; otherwise {@code false}.
	 */
	public boolean trouver(double[] tab, double val){
		int i = 0;
		while(i < tab.length - 1 && tab[i] != val)
			i++;
		return tab[i] == val;
	}
	/** Searches the index of {@code val} in {@code tab}.
	 * @param tab The sreach place.
	 * @param val The value for which its index is to be searched.
	 * @return The index of {@code val} if found; otherwise {@code -1}.
	 */
	public int indiceRech(double[] tab, double val){
		int i = 0;
		while(i < tab.length - 1 && tab[i] != val)
			i++;
		return tab[i] == val ? i : -1;
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
