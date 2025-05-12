package dev.pimous.l2s4sdi.td2tp2;

import java.util.ArrayList;

public class QStore{

	private static final int SIZE = 2020;
	private static final int MINIMUM = 1;
	private static final int MAXIMUM = SIZE;
	private static final int SEARCHED = 31;

	private ArrayList<Integer> tab;
	private int compteurDicho;

	public QStore(){
		this.tab = new ArrayList<>();
		this.compteurDicho = 0;
	}

	// GETTERS
	public boolean estTrie(){
		return false;
	}

	// FUNCTIONS
	public boolean dicho(int n, int iDebut, int iFin){
		int i = (iDebut + iFin)/2;

		compteurDicho++;

		if(tab.get(i) == n) return true;
		else if(iDebut == iFin) return false;
		else return dicho(n,
			n < tab.get(i) ? iDebut : i + 1,
			n < tab.get(i) ? i - 1 : iFin
		);
	}

	public void exec(){
		tab.clear();
		SortedListe.nouvelleAlea(SIZE, MINIMUM, MAXIMUM).QS().listeEnTab(tab);

		compteurDicho = 0;

		System.out.printf(
			"Searching %d in %d values (%d to %d) -> %b (%d calls).\n",
			SEARCHED, SIZE, MINIMUM, MAXIMUM,
			dicho(SEARCHED, 0, tab.size() - 1), compteurDicho
		);
	}
}