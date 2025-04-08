package dev.pimous.l2s3sdn.tp1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dev.pimous.l2s3sdn.td1.Point;

/** Represents a course defined by {@link Point}s.
 * @author Xibitol
 * @author csempe
 */
public class Parcours{

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

	/** Retrieves course's velocities, calculated every {@code intervalleDist}
	 * kilometers.
	 * @param intervalleDist Amount of kilometers to group points (Making better
	 * calculations).
	 * @return Calculated course's velocities.
	 */
	public ArrayList<Double> split(double intervalleDist){
		ArrayList<Double> velocities = new ArrayList<>();
		Point previous, p;
		double kilometers = 0;
		int time = 0;

		for(int i = 1; i < points.size(); i++){
			previous = points.get(i - 1);
			p = points.get(i);

			kilometers += p.distance(previous);
			time += p.getDeltatime();

			if(kilometers >= intervalleDist){
				velocities.add(kilometers/time*3600);
				kilometers = 0;
				time = 0;
			}
		}

		return velocities;
	}
	/** Retrieves course's velocities between each points.<br><br>
	 * Equivalent to {@code split(0)}.
	 * @return Calculated course's velocities.
	 */
	public List<Double> getVelocities(){
		return split(0);
	}
	/** Retrieves course's average velocity from {@code getVelocities()}.
	 * @return Calculated course's average velocity.
	 */
	public double vitesseMoy(){
		return getVelocities().stream()
			.sorted((v1, v2) -> Double.compare(Math.abs(v1), Math.abs(v2)))
			.collect(Collectors.summarizingDouble(v -> v))
			.getAverage();
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
	/** Traces a list of values to a window {@link Fenetre}, resized by a
	 * {@code coef}.
	 * @param window Window {@link Fenetre} to trace values on.
	 * @param values Values to trace.
	 * @param coef Resize coef to modify the graph.
	 */
	private static void tracer(Fenetre window,
		List<Double> values,
		double coef
	){
		Double max = values.stream().max(Double::compare).get();

		double xDelta = (double) values.size()/window.getWidth();
		double yDelta = window.getHeight()/2/max*coef;
		double v;

		for(int i = 0; i < window.getWidth(); i++){
			v = values.get((int) Math.floor(xDelta*i));

			window.tracerPoint(
				i, window.getHeight()/2 - (int) (yDelta*v)
			);
		}
	}

	/** Reads and adds all parsed points from {@code coordinates.csv}.
	 * @throws IOException When the file {@code coordinates.csv} cannot be read.
	 */
  	public void chargement() throws IOException{
		Scanner sc = new Scanner(new FileInputStream(
			ClassLoader.getSystemResource("coordinates.csv").getPath()
		));

   		while(sc.hasNextLine()){
			StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");

			points.add(new Point(
				Double.parseDouble(st.nextToken()),
				Double.parseDouble(st.nextToken()),
				Double.parseDouble(st.nextToken()),
				Integer.parseInt(st.nextToken())
			));
   		}

		sc.close();
   	}

	/** Displays up to {@code limit} points to the standard output.<br><br>
	 * Equivalent to {@code Parcours.afficher(title, points.stream(), limit)}.
	 * @param limit Number of values to print.
	 */
	public void afficher(int limit){
		Parcours.afficher("Parcours", points.stream(), limit);
	}
	/** Displays up to {@code limit} points considered as altitude
	 * spikes/maximums, to the standard output.
	 * @param limit Number of values to print.
	 */
	public void afficherLesMax(int limit){
		List<Point> spikes = new ArrayList<>();
		Point previous, p, next;

		for(int i = 1; i < points.size() - 1; i++){
			previous = points.get(i - 1);
			p = points.get(i);
			next = points.get(i + 1);

			if(p.getAltitude() - previous.getAltitude() > 0
				&& p.getAltitude() - next.getAltitude() > 0
			)
				spikes.add(p);
		}

		Parcours.afficher("Altitude spikes on Parcours",
			spikes.stream(),
			limit
		);
	}
	/** Displays up to {@code limit} well-formatted velocities ({@code double}
	 * values) from a list, to the standard output.
	 * @param velocities Values to print.
	 * @param limit Number of values to print.
	 */
	public void afficherVitesses(List<Double> velocities, int limit){
		Parcours.afficher("Speed along parcours",
			velocities.stream().map(v -> "%.1f".formatted(v)),
			limit
		);
	}
	/** Displays up to {@code limit} well-formatted velocities to the standard
	 * output.<br><br>
	 * Equivalent to {@code afficherVitesses(getVelocities(), limit)}.
	 * @param limit Number of values to print.
	 */
	public void afficherVitesses(int limit){
		afficherVitesses(getVelocities(), limit);
	}

	/** Traces points' altitudes to a window {@link Fenetre}.
	 * @param window Window {@link Fenetre} to trace values on.
	 */
	public void tracerAltitude(Fenetre window){
		tracer(window,
			points.stream().map(p -> p.getAltitude()).toList(),
			0.25
		);
	}
	/** Traces velocities to a window {@link Fenetre}.
	 * @param window Window {@link Fenetre} to trace values on.
	 */
	public void tracerVitesses(Fenetre window){
		tracer(window, split(0.5), 0.5);
	}
}