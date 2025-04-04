package dev.pimous.l2s4gl.fvsw.game;

import java.util.Arrays;

public enum MonsterType{

	DRAGON("Dragon"),
	GOLEM("Golem"),
	PHANTOM("Spectre"),
	ORC("Orc"),
	ZOMBIE("Mort_Vivant");

	private String displayName;

	private MonsterType(String displayName){
		this.displayName = displayName;
	}

	// GETTERS
	public static MonsterType fromDisplayName(String name){
		return Arrays.stream(MonsterType.values())
			.filter(mt -> mt.getDisplayName().equals(name))
			.findFirst()
			.orElse(null);
	}

	public String getDisplayName(){ return displayName; }
}
