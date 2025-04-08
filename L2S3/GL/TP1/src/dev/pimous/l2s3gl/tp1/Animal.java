package dev.pimous.l2s3gl.tp1;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import dev.pimous.javautils.AutoToString;

/**
 * @author Xibitol;
 */
public sealed abstract class Animal extends AutoToString
	implements Comparable<Animal>
	permits Mammifere, Reptile
{
	
	private static int prochainIdentifiant = 0;
	private static List<Integer> identifiantUtilises = new ArrayList<>();

	private int identifiant;
	protected String race;
	protected String dateDeNaiss;
	protected float prix;

	public Animal(int identifiant,
		String race,
		String dateDeNaiss,
		float prix
	){
		if(identifiant == prochainIdentifiant) prochainIdentifiant++;
		else if(identifiant > prochainIdentifiant
			&& !identifiantUtilises.contains(identifiant)
		) identifiantUtilises.add(identifiant);
		else
			throw new IllegalArgumentException("Identifier already used.");

		this.identifiant = identifiant;
		this.race = race;
		this.dateDeNaiss = dateDeNaiss;
		this.prix = prix;
	}
	public Animal(String race, String dateDeNaiss, float prix){
		this(prochainIdentifiant, race, dateDeNaiss, prix);
	}

	// GETTERS
	protected static int getProchainIdentifiant(){ return prochainIdentifiant; }

	public int getIdentifiant(){ return identifiant; }
	public String getRace(){ return race; }
	public String getDateDeNaiss(){ return dateDeNaiss; }
	public Date getDateDeNaissAsDate(){ return Date.valueOf(dateDeNaiss); }
	public double getPrix(){ return prix; }

	// FUNCTIONS
	@Override
	public int compareTo(Animal that) {
		return Integer.compare(getIdentifiant(), that.getIdentifiant());
	}
}
