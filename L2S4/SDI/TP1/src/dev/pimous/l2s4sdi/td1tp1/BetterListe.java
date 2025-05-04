package dev.pimous.l2s4sdi.td1tp1;

import java.io.PrintStream;

public abstract sealed class BetterListe<E> implements Cloneable{

	// GETTERS
	public abstract boolean estVide();
	public abstract E getPremier();
	public abstract BetterListe<E> getReste();

	public long longueur(){
		return estVide() ? 0 : 1 + getReste().longueur();
	}
	public long size(){ return longueur(); }
	public boolean recherche(E valeur){
		BetterListe<E> noeud = this;

		while(!noeud.estVide() && !noeud.getPremier().equals(valeur))
			noeud = noeud.getReste();

		return !noeud.estVide();
	}
	public boolean trouver(E valeur){ return recherche(valeur); }
	public boolean inclus(BetterListe<E> L){
		return L.estVide() || (trouver(L.getPremier()) && inclus(L.getReste()));
	}

	// FUNCTIONS
	public static <E> BetterListe<E> vide(){ return new ListeVide<E>(); }
	public static <E> BetterListe<E> nouvelle(E valeur, BetterListe<E> suivant){
		return new ListeCons<E>(valeur, suivant);
	}

	public BetterListe<E> inverser(){
		return estVide() ? vide() :
			getReste().inverser().placerFin(getPremier());
	}

	public BetterListe<E> inserer(E s, int rang){
		return rang == 0 ? BetterListe.nouvelle(s, cloneListe())
			: (estVide() ? BetterListe.nouvelle(s, BetterListe.vide())
				: BetterListe.nouvelle(
					getPremier(), getReste().inserer(s, rang - 1)
				)
			);
	}
	public BetterListe<E> placerFin(E valeur){
		return estVide() ? BetterListe.nouvelle(valeur, BetterListe.vide())
			: BetterListe.nouvelle(getPremier(), getReste().placerFin(valeur));
	}
	public BetterListe<E> concat(BetterListe<E> L1){
		return estVide() ? L1.cloneListe()
			: BetterListe.nouvelle(getPremier(), getReste().concat(L1));
	}
	public BetterListe<E> intersection(BetterListe<E> L){
		return estVide() ? BetterListe.vide() : (
			!L.trouver(getPremier()) ? getReste().intersection(L)
				: BetterListe.nouvelle(getPremier(), getReste().intersection(L))
		);
	}
	public BetterListe<E> union(BetterListe<E> L){
		return estVide() ? L.cloneListe() : (
			L.trouver(getPremier()) ? getReste().union(L)
				: BetterListe.nouvelle(getPremier(), getReste().union(L))
		);
	}

	public String somme(){
		return getPremier() + getReste().somme();
	}

	public void afficheIter(PrintStream out){
		StringBuilder sb = new StringBuilder(
			"%s#%h[".formatted(getClass().getName(), hashCode())
		);
		BetterListe<E> noeud = this;

		while(!noeud.estVide()){
			sb.append("%s, ".formatted(noeud.getPremier()));
			noeud = noeud.getReste();
		}

		sb.replace(sb.length() - 2, sb.length(), "]");
		out.println(sb);
	}
	public void affiche(PrintStream out){
		out.printf("\t- %s\n", getPremier());
		if(!getReste().estVide()) getReste().affiche(out);
	}
	public void afficheInverse(PrintStream out){
		if(!getReste().estVide()) getReste().afficheInverse(out);
		out.printf("\t- %s\n", getPremier());
	}

	protected BetterListe<E> cloneListe(){
		return estVide() ? BetterListe.vide()
			: BetterListe.nouvelle(getPremier(), getReste().cloneListe());
	}
	@Override
	public Object clone(){ return cloneListe(); }

	// INNER CLASSES
	private static final class ListeVide<E> extends BetterListe<E>{

		// GETTERS
		public boolean estVide(){ return true; }
		public E getPremier(){ return null; }
		public BetterListe<E> getReste(){ return null; }
	}

	private static final class ListeCons<E> extends BetterListe<E>{

		private final E valeur;
		private final BetterListe<E> suiv;

		public ListeCons(E val, BetterListe<E> L){
			if(L == null) throw new IllegalArgumentException();

			this.valeur = val; this.suiv = L;
		}

		// GETTERS
		public boolean estVide(){ return false; }
		public E getPremier(){ return this.valeur; }
		public BetterListe<E> getReste(){ return this.suiv; }
	}
}