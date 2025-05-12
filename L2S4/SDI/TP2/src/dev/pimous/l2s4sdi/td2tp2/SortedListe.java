package dev.pimous.l2s4sdi.td2tp2;

import java.io.PrintStream;
import java.util.Collection;
import java.util.Comparator;

public abstract sealed class SortedListe<E> implements Cloneable, Comparator<E>{

	protected final Comparator<E> comparator;

	public SortedListe(Comparator<E> comparator){
		this.comparator = comparator;
	}

	// GETTERS
	public abstract boolean estVide();
	public abstract E getPremier();
	public abstract SortedListe<E> getReste();

	public abstract long longueur();
	public abstract boolean trouver(E valeur);
	public abstract boolean inclus(SortedListe<E> L);

	public long size(){ return longueur(); };
	public boolean recherche(E valeur){
		SortedListe<E> noeud = this;

		while(!noeud.estVide() && !noeud.getPremier().equals(valeur))
			noeud = noeud.getReste();

		return !noeud.estVide();
	}

	// FUNCTIONS
	public static <E> SortedListe<E> vide(){ return new SListeVide<E>(); }
	public static <E> SortedListe<E> nouvelle(E valeur, SortedListe<E> suivant){
		return new SListeCons<E>(valeur, suivant);
	}
	public static <E> SortedListe<E> nouvelle(E valeur, SortedListe<E> suivant,
		Comparator<E> comparator
	){
		return new SListeCons<E>(valeur, suivant, comparator);
	}
	public static SortedListe<Integer> nouvelleAlea(
		long size, int min, int max
	){
		return new RandIntSListe(size, min, max);
	}

	public abstract SortedListe<E> inverser();
	public abstract SortedListe<E> QS();

	public abstract SortedListe<E> inserer(E s, int rang);
	public abstract SortedListe<E> placerFin(E valeur);
	public abstract SortedListe<E> concat(SortedListe<E> L1);
	public abstract SortedListe<E> intersection(SortedListe<E> L);
	public abstract SortedListe<E> union(SortedListe<E> L);
	public abstract SortedListe<E> inferieurs(E s);
	public abstract SortedListe<E> egaux(E s);
	public abstract SortedListe<E> superieurs(E s);

	@Override
	public int compare(E o1, E o2){ return comparator.compare(o1, o2); }
	public abstract String somme();
	public abstract void listeEnTab(Collection<E> T);

	public void afficheIter(PrintStream out){
		StringBuilder sb = new StringBuilder(
			"%s#%h[".formatted(getClass().getName(), hashCode())
		);
		SortedListe<E> noeud = this;

		while(!noeud.estVide()){
			sb.append("%s, ".formatted(noeud.getPremier()));
			noeud = noeud.getReste();
		}

		sb.replace(sb.length() - 2, sb.length(), "]");
		out.println(sb);
	}
	public abstract void affiche(PrintStream out);
	public abstract void afficheInverse(PrintStream out);

	protected abstract SortedListe<E> cloneSListe();
	@Override
	public Object clone(){ return cloneSListe(); }

	// INNER CLASSES
	private static final class SListeVide<E> extends SortedListe<E>{

		@SuppressWarnings("unchecked")
		public SListeVide(){
			super((Comparator<E>) Comparator.naturalOrder());
		}

		// GETTERS
		public boolean estVide(){ return true; }
		public E getPremier(){ return null; }
		public SortedListe<E> getReste(){ return null; }

		@Override
		public long longueur(){ return 0; }
		@Override
		public boolean trouver(E valeur){ return false; }
		@Override
		public boolean inclus(SortedListe<E> L){ return L.estVide(); }

		// FUNCTIONS
		@Override
		public SortedListe<E> inverser(){ return SortedListe.vide(); }
		@Override
		public SortedListe<E> QS(){ return cloneSListe(); }

		@Override
		public SortedListe<E> inserer(E s, int rang){ return placerFin(s); }
		@Override
		public SortedListe<E> placerFin(E valeur){
			return nouvelle(valeur, SortedListe.vide());
		}
		@Override
		public SortedListe<E> concat(SortedListe<E> L1){
			return L1.cloneSListe();
		}
		@Override
		public SortedListe<E> intersection(SortedListe<E> L){
			return SortedListe.vide();
		}
		@Override
		public SortedListe<E> union(SortedListe<E> L){ return L.cloneSListe(); }
		@Override
		public SortedListe<E> inferieurs(E s){ return SortedListe.vide(); }
		@Override
		public SortedListe<E> egaux(E s){ return SortedListe.vide(); }
		@Override
		public SortedListe<E> superieurs(E s){ return SortedListe.vide(); }
		

