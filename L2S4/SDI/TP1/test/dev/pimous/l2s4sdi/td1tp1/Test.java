package dev.pimous.l2s4sdi.td1tp1;

import java.io.PrintStream;

class Test{

	private PrintStream out;

	private Test(PrintStream out){
		this.out = out;
	}
	
	// FUNCTIONS
	public static void main(String[] args){
		Test context = new Test(System.out);

		context.testTD();
		context.testTP();
	}

	public void testTD(){
		printsln("----------- TEST TD:");

		Liste<String> nombres = Liste.nouvelle("Un",
			Liste.nouvelle("Deux",
				Liste.nouvelle("Trois",
					Liste.nouvelle("Quatre", Liste.vide())
				)
			)
		);

		printsln("afficherIter()");
		nombres.afficheIter(out);
		printsln("affiche()");
		nombres.affiche(out);
		printsln("afficheInverse()");
		nombres.afficheInverse(out);

		printsln("longueur() ->", nombres.longueur());

		printsln("recherche(\"Un\") ->",
			nombres.recherche("Un")
		);
		printsln("recherche(\"Six\") ->",
			nombres.recherche("Six")
		);
		printsln("recherche(\"Quatre\") ->",
			nombres.recherche("Quatre")
		);

		printsln("clone()");
		Object cn = nombres.clone();
		if(cn instanceof Liste ls) ls.afficheIter(out);

		printsln("placerFin()");
		nombres.placerFin("Cinq").afficheIter(out);
	}

	public void testTP(){
		printsln("----------- TEST TP:");

		Liste<String> nombres = Liste.nouvelle("Un",
			Liste.nouvelle("Deux",
				Liste.nouvelle("Trois",
					Liste.nouvelle("Quatre", Liste.vide())
				)
			)
		);

		printsln("trouver(\"Un\") ->", nombres.trouver("Un"));
		printsln("trouver(\"Six\") ->", nombres.trouver("Six"));
		printsln("trouver(\"Quatre\") ->",
			nombres.trouver("Quatre")
		);

		printsln("size() ->", nombres.size());

		printsln("concat([Cinq, Six])");
		Liste<String> nombresSuivant = Liste.nouvelle("Cinq",
			Liste.nouvelle("Six", Liste.vide())
		);
		nombres.concat(nombresSuivant).afficheIter(out);

		printsln("concat([])");
		nombres.concat(Liste.vide()).afficheIter(out);

		printsln("inverser()");
		nombres.inverser().afficheIter(out);

		printsln("inserer(\"Deux et demi\", 2)");
		nombres.inserer("Deux et demi", 2).afficheIter(out);

		printsln("intersection([Un, Trois, Cinq])");
		Liste<String> nombresIntersect = Liste.nouvelle("Un",
			Liste.nouvelle("Trois",
				Liste.nouvelle("Cinq", Liste.vide())
			)
		);
		nombres.intersection(nombresIntersect).afficheIter(out);

		printsln("intersection([Un, Trois, Cinq])");
		Liste<String> nombresUnion = Liste.nouvelle("Un",
			Liste.nouvelle("Trois",
				Liste.nouvelle("Cinq", Liste.vide())
			)
		);
		nombres.union(nombresUnion).afficheIter(out);

		Liste<String> nombresInclus = Liste.nouvelle("Deux",
			Liste.nouvelle("Quatre", Liste.vide())
		);
		printsln("inclus([Deux, Quatre]) ->",
			nombres.inclus(nombresInclus)
		);

		nombresInclus = Liste.nouvelle("Deux",
			Liste.nouvelle("Cinq", Liste.vide())
		);
		printsln("inclus([Deux, Cinq]) ->",
			nombres.inclus(nombresInclus)
		);
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
