package dev.pimous.l2s4sdi.td3tp3;

import java.io.PrintStream;
import java.util.List;

public abstract sealed class Arbre<E>{

	// GETTERS
	public abstract boolean estVide();
	public abstract E getRacine();
	public abstract boolean estFeuille();
	public abstract Arbre<E> getAg();
	public abstract Arbre<E> getAd();

	public abstract int nbNoeuds();
	public abstract int hauteur();
	public abstract int nbFeuilles();
	public abstract E lePlusAGauche();
	public abstract boolean trouver(E val);

	// SETTERS
	public abstract void setRacine(E s);
	public abstract void setAg(Arbre<E> Ag);
	public abstract void setAd(Arbre<E> Ad);

	public abstract boolean insereFeuille(E valeur);
	public abstract void placerGagnant(E s);

	// FUNCTIONS
	public static <E> Arbre<E> vide(){ return new ArbreVide<>(); }
	public static <E> Arbre<E> nouvelle(E valeur){
		return new ArbreCons<>(valeur);
	}
	public static <E> Arbre<E> nouvelle(E valeur, Arbre<E> Ag, Arbre<E> Ad){
		return new ArbreCons<>(valeur, Ag, Ad);
	}
	public static <E> Arbre<E> nouvelleAuto(int hauteur){
		return new ArbreCons<>(hauteur);
	}

	public abstract Arbre<E> miroir();
	public abstract Arbre<E> supprimer(E val);

	public abstract void adversaires(List<E> adversaires);

	public abstract void afficher(PrintStream out);
	public void afficherIndente(PrintStream out){
		afficherIndente(out, hauteur());
	}
	public abstract void afficherIndente(PrintStream out, int hauteur);
	public abstract void afficherGRD(PrintStream out);

	// INNER CLASSES
	private static final class ArbreVide<E> extends Arbre<E>{

		// GETTERS
		@Override
		public boolean estVide(){ return true; }
		@Override
		public E getRacine(){ return null; }
		@Override
		public boolean estFeuille(){ return false; }
		@Override
		public Arbre<E> getAd(){ return this; }
		@Override
		public Arbre<E> getAg(){ return this; }

		@Override
		public int nbNoeuds(){ return 0; }
		@Override
		public int hauteur(){ return 0; }
		@Override
		public int nbFeuilles(){ return 0; }
		@Override
		public boolean trouver(E val){ return false; }
		@Override
		public E lePlusAGauche(){ return null; }

		// SETTERS
		@Override
		public void setRacine(E s){}
		@Override
		public void setAd(Arbre<E> Ad){}
		@Override
		public void setAg(Arbre<E> Ag){}

		@Override
		public boolean insereFeuille(E valeur){ return false; }
		@Override
		public void placerGagnant(E s){}

		// FUNCTIONS
		@Override
		public Arbre<E> miroir(){ return Arbre.vide(); }
		@Override
		public Arbre<E> supprimer(E val){ return Arbre.vide(); }

		@Override
		public void adversaires(List<E> adversaires){}

		@Override
		public void afficher(PrintStream out){}
		@Override
		public void afficherIndente(PrintStream out, int hauteur){}
		@Override
		public void afficherGRD(PrintStream out){}
	}

	private static final class ArbreCons<E> extends Arbre<E>{

		private E racine;
		private Arbre<E> Ag = Arbre.vide();
		private Arbre<E> Ad = Arbre.vide();

		public ArbreCons(E valeur){
			this.racine = valeur;
		}
		public ArbreCons(E valeur, Arbre<E> Ag, Arbre<E> Ad){
			this(valeur);

			this.Ag = Ag;
			this.Ad = Ad;
		}
		public ArbreCons(int hauteur){
			this(null,
				hauteur > 1 ? new ArbreCons<>(hauteur - 1) : Arbre.vide(),
				hauteur > 1 ? new ArbreCons<>(hauteur - 1) : Arbre.vide()
			);
		}

