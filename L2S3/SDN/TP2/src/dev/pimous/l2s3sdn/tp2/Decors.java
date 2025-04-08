package dev.pimous.l2s3sdn.tp2;

import java.awt.Color;

/**
 * Permet de tester un ensemble de fonctionnalités
 * @author csempe
 */
public class Decors {

	private Logo l; // Logo courant utilisé

	/** Constructeur qui met en place un Logo */
	public Decors(){
		this.l = new Logo(
			200, 650, 0,
			Color.red, Logo.PEN_DOWN,30
		);
	}

	// TESTS
	/** Test de commandes pour revenir en arriere */
	public void testRetourArriere(){
		l.av();
		l.setAngle(30);
		l.av();
		l.setAngle(-60);
		l.av();
		l.retourArriere();
		l.retourArriere();
	}

	/** Test de la memorisation de contextes */
	public void testExempleMemo(){
		l.av();
 
		l.memo();        // on memerise le contexte
		l.setXStylo(150);// on change le contexte
		l.setYStylo(700);
		l.setColor(Color.blue);
		l.setAngle(30);
 
		l.av();
		l.av();
 
		l.recupMemo();   // on reinstalle le contexte mémorisé
		l.setColor(Color.black);
		l.av();
		l.av();
	}
	/** Test de plusieur mise en mémoire de contextes */
	public void perspective(){
		while (l.getPas()>1 ){
			motifBase();
			l.memo();
			int echelle = (int) (l.getPas()/1.5);
			l.setPas( echelle );
			l.setXStylo(l.getXStylo()+l.getPas()*5);
		}

		while(!l.memoVide()){
			l.recupMemo();
			l.setColor(Color.blue);
			motifBase();
		}
	}

	// FUNCTIONS
	/** Motif de base */
	public void motifBase(){
		l.rotG(90);
		l.av();
		l.rotD(90);
		l.av();
		l.setPas(l.getPas()/2);
		l.rotD(90);
		l.av();
		l.rotD(90);
		l.av();
		l.rotG(90);
		l.av();
		l.rotG(90);
		l.av();l.av();
		l.setPas(l.getPas()*2);
   	}

	/** Dessine une ligne du motif de base
	 * @param longueur
	 */
	public void ligne(int longueur){
		int old = l.getPas();

		l.setPas(longueur);
		l.av();
		l.setPas(old);
	}
}
