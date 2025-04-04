package dev.pimous.l2s4ri.tp1;

import java.io.IOException;
import java.util.Scanner;

public class Exercice3{

	private static final String prompt = "$ ";
	private static final String delimiter = " ";
	
	public Exercice3(){}

	// FUNCTIONS
	public void main(String[] args){
		String[] command = new String[]{};

		try(Scanner sc = new Scanner(System.in)){
			System.out.print(prompt);

			command = sc.nextLine().split(delimiter);
		}

		try{
			Process process = Runtime.getRuntime().exec(command);

			process.getInputStream().transferTo(System.out);
			process.getErrorStream().transferTo(System.err);

			process.onExit().whenComplete(
				(p, t) -> System.out.printf("Exit code: %d\n",
					p.exitValue()
				)
			);
		}catch(IOException ignored){}
	}
}
