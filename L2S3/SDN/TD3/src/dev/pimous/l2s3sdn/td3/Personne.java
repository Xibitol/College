package dev.pimous.l2s3sdn.td3;

import dev.pimous.javautils.AutoToString;

public class Personne extends AutoToString{
	
	private String name;
	private int priority;
	private byte[] absences;

	public Personne(String name, int priority, byte[] absences){
		if(absences.length != 5)
			throw new IllegalArgumentException(
				"There should be 5 week of absence."
			);

		this.name = name;
		this.priority = priority;
		this.absences = absences;
	}
	public Personne(String name, int priority){
		this(name, priority, new byte[5]);
	}

	// GETTERS
	public String getNom(){ return this.name; }
	public int getPrio(){ return this.priority; }
	public boolean isAbsent(int week){
		int i = 0;
		while(i < absences.length && absences[i++] != week);
		return i < absences.length;
	}

	// SETTERS
	public void setPrio(int priority){
		this.priority = priority;
	}
}
