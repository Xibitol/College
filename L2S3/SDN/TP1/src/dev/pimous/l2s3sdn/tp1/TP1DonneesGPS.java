package dev.pimous.l2s3sdn.tp1;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import dev.pimous.l2s3sdn.td1.Point;

/**
 * @author Xibitol
 */
public class TP1DonneesGPS{

	private PrintStream out;

	public TP1DonneesGPS(PrintStream out){
		this.out = out;
	}

	/**
	 * @param args the command line arguments
	 * @throws java.io.IOException
	 */
	public static void main(String[] args){
		TP1DonneesGPS main = new TP1DonneesGPS(System.out);

		main.testPoint();
		main.testParcours();
		main.testEnhancedParcours();
		main.testGraphicParcours();
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
	
	private void testEnhancedParcours(){
		Parcours p = new Parcours();
		try{
			p.chargement();
		}catch(IOException e){
			e.printStackTrace();
		};
		
		p.afficher(6);
		out.printf("Highest altitude on Parcours: %s m.\n",
			p.altitudeMax()
		);
		out.printf("Parcours total time: %d s.\n", p.temps());
		p.afficherVitesses(6);
		out.printf("Average velocity on Parcours: %.1f km/h.\n",
			p.vitesseMoy()
		);
		p.afficherVitesses(p.split(1), 6);
		p.afficherLesMax(6);
	}

	private void testGraphicParcours(){
		Parcours p = new Parcours();
		try{
			p.chargement();
		}catch(IOException e){
			e.printStackTrace();
		};

		Fenetre win = new Fenetre("Altitude along course.", 
			2000, 500
		);
		p.tracerAltitude(win);

		win = new Fenetre("Velocities along course.", 
			2000, 500
		);
		p.tracerVitesses(win);
	}
}
