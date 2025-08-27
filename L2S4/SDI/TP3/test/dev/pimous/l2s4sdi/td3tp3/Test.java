package dev.pimous.l2s4sdi.td3tp3;

import java.io.PrintStream;

class Test{

	private PrintStream out;

	private Test(PrintStream out){
		this.out = out;
	}

	// FUNCTIONS
	public static void main(String[] args){
		Test context = new Test(System.out);

		context.testCM();
		context.testTD();
		context.testTP();
		context.testCC();
	}

	public void testCM(){
		printsln("----------- TEST CM:");

		//                5
		//        3               7
		//    1       4       6       9
		//                         10
		Arbre<Integer> nombres = Arbre.nouvelle(5,
			Arbre.nouvelle(3,
				Arbre.nouvelle(1),
				Arbre.nouvelle(4)
			),
			Arbre.nouvelle(7,
				Arbre.nouvelle(6),
				Arbre.nouvelle(9,
					Arbre.nouvelle(10),
					Arbre.vide()
				)
			)
		);

		nombres.afficherIndente(out);

		printsln("afficher(out)");
		nombres.afficher(out);

		printsln("estVide() ->", nombres.estVide());
		printsln("getRacine() ->", nombres.getRacine());
		printsln("estFeuille() ->", nombres.estFeuille());
		printsln("getAd() ->", nombres.getAg());

		printsln("nbNoeuds() ->", nombres.nbNoeuds());
		printsln("hauteur() ->", nombres.hauteur());

		printsln("setRacine(4)");
		nombres.setRacine(4);
		printsln(nombres.getRacine());
		printsln("setAg(Arbre.nouvelle(0))");
		nombres.setAg(Arbre.nouvelle(0));
		printsln(nombres.getAg(), "->",
			nombres.getAg().getRacine(),
			"; Feuille?", nombres.getAg().estFeuille()
		);
		printsln("setAd(Arbre.nouvelle(8))");
		nombres.setAd(Arbre.nouvelle(8));
		printsln(nombres.getAd(), "->",
			nombres.getAd().getRacine(),
			"; Feuille?", nombres.getAd().estFeuille()
		);
	}
	public void testTD(){
		printsln("----------- TEST TD:");

		//                5
		//        3               7
		//    1       4       6       9
		//                         10
		Arbre<Integer> nombres = Arbre.nouvelle(5,
			Arbre.nouvelle(3,
				Arbre.nouvelle(1),
				Arbre.nouvelle(4)
			),
			Arbre.nouvelle(7,
				Arbre.nouvelle(6),
				Arbre.nouvelle(9,
					Arbre.nouvelle(10),
					Arbre.vide()
				)
			)
		);

		printsln("afficherGRD(out)");
		nombres.afficherGRD(out);

		printsln("nbFeuilles(out) ->", nombres.nbFeuilles());
		printsln("trouver(6) ->", nombres.trouver(6));
		printsln("trouver(10) ->", nombres.trouver(10));
		printsln("trouver(2) ->", nombres.trouver(2));
		printsln("lePlusAGauche() ->", nombres.lePlusAGauche());

		Arbre<Integer> nombresReduit;
		printsln("supprimer(1)");
		nombresReduit = nombres.supprimer(1);
		nombresReduit.afficherIndente(out);
		printsln("supprimer(9)");
		nombresReduit = nombres.supprimer(9);
		nombresReduit.afficherIndente(out);
		printsln("supprimer(5)");
		nombresReduit = nombres.supprimer(5);
		nombresReduit.afficherIndente(out);

		printsln("Arbre.nouvelle(5)");
		Arbre<Integer> nulls = Arbre.nouvelleAuto(5);
		printsln("Noeuds:", nulls.nbNoeuds());
		printsln("Feuilles:", nulls.nbFeuilles());
		printsln("Hauteur:", nulls.hauteur());
	}
	public void testTP(){
		printsln("----------- TEST TP:");

		Arbre<Integer> nombres = Arbre.nouvelle(null,
			Arbre.nouvelle(null,
				Arbre.nouvelle(1),
				Arbre.nouvelle(4)
			),
			Arbre.nouvelle(7,
				Arbre.nouvelle(null,
					Arbre.nouvelle(2),
					Arbre.nouvelle(1)
				),
				Arbre.nouvelle(9,
					Arbre.nouvelle(null),
					Arbre.vide()
				)
			)
		);

		nombres.afficherIndente(out);

		printsln("insereFeuille(10) ->",
			nombres.insereFeuille(10)
		);
		nombres.afficherIndente(out);
		printsln("insereFeuille(31) ->",
			nombres.insereFeuille(31)
		);
		nombres.afficherIndente(out);

		printsln("placerGagnant(1)");
		nombres.placerGagnant(1);
		nombres.afficherIndente(out);
		printsln("placerGagnant(7)");
		nombres.placerGagnant(7);
		nombres.afficherIndente(out);
		printsln("placerGagnant(10)");
		nombres.placerGagnant(10);
		nombres.afficherIndente(out);

		// nombres = Arbre.nouvelle(3);
		// nombres.afficherIndente(out);
	}
	public void testCC(){
		printsln("----------- TEST CC:");

		//                5
		//        3               7
		//    1       4       6       9
		//                         10
		Arbre<Integer> nombres = Arbre.nouvelle(5,
			Arbre.nouvelle(2,
				Arbre.nouvelle(1),
				Arbre.nouvelle(3)
			),
			Arbre.nouvelle(8,
				Arbre.nouvelle(7,
					Arbre.nouvelle(6),
					Arbre.vide()
				),
				Arbre.nouvelle(10)
			)
		);

		nombres.afficherIndente(out);

		printsln("miroir()");
		Arbre<Integer> reversed = nombres.miroir();
		reversed.afficherIndente(out);
	}

	public void printsln(Object ...args){
		StringBuilder sb = new StringBuilder();

		for(Object arg : args){
			sb.append(arg.toString());
			sb.append(" ");
		}

		out.println(sb);
	}
}
