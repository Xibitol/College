package fr.lru.jeu;

import java.util.Arrays;

public enum TypeMonstre{

	DRAGON("Dragon"),
	GOLEM("Golem"),
	MORT_VIVANT("Mort_Vivant"),
	ORC("Orc"),
	SPECTRE("Spectre");

	private String displayName;

	private TypeMonstre(String displayName){
		this.displayName = displayName;
	}

	// GETTERS
	public static TypeMonstre fromDisplayName(String name){
		return Arrays.stream(TypeMonstre.values())
			.filter(mt -> mt.getDisplayName().equals(name))
			.findFirst()
			.orElse(null);
	}

	public String getDisplayName(){ return displayName; }
	
}
