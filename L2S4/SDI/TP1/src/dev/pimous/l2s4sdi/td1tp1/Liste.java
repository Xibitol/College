package dev.pimous.l2s4sdi.td1tp1;

import java.io.PrintStream;

public abstract sealed class Liste<E> implements Cloneable{

	// GETTERS
	public abstract boolean estVide();
	public abstract E getPremier();
	public abstract Liste<E> getReste();

	public long longueur(){
		return estVide() ? 0 : 1 + getReste().longueur();
	}
	public long size(){ return longueur(); }
	public boolean recherche(E valeur){
		Liste<E> noeud = this;

		while(!noeud.estVide() && !noeud.getPremier().equals(valeur))
			noeud = noeud.getReste();

		return !noeud.estVide();
	}
	public boolean trouver(E valeur){ return recherche(valeur); }

	// FUNCTIONS
	public static <E> Liste<E> vide(){ return new ListeVide<E>(); }
	public static <E> Liste<E> nouvelle(E valeur, Liste<E> suivant){
		return new ListeCons<E>(valeur, suivant);
	}

	public Liste<E> inverser(){
		return estVide() ? Liste.vide() :
			getReste().inverser().placerFin(getPremier());
	}
	public Liste<E> inserer(E s, int rang){
		return rang == 0 ? Liste.nouvelle(s, cloneListe())
			: Liste.nouvelle(getPremier(), getReste().inserer(s, rang - 1));
	}

	public Liste<E> placerFin(E valeur){
		return estVide() ? Liste.nouvelle(valeur, Liste.vide())
			: Liste.nouvelle(getPremier(), getReste().placerFin(valeur));
	}
	public Liste<E> concat(Liste<E> L1){
		return estVide() ? L1.cloneListe()
			: Liste.nouvelle(getPremier(), getReste().concat(L1));
	}
	public Liste<E> intersection(Liste<E> L){
		return estVide() ? Liste.vide() : (
			!L.trouver(getPremier()) ? getReste().intersection(L)
				: Liste.nouvelle(getPremier(), getReste().intersection(L))
		);
	}
	public Liste<E> union(Liste<E> L){
		return estVide() ? L.cloneListe() : (
			L.trouver(getPremier()) ? getReste().union(L)
				: Liste.nouvelle(getPremier(), getReste().union(L))
		);
	}

	public String somme(){
		return getPremier() + getReste().somme();
	}
	public boolean inclus(Liste<E> L){
		return L.estVide() ? true
			: trouver(L.getPremier()) && inclus(L.getReste());
	}

	public void afficheIter(PrintStream out){
		StringBuilder sb = new StringBuilder(
			"%s#%h[".formatted(getClass().getName(), hashCode())
		);
		Liste<E> noeud = this;

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

	protected Liste<E> cloneListe(){
		return estVide() ?
			Liste.vide() :
			Liste.nouvelle(getPremier(), getReste().cloneListe());
	}
	@Override
	public Object clone(){ return cloneListe(); }

	// INNER CLASSES
	private static final class ListeVide<E> extends Liste<E>{

		// GETTERS
		public boolean estVide(){ return true; }
		public E getPremier(){ return null; }
		public Liste<E> getReste(){ return null; }
	}

	private static final class ListeCons<E> extends Liste<E>{

		private final E valeur;
		private final Liste<E> suiv;

		public ListeCons(E val, Liste<E> L){
			if(L == null) throw new IllegalArgumentException();

			this.valeur = val; this.suiv = L;
		}

		// GETTERS
		public boolean estVide(){ return false; }
		public E getPremier(){ return this.valeur; }
		public Liste<E> getReste(){ return this.suiv; }
	}
}