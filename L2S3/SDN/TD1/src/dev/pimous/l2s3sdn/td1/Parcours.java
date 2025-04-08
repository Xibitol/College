package dev.pimous.l2s3sdn.td1;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** Represents a course defined by {@link Point}s.
 * @author Xibitol
 */
class Parcours{

	private static final String LINEFEED_CHAR = "\n";
	private static final String TITLE_FORMAT = "%s:\n";
	private static final String LINE_FORMAT = "\t- %s;";

	private List<Point> points = new ArrayList<>();

	// GETTERS
	/** Retrieves the maximal altitude along the course, among all points.
	 * @return Maximal altitude along the course.
	 */
	public double altitudeMax(){
		return points.stream().max((p1, p2) ->
			Double.compare(p1.getAltitude(), p2.getAltitude())
		).get().getAltitude();
	}
	/** Retrieves the course's duration. This is a sum of all point deltatimes.
	 * @return Course's duration.
	 */
	public int temps(){
		return points.stream().collect(
			Collectors.summingInt(p -> p.getDeltatime())
		).intValue();
	}

	// SETTERS
	/** Adds a point to the course.
	 * @param point New/Existant point to add.
	 */
	public void add(Point point){
		points.add(point);
	}

	// FUNCTIONS
	/** Displays to the standard output a limited list of values. The printed
	 * list is (clearly) identified by a title and the list ends with an
	 * indicator for more values available.
	 * @param <T> Type the values are.
	 * @param title A title to identify the printed list.
	 * @param stream Values of type {@code T} to print.
	 * @param limit Number of values to use.
	 */
	public static <T> void afficher(String title, Stream<T> stream, int limit){
		System.out.println(
			Stream.concat(
				stream.limit(limit).map(p ->
					LINE_FORMAT.formatted(p.toString())
				),
				Stream.of(LINE_FORMAT.formatted("..."))
			).collect(Collectors.joining(
				LINEFEED_CHAR,
				TITLE_FORMAT.formatted(title),
				""
			))
		);
	}

	/** Displays up to {@code limit} points to the standard output.<br><br>
	 * Equivalent to {@code Parcours.afficher(title, points.stream(), limit)}.
	 * @param limit Number of values to print.
	 */
	public void afficher(int limit){
		afficher("Parcours", points.stream(), limit);
	}
}