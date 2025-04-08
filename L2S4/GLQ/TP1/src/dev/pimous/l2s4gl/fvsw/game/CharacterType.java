package dev.pimous.l2s4gl.fvsw.game;

import java.util.Arrays;

public enum CharacterType{

	ARCHER("Archer"),
	KNIGHT("Guerrier"),
	MAGE("Mage");

	private String displayName;

	private CharacterType(String displayName){
		this.displayName = displayName;
	}

	// GETTERS
	public static CharacterType fromDisplayName(String name){
		return Arrays.stream(CharacterType.values())
			.filter(mt -> mt.getDisplayName().equals(name))
			.findFirst()
			.orElse(null);
	}
	public String getDisplayName(){ return displayName; }
}