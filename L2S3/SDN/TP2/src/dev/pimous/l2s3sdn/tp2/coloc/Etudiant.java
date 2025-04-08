package dev.pimous.l2s3sdn.tp2.coloc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import dev.pimous.javautils.AutoToString;

public class Etudiant extends AutoToString{

	private static final String DEPOSIT_LINE_FORMAT =
		"%s, that currently has %s €, deposited a total of %s €";
	private static final String MESSAGE_BUYING_FORMAT =
		"%s bought food for %s €.";
	private static final String MESSAGE_DEPOSITING_FORMAT =
		"%s deposited %s €.";
	private static final String ROOMMATES_TITLE_FORMAT = "Roommates:";
	private static final String DEPOSITS_TITLE_FORMAT = "Deposits:";
	private static final String MESSAGES_TITLE_FORMAT = "%s's messages:";
	private static final String LINE_FORMAT = "\n\t- %s;";
	private static final String DELIMITER = "";

	private static double sharedPot = 0;
	private static ArrayList<Etudiant> roommates = new ArrayList<>();
	private static HashMap<Etudiant, Double> deposits = new HashMap<>();
	
	private String name;
	private double budget;
	private ArrayList<String> messages = new ArrayList<>();

	public Etudiant(String name, double budget){
		this.name = name;
		this.budget = budget;

		roommates.add(this);
		deposits.put(this, 0d);
	}

	// GETTERS
	public String getNom(){ return name; }
	public double getBudget(){ return budget; }

	// SETTERS
	public static double getSharedPot(){ return sharedPot; }

	public void setBudget(double budget){
		this.budget = budget;
	}

	public void ajouterMessage(String message){
		messages.add(message);
	}
	private void notifyMessage(String message){
		roommates.stream().filter(e -> e != this)
			.forEach(e -> e.ajouterMessage(message));
	}
	public void notifierMessageAjoutArgent(double amount){
		notifyMessage(MESSAGE_DEPOSITING_FORMAT.formatted(getNom(), amount));
	}
	public void notifierMessageAchat(double amount){
		notifyMessage(MESSAGE_BUYING_FORMAT.formatted(getNom(), amount));
	}

	public void verserPotCommun(double amount){
		if(getBudget() >= amount){
			setBudget(getBudget() - amount);
			sharedPot += amount;

			deposits.replace(this, deposits.get(this) + amount);
			notifierMessageAjoutArgent(amount);
		}
	}
	public void retirerPotCommun(double amount){
		if(sharedPot >= amount){
			sharedPot -= amount;
			setBudget(getBudget() + amount);

			notifierMessageAchat(amount);
		}
	}

	// FUNCTIONS
	private static void afficher(String title, List<? extends Object> list){
		System.out.println(
			list.stream()
				.map(m -> LINE_FORMAT.formatted(m))
				.collect(Collectors.joining(DELIMITER, title, ""))
		);
	}
	public static void afficherColoc(){
		afficher(ROOMMATES_TITLE_FORMAT, roommates);
	}
	public static void afficherDecomptes(){
		afficher(DEPOSITS_TITLE_FORMAT,
			deposits.entrySet().stream().map(e ->
				DEPOSIT_LINE_FORMAT.formatted(
					e.getKey().getNom(), e.getKey().getBudget(), e.getValue()
				)
			).toList()
		);
	}

	public void afficherMessages(){
		afficher(MESSAGES_TITLE_FORMAT.formatted(getNom()), messages);
	}
}
