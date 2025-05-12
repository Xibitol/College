package dev.pimous.l2s4sdi.td2tp2;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;

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

		SortedListe<Integer> nombres = SortedListe.nouvelle(4,
			SortedListe.nouvelle(2,
				SortedListe.nouvelle(3,
					SortedListe.nouvelle(1, SortedListe.vide())
				)
			)
		);

		printsln("afficherIter()");
		nombres.afficheIter(out);

		printsln("inferieurs(3)");
		nombres.inferieurs(3).afficheIter(out);
		printsln("superieurs(3)");
		nombres.superieurs(3).afficheIter(out);

		printsln("QS()");
		nombres.QS().afficheIter(out);

		printsln("nouvelleAlea(10, 1, 100)");
		SortedListe<Integer> randNombres = SortedListe.nouvelleAlea(
			10, 1, 10
		);
		randNombres.afficheIter(out);
		printsln("last.QS()");
		randNombres.QS().afficheIter(out);

		Collection<Integer> c = new ArrayList<>((int) nombres.longueur());
		nombres.QS().listeEnTab(c);
		printsln("listeEnTab() ->", c);
	}
	public void testTP(){
		printsln("----------- TEST TP:");

		printsln("new QStore().exec() ->");
		QStore qs = new QStore();
		for(int i = 0; i < 10; i++) qs.exec();

		LSystem fractal = LSystem.newFractal();
		printsln("Fractal(X, X=X+Y++Y-X--XX-Y+, Y=-X+YY++Y+X--X-Y) ->",
			fractal.getExpCourante()
		);
		fractal.derivation();
		printsln("fractal.derivation() ->", fractal.getExpCourante());
		fractal.derivation();
		printsln("fractal.derivation() ->", fractal.getExpCourante());
		fractal.derivation();
		printsln("fractal.derivation() ->", fractal.getExpCourante());

		LSystem plante = LSystem.newPlante();
		printsln("Plante(X, X=F-[[X]+X]+F[+FX]-X, F=FF) ->",
			plante.getExpCourante()
		);
		plante.derivation();
		printsln("plante.derivation() ->", plante.getExpCourante());
		plante.derivation();
		printsln("plante.derivation() ->", plante.getExpCourante());
		plante.derivation();
		printsln("plante.derivation() ->", plante.getExpCourante());


		LSystem arbre = LSystem.newArbre();
		printsln("Arbre(0, 0=1[-0]+0, 1=11) ->",
			arbre.getExpCourante()
		);
		arbre.derivation();
		printsln("arbre.derivation() ->", arbre.getExpCourante());
		arbre.derivation();
		printsln("arbre.derivation() ->", arbre.getExpCourante());
		arbre.derivation();
		printsln("arbre.derivation() ->", arbre.getExpCourante());
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
