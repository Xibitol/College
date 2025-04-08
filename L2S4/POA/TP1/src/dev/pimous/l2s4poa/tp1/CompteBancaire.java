package dev.pimous.l2s4poa.tp1;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import dev.pimous.javautils.AutoToString;

public class CompteBancaire extends AutoToString{

	private static final String[] UNSAFE_FIELDS = {"client"};

	private static int lastNumero = 0;
	private static Set<Integer> usedNumeros = new TreeSet<>();

	private int numero;
	private Client client;
	private double solde;

	{
		Arrays.sort(UNSAFE_FIELDS);
	}

	public CompteBancaire(int numero, double soldeInitial, Client client){
		if(numero <= lastNumero || usedNumeros.contains(numero))
			throw new IllegalArgumentException(
				"Already used numero %d".formatted(numero)
			);

		this.numero = numero;
		if(lastNumero + 1 == numero) lastNumero = numero;
		else usedNumeros.add(numero);

		this.client = client;
		this.solde = soldeInitial;
	}
	public CompteBancaire(int numero, Client client){
		this(numero, 0, client);
	}
	public CompteBancaire(double soldeInitial, Client client){
		this(goToNextNumero(), soldeInitial, client);
	}

	// GETTERS
	public int donneNumero(){ return numero; }
	public Client donneDetenteur(){ return client; }
	public double consulter(){ return solde; }

	// SETTERS
	private static int goToNextNumero(){
		while(usedNumeros.contains(lastNumero + 1)){
			lastNumero++;
			usedNumeros.remove(lastNumero);
		}

		return lastNumero + 1;
	}

	public double crediter(double montant){
		return solde += montant;
	}
	public double debiter(double montant){
		return solde -= montant;
	}

	public boolean transferer(CompteBancaire unCompte,
		double montantATransferer
	){
		boolean allowed = montantATransferer <= solde;

		if(allowed){
			debiter(montantATransferer);
			unCompte.crediter(montantATransferer);
		}

		return allowed;
	}

	@Override
	protected String createString(Field[] fields) {
		Collection<Field> safeFields = new ArrayList<>(
			fields.length - UNSAFE_FIELDS.length
		);

		for(Field f : fields)
			if(Arrays.binarySearch(UNSAFE_FIELDS, f.getName()) < 0)
				safeFields.add(f);

		return super.createString(safeFields.toArray(new Field[]{}));
	}
}
