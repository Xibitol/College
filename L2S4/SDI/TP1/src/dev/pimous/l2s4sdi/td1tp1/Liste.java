package dev.pimous.l2s4sdi.td1tp1;

import java.io.PrintStream;

public abstract sealed class Liste<E> implements Cloneable{

	// GETTERS
	public abstract boolean estVide();
	public abstract E getPremier();
	public abstract Liste<E> getReste();

	public abstract long longueur();
	public abstract boolean trouver(E valeur);
	public abstract boolean inclus(Liste<E> L);

	public long size(){ return longueur(); };
	public boolean recherche(E valeur){
		Liste<E> noeud = this;

		while(!noeud.estVide() && !noeud.getPremier().equals(valeur))
			noeud = noeud.getReste();

		return !noeud.estVide();
	}

	// FUNCTIONS
	public static <E> Liste<E> vide(){ return new ListeVide<E>(); }
	public static <E> Liste<E> nouvelle(E valeur, Liste<E> suivant){
		return new ListeCons<E>(valeur, suivant);
	}

	public abstract Liste<E> inverser();

	public abstract Liste<E> inserer(E s, int rang);
	public abstract Liste<E> placerFin(E valeur);
	public abstract Liste<E> concat(Liste<E> L1);
	public abstract Liste<E> intersection(Liste<E> L);
	public abstract Liste<E> union(Liste<E> L);

	public abstract String somme();

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
	public abstract void affiche(PrintStream out);
	public abstract void afficheInverse(PrintStream out);

	protected abstract Liste<E> cloneListe();
	@Override
	public Object clone(){ return cloneListe(); }

	// INNER CLASSES
	private static final class ListeVide<E> extends Liste<E>{

		// GETTERS
		public boolean estVide(){ return true; }
		public E getPremier(){ return null; }
		public Liste<E> getReste(){ return null; }

		@Override
		public long longueur(){ return 0; }
		@Override
		public boolean trouver(E valeur){ return false; }
		@Override
		public boolean inclus(Liste<E> L){ return L.estVide(); }

		// FUNCTIONS
		@Override
		public Liste<E> inverser(){ return Liste.vide(); }

		@Override
		public Liste<E> inserer(E s, int rang){ return placerFin(s); }
		@Override
		public Liste<E> placerFin(E valeur){
			return nouvelle(valeur, Liste.vide());
		}
		@Override
		public Liste<E> concat(Liste<E> L1){ return L1.cloneListe(); }
		@Override
		public Liste<E> intersection(Liste<E> L){ return Liste.vide(); }
		@Override
		public Liste<E> union(Liste<E> L){ return L.cloneListe(); }

		@Override
		public String somme(){ return new String(); }

		@Override
		public void affiche(PrintStream out){ return; }
		@Override
		public void afficheInverse(PrintStream out){ return; }

		@Override
		protected Liste<E> cloneListe(){ return Liste.vide(); }
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
		public E getPremier(){ return valeur; }
		public Liste<E> getReste(){ return suiv; }

		@Override
		public long longueur() {
			return 1 + getReste().longueur();
		}
		@Override
		public boolean trouver(E valeur) {
			return valeur == this.valeur || getReste().trouver(valeur);
		}
		@Override
		public boolean inclus(Liste<E> L){
			return L.estVide() || (
				trouver(L.getPremier()) && inclus(L.getReste())
			);
		}

		// FUNCTIONS
		@Override
		public Liste<E> inverser(){
			return getReste().inverser().placerFin(getPremier());
		}

		@Override
		public Liste<E> inserer(E s, int rang){
			return rang == 0 ? Liste.nouvelle(s, cloneListe())
				: Liste.nouvelle(getPremier(), getReste().inserer(s, rang - 1));
		}
		@Override
		public Liste<E> placerFin(E valeur){
			return Liste.nouvelle(getPremier(), getReste().placerFin(valeur));
		}
		@Override
		public Liste<E> concat(Liste<E> L1){
			return Liste.nouvelle(getPremier(), getReste().concat(L1));
		}
		@Override
		public Liste<E> intersection(Liste<E> L){
			return !L.trouver(getPremier()) ? getReste().intersection(L)
				: Liste.nouvelle(getPremier(), getReste().intersection(L));
		}
		@Override
		public Liste<E> union(Liste<E> L){
			return L.trouver(getPremier()) ? getReste().union(L)
				: Liste.nouvelle(getPremier(), getReste().union(L));
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
		protected Liste<E> cloneListe(){
			return Liste.nouvelle(getPremier(), getReste().cloneListe());
		}
	}
}