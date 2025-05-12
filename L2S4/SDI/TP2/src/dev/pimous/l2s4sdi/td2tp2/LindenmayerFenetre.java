package dev.pimous.l2s4sdi.td2tp2;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import dev.pimous.l2s4sdi.td1tp1.RecursiveFenetre;

public class LindenmayerFenetre extends RecursiveFenetre<Lindenmayer>{

	private static final Map<String,
		BiFunction<Integer, Integer, Lindenmayer>
	> lmSuppliers = new HashMap<>();
	private static final String defaultLMSupplier;

	private ButtonGroup lmButtons;

	static {
		lmSuppliers.putAll(Map.ofEntries(
			Map.entry("Fractal", LindenmayerFenetre::getNewDefaultLMFractal),
			Map.entry("Plante", LindenmayerFenetre::getNewDefaultLMPlante),
			Map.entry("Arbre", LindenmayerFenetre::getNewDefaultLMArbre)
		));

		defaultLMSupplier = lmSuppliers.keySet().stream().findFirst().get();
	}

	public LindenmayerFenetre(String titre, int largeur, int hauteur){
		super(titre, largeur, hauteur, lmSuppliers.get(defaultLMSupplier));
	}

	// GETTERS
	private static Lindenmayer getNewDefaultLMFractal(int x, int y){
		return new Lindenmayer(x, y, LSystem.newFractal(), Map.ofEntries(
			Map.entry('X', Lindenmayer.Logo::av),
			Map.entry('Y', Lindenmayer.Logo::av),
			Map.entry('+', l -> l.rotG(60)),
			Map.entry('-', l -> l.rotD(60))
		));
	}
	private static Lindenmayer getNewDefaultLMPlante(int x, int y){
		return new Lindenmayer(x, y, LSystem.newPlante(), Map.ofEntries(
			Map.entry('X', l -> {}),
			Map.entry('F', Lindenmayer.Logo::av),
			Map.entry('+', l -> l.rotG(
				LindenmayerFenetre.randInt(20, 30)
			)),
			Map.entry('-', l -> l.rotD(
				LindenmayerFenetre.randInt(20, 30)
			))
		), 5);
	}
	private static Lindenmayer getNewDefaultLMArbre(int x, int y){
		return new Lindenmayer(x, y, LSystem.newArbre(), Map.ofEntries(
			Map.entry('0', Lindenmayer.Logo::av),
			Map.entry('1', Lindenmayer.Logo::av),
			Map.entry('+', l -> l.rotG(45)),
			Map.entry('-', l -> l.rotD(45))
		), 5);
	}

	// SETTERS
	@Override
	protected void mise_en_page(int maxX, int maxY){
		super.mise_en_page(maxX, maxY);

		lmButtons = new ButtonGroup();
		lmSuppliers.keySet().forEach(name -> {
			JRadioButton b = new JRadioButton(name);
			b.addActionListener(this);

			lmButtons.add(b);
			getPanel1().add(b);
		});

		setVisible(true);
	}

	// FUNCTIONS
	private static int randInt(int min, int max){
		return (int) (Math.random()*(max - min + 1) + min);
	}

	// LISTENERS
	@Override
	public void actionPerformed(ActionEvent e){
		super.actionPerformed(e);

		BiFunction<Integer, Integer, Lindenmayer> lmSupplier = lmSuppliers.get(
			e.getActionCommand()
		);
		if(lmSupplier != null) setDrawingGetter(lmSupplier);
	}
}