		// GETTERS
		@Override
		public boolean estVide(){ return false; }
		@Override
		public E getRacine(){ return racine; }
		@Override
		public boolean estFeuille(){
			return getAg().estVide() && getAd().estVide();
		}
		@Override
		public Arbre<E> getAg(){ return Ag; }
		@Override
		public Arbre<E> getAd(){ return Ad; }

		@Override
		public int nbNoeuds(){
			return 1 + getAg().nbNoeuds() + getAd().nbNoeuds();
		}
		@Override
		public int hauteur(){
			return 1 + Math.max(getAg().hauteur(), getAd().hauteur());
		}
		@Override
		public int nbFeuilles(){
			return estFeuille() ? 1 :
				getAg().nbFeuilles() + getAd().nbFeuilles();
		}
		@Override
		public E lePlusAGauche(){
			return estFeuille() ? getRacine() : getAg().lePlusAGauche();
		}
		@Override
		public boolean trouver(E val){
			return getRacine().equals(val)
				|| (getAg().trouver(val) || getAd().trouver(val));
		}

		// SETTERS
		@Override
		public void setRacine(E s){
			racine = s;
		}
		@Override
		public void setAg(Arbre<E> Ag){
			if(Ad == null) throw new IllegalArgumentException();
			this.Ag = Ag;
		}
		@Override
		public void setAd(Arbre<E> Ad){
			if(Ad == null) throw new IllegalArgumentException();
			this.Ad = Ad;
		}
		@Override
		public boolean insereFeuille(E valeur){
			if(estFeuille()){
				if(getRacine() == null){
					setRacine(valeur);
					return true;
				}

				return false;
			}

			return getAg().insereFeuille(valeur)
				|| getAd().insereFeuille(valeur);
		}
		@Override
		public void placerGagnant(E s){
			if(s.equals(getAg().getRacine()) || s.equals(getAd().getRacine()))
				setRacine(s);
			else{
				getAg().placerGagnant(s);
				getAd().placerGagnant(s);
			}
		}

		// FUNCTIONS
		@Override
		public Arbre<E> miroir(){
			return Arbre.nouvelle(getRacine(),
				getAd().miroir(),
				getAg().miroir()
			);
		}
		@Override
		public Arbre<E> supprimer(E val){
			E racine = getRacine();

			if(racine.equals(val)){
				if(getAg().estVide()) return getAd();
				else if(getAd().estVide()) return getAg();

				racine = getAg().lePlusAGauche();
				val = racine;
			}

			return Arbre.nouvelle(racine,
				getAg().supprimer(val), getAd().supprimer(val)
			);
		}

		@Override
		public void adversaires(List<E> adversaires){
			if(getRacine() == null)
				throw new RuntimeException("Tree isn't completed;");
			if(!estFeuille() && (getAg().estVide() || getAd().estVide()))
				throw new RuntimeException(
					"Tree isn't a perfect binary tree;"
				);

			if(!estFeuille()){
				if(!getAg().getRacine().equals(getRacine())){
					adversaires.add(getAg().getRacine());
					getAd().adversaires(adversaires);
				}else if(!getAd().getRacine().equals(getRacine())){
					adversaires.add(getAd().getRacine());
					getAg().adversaires(adversaires);
				}
			}
		}

		@Override
		public void afficher(PrintStream out){
			out.printf(estFeuille() ? "%s: Feuille\n" : "%s\n",
				String.valueOf(getRacine())
			);
			getAg().afficher(out);
			getAd().afficher(out);
		}
		@Override
		public void afficherIndente(PrintStream out, int hauteur){
			hauteur--;

			getAg().afficherIndente(out, hauteur);

			out.print("\t".repeat(hauteur));
			out.printf(estFeuille() ? "%s: Feuille\n" : "%s\n",
				String.valueOf(getRacine())
			);

			getAd().afficherIndente(out, hauteur);
		}
		@Override
		public void afficherGRD(PrintStream out){
			getAg().afficherGRD(out);
			out.printf(estFeuille() ? "%s: Feuille\n" : "%s\n",
				String.valueOf(getRacine())
			);
			getAd().afficherGRD(out);
		}

	}
}