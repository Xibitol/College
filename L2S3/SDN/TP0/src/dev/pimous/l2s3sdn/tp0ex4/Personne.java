package dev.pimous.l2s3sdn.tp0ex4;

import dev.pimous.javautils.AutoToString;

/** Represents a person (in real life).
 * @author Xibitol
 */
public class Personne extends AutoToString{

	private static final String DESCRIPTION_FORMAT =
		"%s is a person and is %d years old.";

	private String name;
	private int age;
	
	/**
	 * @param name Full name of the person.
	 * @param age Age of the person.
	 */
	public Personne(String name, int age){
		this.name = name;
		this.age = age;
	}

	// GETTERS
	/** Retrieves the full name of the person.
	 * @return Full name of the person.
	 */
	public String getName(){ return name; }
	/** Retrieves the age of the person.
	 * @return Age of the person.
	 */
	public int getAge(){ return age; }

	/**Generates a/few sentence(s) describing the person
	 * @return A {@code String} describing the person.
	 */
	public String describe(){
		return DESCRIPTION_FORMAT.formatted(getName(), getAge());
	}

	// SETTERS
	/** Defines the full name of the person.
	 * @param name Full name of the person.
	 */
	public void setName(String name){
		this.name = name;
	}
	/** Defines the age of the person.
	 * @param age Age of the person.
	 */
	public void setAge(int age){
		this.age = age;
	}
}
