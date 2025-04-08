package dev.pimous.l2s3sdn.tp0ex1;

import java.io.PrintStream;

/** My answer to the TP0 Ex1.<br><br>
 *
 * Objectif : savoir ouvrir un projet, executer le projet, comprendre
 * les instructions et modifier le programme.
 *
 * @version 1.0.0
 * @author Xibitol
 */
public class TP0Ex1{

	private PrintStream output;

	/**
	 * @param output Where my answer will print its results.
	 */
	public TP0Ex1(PrintStream output){
		this.output = output;
	}

	// FUNCTIONS
    public static void main(String[] args){
        (new TP0Ex1(System.out)).motif(8);
    }

	/** Prints a pattern to the {@code output}.<br><br>
	 * <pre>
	 * // It look something like this (For a size of 5):
	 * *
	 * XXXX
	 * **
	 * XXX
	 * ***
	 * XX
	 * ****
	 * X
	 * *****
	 * </pre>
	 * @param size The size of the pattern.
	 */
	public void motif(int size){
		for (int i = 1; i <= size; i++) {
            this.output.println("*".repeat(i));

			if(i != size)
				this.output.println("X".repeat(size - i));
        }
	}
}