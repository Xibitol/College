package dev.pimous.l2s3sdn.tp0ex5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** Represents a team of football {@link Joueur}s (Or a similar sport?)
 * @author Xibitol;
 */
public class Equipe{

	/** Size of a best players' selection.
	 * @see #selection(String)
	 */
	public static final int SELECTION_SIZE = 3;

	private String name;
	private List<Joueur> players = new ArrayList<>();

	{
		players.addAll(List.of(
			new Joueur("Griezman", 7, "Attaquant", 90),
			new Joueur("Giroud", 9, "Attaquant", 80),
			new Joueur("Mbappe", 10, "Attaquant", 85),
			new Joueur("Fekir", 18, "Attaquant", 75),
			new Joueur("Matuidi", 14, "Centre", 82),
			new Joueur("Kante", 13, "Centre", 90),
			new Joueur("Pogba", 6, "Centre", 87),
			new Joueur("Pavard", 2, "Defense", 85),
			new Joueur("Herandez", 21, "Defense", 89),
			new Joueur("Umtiti", 2, "Defense", 81),
			new Joueur("Varane", 4, "Defense", 91),
			new Joueur("Sidibé", 2, "Defense", 71),
			new Joueur("Tolisso", 16, "Centre", 80),
			new Joueur("Lloris", 1, "Gardien", 89),
			new Joueur("Mandanda", 6, "Gardien", 75),
			new Joueur("Thauvin", 7, "Attaquant", 88)
		));
	}

	/** Creates a team of football {@link Joueur}s automatically added.
	 * @param name Name of the team (According to {@link Joueur} entries, seems
	 * to be PSG).
	 */
	public Equipe(String name){
		this.name = name;
	}

	// GETTERS
	/** Retrieves the name of the team.
	 * @return Name of the team.
	 */
	public String getNom(){
		return name;
	}
	
	/** Filters {@link Joueur}s by their playing {@code category} place.
	 * @param category Place where {@link Joueur}s should play in.
	 * @return A immutable list of the filtering's results.
	 */
	public List<Joueur> tabCat(String category){
		return players.stream().filter(j -> j.getPlace() == category).toList();
	};
	/** Searchs for the {@link Joueur} in the best condition (weight).
	 * @param players List to search.
	 * @return The {@link Joueur} in the best condition (weight) or one of them
	 * in case of equality; if {@code players} is empty, returns null.
	 */
	public Joueur meilleur(List<Joueur> players){
		return players.stream()
			.max(Comparator.comparingInt(j -> j.getWeight()))
			.orElse(null); // FIXME: Should return the optional instead.
	};
	/** Selects the {@link #SELECTION_SIZE} {@link Joueur}s in best condition
	 * (weight) that play in a specific {@code category} place.
	 * @param category Place where {@link Joueur}s should play in.
	 * @return A immutable list of the selection's result.
	 *
	 * @implNote We used {@link Stream#reduce(Object,
	 * java.util.function.BiFunction, java.util.function.BinaryOperator)} but we
	 * didn't implemented the combinator as it will never be called because we
	 * never wanted to make this stream parralel (We also used {@link
	 * Stream#sequential()} to force the behaviour, but its useless). <b>If we
	 * need it, don't forget to implement it.</b>
	 * @see <a href="https://stackoverflow.com/a/27950586/23208036">@implNote:
	 * Can a Java 8 `Stream` be parallel without you even asking for it? - Stack
	 * Overflow</a>
	 */
	public List<Joueur> selection(String category){
		return tabCat(category).stream().sequential().reduce(
			new ArrayList<>(SELECTION_SIZE),
			(bps, p) -> {
				int i = 0;
				while(i < bps.size() && bps.get(i).getWeight() > p.getWeight())
					i++;

				if(i < SELECTION_SIZE){
					Joueur holder = p;

					do{
						if(i < bps.size())
							holder = bps.set(i, holder);
						else{
							bps.add(holder);
							holder = null;
						}

						i++;
					}while(i < SELECTION_SIZE && holder != null);
				}

				return bps;
			},
			// See comment above method's return statement.
			(bpsResult, bpsCombined) -> { return bpsResult; }
		);
	};

	// FUNCTIONS
	/** Prints to {@link System#out} a titled stream of {@link Joueur}s, well
	 * formatted.
	 * @param title A title to make easier to understand the printed data.
	 * @param stream Stream of {@link Joueur}s to be printed.
	 */
	public static void afficherJoueurs(String title, Stream<Joueur> stream){
		System.out.println(
			stream.map(j -> "\t- %s".formatted(j.toString()))
				.collect(Collectors.joining(
					"\n", title + "\n", ""
				))
		);
	}
	/** Prints to {@link System#out} a titled list of {@link Joueur}s, well
	 * formatted.
	 * @param title A title to make easier to understand the printed data.
	 * @param players List of {@link Joueur}s to be printed.
	 */
	public static void afficherJoueurs(String title, List<Joueur> players){
		afficherJoueurs(title, players.stream());
	}

	/** Prints to {@link System#out} all {@link Joueur}s, well formatted. It
	 * uses {@link #afficherJoueurs(String, Stream)}.
	 */
	public void afficher(){
		Equipe.afficherJoueurs("%s is composed of:".formatted(getNom()),
			players
		);
	}
	/** Prints to {@link System#out} {@link Joueur}s that play in a specific
	 * {@code category} place, well formatted. It uses
	 * {@link #afficherJoueurs(String, Stream)}.
	 * @param category Place where {@link Joueur}s should play in.
	 */
	public void afficherCat(String category){
		Equipe.afficherJoueurs(
			"%s is composed in %s of:".formatted(getNom(), category),
			players.stream().filter(j -> j.getPlace().equals(category))
		);
	}
	/** Prints to {@link System#out} {@link Joueur}s that have condition
	 * (weight) under {@code seuilCoef} limit, well formatted. It uses
	 * {@link #afficherJoueurs(String, Stream)}.
	 * @param seuilCoef Maximum condition (weight) {@link Joueur}s should have.
	 */
	public void afficherPetiteForme(byte seuilCoef){
		Equipe.afficherJoueurs(
			"%s is composed with a smaller weight than %d of:".formatted(
				getNom(), seuilCoef
			),
			players.stream().filter(j -> j.getWeight() < seuilCoef)
		);
	}
}