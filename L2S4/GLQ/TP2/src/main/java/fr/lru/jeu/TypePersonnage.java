package fr.lru.jeu;

import java.util.Arrays;

public enum TypePersonnage{

	ARCHER("Archer"),
	GUERRIER("Guerrier"),
	MAGE("Mage");

	private String displayName;

	private TypePersonnage(String displayName){
		this.displayName = displayName;
	}

	// GETTERS
	public static TypePersonnage fromDisplayName(String name){
		return Arrays.stream(TypePersonnage.values())
			.filter(mt -> mt.getDisplayName().equals(name))
			.findFirst()
			.orElse(null);
	}
	public String getDisplayName(){ return displayName; }
}
