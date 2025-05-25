import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import controller.ControleurDessin;
import model.Dessin;
import view.VueDessin;

final class Launcher{

	private static final String WINDOW_TITLE = "Let's draw rectangles!";
	private static final Dimension WINDOW_DIMENSION = new Dimension(
		360, 360
	);

	public static void main(String[] args) {
		SwingUtilities.invokeLater(Launcher::openWindow);
	}

	// Functions
	private static void openWindow(){
		Dessin model = new Dessin();
		VueDessin view = new VueDessin(model);
		view.attachController(new ControleurDessin(view, model));

		// Window
		JFrame window = new JFrame(WINDOW_TITLE);

		window.setMinimumSize(WINDOW_DIMENSION);

		window.setContentPane(view);
		window.pack();
		window.setVisible(true);

		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
