package switchs;

/** Represents a Panel of a game where the player has to turn on all lights
 * using switchs.
 */
public class Panneau {
	
	private boolean lumiere1;
	private boolean lumiere2;
	private boolean lumiere3;
	private boolean lumiere4;
	private boolean lumiere5;

	/** Creates a {@code Panneau} with lights randomly turned on or off. */
	public Panneau(){
		lumiere1 = randomBoolean();
		lumiere2 = randomBoolean();
		lumiere3 = randomBoolean();
		lumiere4 = randomBoolean();
		lumiere5 = randomBoolean();
	}

	// GETTERS
	/** Indicates if all lights are on.
	 * @return True if all lights are on, false otherwise.
	 */
	public boolean allumees(){
		return lumiere1 && lumiere2 && lumiere3 && lumiere4 && lumiere5;
	}

	// SETTERS
	/** Switch 1. Toggles light 2. */
	public void interrupteur1(){
		lumiere2 = !lumiere2;
	}
	/** Switch 2. Toggles light 2 and 3. */
	public void interrupteur2(){
		lumiere2 = !lumiere2;
		lumiere3 = !lumiere3;
		
	}
	/** Switch 3. Toggles light 3 and 4. */
	public void interrupteur3(){
		lumiere3 = !lumiere3;
		lumiere4 = !lumiere4;
		
	}
	/** Switch 4. Toggles light 4 and 5. */
	public void interrupteur4(){
		lumiere4 = !lumiere4;
		lumiere5 = !lumiere5;
		
	}
	/** Switch 5. Toggles light 1 and 5. */
	public void interrupteur5(){
		lumiere1 = !lumiere1;
		lumiere5 = !lumiere5;
		
	}
	/** Switchs all lights off. */
	public void reset(){
		lumiere1 = false;
		lumiere2 = false;
		lumiere3 = false;
		lumiere4 = false;
		lumiere5 = false;
	}

	// FUNCTIONS
	/** Generates a random {@code boolean}.
	 * @return A random {@code boolean}.
	 */
	private boolean randomBoolean(){
		return Math.random()/Math.nextDown(1.0) >= 0.5;
	}
	/** Returns {@code 'O'} when the boolean is false, {@code 'X'} otherwise.
	 * @param bool A boolean.
	 * @return The associated char.
	 */
	private char offOrOn(boolean bool){
		return bool ? 'X' : 'O';
	}

	@Override
	public String toString(){
		return String.format("[%c%c%c%c%c]",
			offOrOn(lumiere1), offOrOn(lumiere2), offOrOn(lumiere3),
			offOrOn(lumiere4), offOrOn(lumiere5)
		);
	}
}
