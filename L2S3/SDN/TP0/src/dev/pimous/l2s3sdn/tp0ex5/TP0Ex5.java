package dev.pimous.l2s3sdn.tp0ex5;

import java.util.List;

/** My answer to the TP0 Ex5.<br><br>
 *
 * Objectif : Conception d'une classe, collection d'objets, ArrayList,
 * algorithmes simples,
 *
 * @version 1.0.0
 * @author Xibitol
 */
public class TP0Ex5{

    public static void main(String[] args) {
		TestArrayList.main(args);

		Equipe e = new Equipe("PSG");
		e.afficher();

		e.afficherCat("Centre");
		e.afficherPetiteForme((byte) 80);

		Equipe.afficherJoueurs("Players of Defense:",
			e.tabCat("Defense")
		);
		assert e.tabCat("Defense").stream().allMatch(
			p -> p.getPlace() == "Defense"
		) : "Equipe.tabCat(\"Defense\") returned Joueur in other places.";

		System.out.print("Best player in a test team: ");
		System.out.println(e.meilleur(List.of(
			new Joueur("A", 31, "Defense", 70),
			new Joueur("F", 20, "Centre", 80),
			new Joueur("B", 1, "Attaquant", 75)
		)));

		Equipe.afficherJoueurs("Best players of Defense:",
			e.selection("Defense")
		);
    }
}
