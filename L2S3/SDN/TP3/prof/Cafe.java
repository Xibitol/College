import java.util.*;

public class Cafe
{
	//Declaration des constantes
	  public static final int NBPERSGROUP=6;
	 public static final int DELAI_ARRIVEES = 15;
	 public static final int DELAI_COM_MINI = 2; 
	 public static final int DELAI_COM_MAXI = 5;
	 public static final int DELAI_SERV_MINI = 5 ;
	 public static final int DELAI_SERV_MAXI = 10;
	 public static final int DELAI_DEP_MINI = 10;
	 public static final int DELAI_DEP_MAXI = 30 ;
	 
	 public static final int STAT_ARRIVEE = 0 ;	// les statuts
	 public static final int STAT_COMMANDE = 1 ;
	 public static final int STAT_SERVICE = 2 ;
	 public static final int STAT_DEPART = 3 ;

	// variable d'instance	

	private int nbPlaces;
	private String nom;
	private FilePrio fp; 
	

	//Constructeurs
	Cafe(String n, int nbPlaces)
	{
		this.nom=n;
		this.nbPlaces=nbPlaces;
	}
	
	//Methodes
	public int getNombrePlaces(){ return this.nbPlaces;}
	
	public void ouverture(int nbreGroupe)
	{
	
	
	
	
	}
	
	
	public void gestion()	// programmation de l'automate
	{
	
	
	
	
	
			
	}	

	public void afficherEvts()
	{
		fp.afficher();
	}
	
	
		
	private int aleatoire(int min, int max)
	{
		return  min +(int)(Math.random()*(max - min + 1));
	}


}

