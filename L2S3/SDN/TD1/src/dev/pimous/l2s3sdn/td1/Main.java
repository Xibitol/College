package dev.pimous.l2s3sdn.td1;

import java.io.PrintStream;
import java.util.List;

public class Main{

	private PrintStream out;

	public Main(PrintStream out){
		this.out = out;
	}

	public static void main(String[] args){
		Main main = new Main(System.out);

		main.testPoint();
		main.testParcours();
	}

	// Tests
	private void testPoint(){
		List<Point> points = List.of(
			new Point( // La Rochelle
				46.160328, -1.151139, 13, 18
			),
			new Point( // Rochefort
				45.945030, -0.975670, 17, 2
			)
		);

		for(int i = 0; i < points.size(); i++){
			Point p = points.get(i);

			out.println("p%d: %s".formatted(i, p));
			out.println("Point %d's longitude: %s.".formatted(i,
				p.getLongitude()
			));
			out.println("Point %d's latitude: %s.".formatted(i,
				p.getLatitude()
			));
			out.println("Point %d's altitude: %s m.".formatted(i,
				p.getAltitude()
			));
			out.println("Point %d's deltatime: %d s.".formatted(i,
				p.getDeltatime()
			));
		}

		if(points.size() >= 2){
			out.println("Distance from 2 Points: %.1f m.".formatted(
				points.get(0).distance(points.get(1))
			));
		}
	}

	private void testParcours(){
		Parcours p = new Parcours();

		p.add(new Point(
			46.056425, -1.083614, 11, 0
		));
		p.add(new Point(
			46.056455, -1.083547, 12, 17
		));
		p.add(new Point(
			46.056377, -1.083457, 13.9, 5
		));
		p.add(new Point(
			46.056393, -1.083334, 13.8, 4
		));
		p.add(new Point(
			46.056356, -1.083223, 12.7, 5
		));
		p.add(new Point(
			46.056388, -1.083104, 11.6, 4
		));
		p.add(new Point(
			46.056414, -1.082966, 11.5, 5
		));
		p.add(new Point(
			46.056396, -1.082829, 11.5, 6
		));

		p.afficher(6);
		out.println("Highest altitude on Parcours: %s m.".formatted(
			p.altitudeMax()
		));
		out.println("Parcours total time: %d s.".formatted(p.temps()));
	}
}