		@Override
		public int compare(E o1, E o2){
			return comparator.compare(o1, o2);
		}
		@Override
		public String somme(){ return new String(); }
		@Override
		public void listeEnTab(Collection<E> T){}

		@Override
		public void affiche(PrintStream out){ return; }
		@Override
		public void afficheInverse(PrintStream out){ return; }

		@Override
		protected SortedListe<E> cloneSListe(){ return SortedListe.vide(); }
	}

	private static non-sealed class SListeCons<E> extends SortedListe<E>{

		private final E valeur;
		private final SortedListe<E> suiv;

		public SListeCons(E val, SortedListe<E> L, Comparator<E> comparator){
			super(comparator);

			if(L == null) throw new IllegalArgumentException();

			this.valeur = val;
			this.suiv = L;
		}
		@SuppressWarnings("unchecked")
		public SListeCons(E val, SortedListe<E> L){
			this(val, L, (Comparator<E>) Comparator.naturalOrder()) ;
		}

		// GETTERS
		public boolean estVide(){ return false; }
		public E getPremier(){ return valeur; }
		public SortedListe<E> getReste(){ return suiv; }

		@Override
		public long longueur() {
			return 1 + getReste().longueur();
		}
		@Override
		public boolean trouver(E valeur) {
			return valeur == this.valeur || getReste().trouver(valeur);
		}
		@Override
		public boolean inclus(SortedListe<E> L){
			return L.estVide() || (
				trouver(L.getPremier()) && inclus(L.getReste())
			);
		}

		// FUNCTIONS
		@Override
		public SortedListe<E> inverser(){
			return getReste().inverser().placerFin(getPremier());
		}
		@Override
		public SortedListe<E> QS(){
			return (SortedListe<E>) inferieurs(getPremier()).QS().concat(
				egaux(getPremier()).concat(
					superieurs(getPremier()).QS()
				)
			);
		}

		@Override
		public SortedListe<E> inserer(E s, int rang){
			return rang == 0 ? SortedListe.nouvelle(s, cloneSListe())
				: SortedListe.nouvelle(getPremier(),
					getReste().inserer(s, rang - 1)
				);
		}
		@Override
		public SortedListe<E> placerFin(E valeur){
			return SortedListe.nouvelle(getPremier(),
				getReste().placerFin(valeur)
			);
		}
		@Override
		public SortedListe<E> concat(SortedListe<E> L1){
			return SortedListe.nouvelle(getPremier(), getReste().concat(L1));
		}
		@Override
		public SortedListe<E> intersection(SortedListe<E> L){
			return !L.trouver(getPremier()) ? getReste().intersection(L)
				: SortedListe.nouvelle(getPremier(),
					getReste().intersection(L)
				);
		}
		@Override
		public SortedListe<E> union(SortedListe<E> L){
			return L.trouver(getPremier()) ? getReste().union(L)
				: SortedListe.nouvelle(getPremier(), getReste().union(L));
		}
		@Override
		public SortedListe<E> inferieurs(E s){
			return compare(getPremier(), s) >= 0 ? getReste().inferieurs(s)
				: SortedListe.nouvelle(getPremier(), getReste().inferieurs(s));
		}
		@Override
		public SortedListe<E> egaux(E s){
			return compare(getPremier(), s) != 0 ? getReste().egaux(s)
				: SortedListe.nouvelle(getPremier(), getReste().egaux(s));
		}
		@Override
		public SortedListe<E> superieurs(E s){
			return compare(getPremier(), s) <= 0 ? getReste().superieurs(s)
				: SortedListe.nouvelle(getPremier(), getReste().superieurs(s));
		}

		@Override
		public void listeEnTab(Collection<E> T){
			if(T.add(getPremier())) getReste().listeEnTab(T);
		}
		@Override
		public String somme(){
			return getPremier() + getReste().somme();
		}

		@Override
		public void affiche(PrintStream out){
			out.printf("\t- %s\n", getPremier());
			getReste().affiche(out);
		}
		@Override
		public void afficheInverse(PrintStream out){
			getReste().afficheInverse(out);
			out.printf("\t- %s\n", getPremier());
		}

		@Override
		protected SortedListe<E> cloneSListe(){
			return SortedListe.nouvelle(getPremier(), getReste().cloneSListe());
		}
	}
	public static final class RandIntSListe extends SListeCons<Integer>{

		public RandIntSListe(long size, int min, int max){
			super(RandIntSListe.randInt(min, max),
				size <= 0 ? SortedListe.vide()
					: new RandIntSListe(size - 1, min, max)
			);
		}

		// GETTERS
		private static int randInt(int min, int max){
			return (int) (Math.random()*(max - min + 1) + min);
		}
	}
}