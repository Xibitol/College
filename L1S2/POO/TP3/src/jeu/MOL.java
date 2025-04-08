package jeu;

import java.util.Objects;
import java.util.Scanner;

/** An simple implementation of the game "More or less". */
public class MOL{

	private static final int MIN = 0;
	private static final int MAX = 100;

	public static void main(String[] args) {
 		long r = Math.round(
			MOL.MIN - (MOL.MIN - MOL.MAX)*Math.random()/Math.nextDown(1.)
		);
		Long n = null;

		Scanner sc = new Scanner(System.in);
		do{
			do{
				System.out.print(
					String.format(
						"Saisissez un entier entre %1$d et %2$d: ",
						MOL.MIN, MOL.MAX
					)
				);

				if(sc.hasNextLong()) n = sc.nextLong();
				else{
					System.out.println(
						String.format(
							"Please enter an integer (not \"%s\").",
							sc.next()
						)
					);
					n = null;
				}
			}while(Objects.isNull(n));

			if(!n.equals(r))
				System.out.println(String.format("Plus %s !",
					n.compareTo(r) > 0 ? "petit" : "grand"
				));
		}while(!n.equals(r));
		sc.close();

		System.out.println("Gagné !");
	}
}