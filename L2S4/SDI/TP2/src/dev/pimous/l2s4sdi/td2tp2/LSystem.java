package dev.pimous.l2s4sdi.td2tp2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LSystem implements Cloneable{

	private static final List<Character> SPECIAL_CHARS = List.of(
		'[', ']'
	);

	private static final LSystem FRACTAL = new LSystem(Map.ofEntries(
		Map.entry('X', "X+Y++Y-X--XX-Y+"),
		Map.entry('Y', "-X+YY++Y+X--X-Y"),
		Map.entry('+', "+"),
		Map.entry('-', "-")
	), "X");
	private static final LSystem PLANTE = new LSystem(Map.ofEntries(
		Map.entry('X', "F-[[X]+X]+F[+FX]-X"),
		Map.entry('F', "FF"),
		Map.entry('+', "+"),
		Map.entry('-', "-")
	), "X");
	private static final LSystem ARBRE = new LSystem(Map.ofEntries(
		Map.entry('0', "1[-0]+0"),
		Map.entry('1', "11"),
		Map.entry('+', "+"),
		Map.entry('-', "-")
	), "0");

	private String axiome;
	private Map<Character, String> regles = new HashMap<>();
	private String expCourante;

	public LSystem(Map<Character, String> regles, String axiome){
		for(Map.Entry<Character, String> e : regles.entrySet())
			LSystem.assertValidString(e.getValue(), regles);
		this.regles.putAll(regles);

		LSystem.assertValidString(axiome, this.regles);
		this.axiome = axiome;

		resetExp();
	}

	// GETTERS
	public static LSystem newFractal(){ return (LSystem) FRACTAL.clone(); }
	public static LSystem newPlante(){ return (LSystem) PLANTE.clone(); }
	public static LSystem newArbre(){ return (LSystem) ARBRE.clone(); }

	public String getExpCourante(){
		return expCourante;
	}

	// SETTERS
	public void derivation(){
		StringBuilder nouvelleExp = new StringBuilder();

		for(char c : expCourante.toCharArray())
			nouvelleExp.append(regles.getOrDefault(c, String.valueOf(c)));

		this.expCourante = nouvelleExp.toString();
	}
	public void resetExp(){
		this.expCourante = this.axiome;
	}

	// FUNCTIONS
	@Override
	protected Object clone(){
		return new LSystem(regles, axiome);
	}

	// ASSERTIONS
	private static void assertValidString(String str,
		Map<Character, String> regles
	){
		for(char c : str.toCharArray())
			if(!regles.containsKey(c) && !SPECIAL_CHARS.contains(c))
				throw new IllegalArgumentException(
					"No such rule symbol (%c);".formatted(c)
				);
	}
}