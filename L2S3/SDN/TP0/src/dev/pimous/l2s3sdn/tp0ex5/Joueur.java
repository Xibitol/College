package dev.pimous.l2s3sdn.tp0ex5;

import dev.pimous.javautils.AutoToString;

/** Represents a football player (Or in a similar sport?).
 * @author Xibitol;
 */
public class Joueur extends AutoToString{
	
	private String name;
	private byte number;
	private String place; // TODO: A enum would be better.
	private byte weight;

	/** Creates a {@code Joueur} object.
	 * @param name Full name of the player.
	 * @param number Jersey (absolute) number.
	 * @param place Player place in game.
	 * @param weight Condition (absolute weight) of the player.
	 */
	public Joueur(String name, byte number, String place, byte weight){
		this.name = name;
		this.number = (byte) Math.abs(number);
		this.place = place;
		this.weight = (byte) Math.abs(weight);
	}
	/** Creates a {@code Joueur} object with int values converted to
	 * {@link Byte} primitive.
	 * @param name Full name of the player.
	 * @param number Jersey (absolute) number converted to a {@link Byte}
	 * primitive.
	 * @param place Player place in game.
	 * @param weight Condition (absolute weight) of the player converted to a
	 * {@link Byte} primitive.
	 */
	public Joueur(String name, int number, String place, int weight){
		this(name, (byte) number, place, (byte) weight);
	}

	// GETTERS
	/** Retrieves the full name of the player.
	 * @return Full name of the player.
	 */
	public String getName(){ return name; }
	/** Retrieves the jersey (absolute) number of the player.
	 * @return Jersey (absolute) number of the player.
	 */
	public byte getNumber(){ return number; }
	/** Retrieves the player place in game.
	 * @return Player place in game.
	 */
	public String getPlace(){ return place; }
	/** Retrieves the condition (absolute weight) of the player.
	 * @return Condition (absolute weight) of the player.
	 */
	public byte getWeight(){ return weight; }

	// SETTERS
	/** Defines the full name of the player.
	 * @param name New full name of the player.
	 */
	public void getName(String name){
		this.name = name;
	}
	/** Defines the jersey (absolute) number of the player.
	 * @param number New jersey (absolute) number of the player.
	 */
	public void getNumber(byte number){
		this.number = (byte) Math.abs(number);
	}
	/** Defines the player place in game.
	 * @param place New player place in game.
	 */
	public void getPlace(String place){
		this.place = place;
	}
	/** Defines the condition (absolute weight) of the player.
	 * @param weight New condition (absolute weight) of the player.
	 */
	public void getWeight(byte weight){
		this.weight = (byte) Math.abs(weight);
	}
}
