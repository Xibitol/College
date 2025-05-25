package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import model.ColoredRectangle;
import model.Dessin;

public class ZoneDessin extends JPanel{

	private Dessin model;

	public ZoneDessin(Dessin model){
		super();

		this.model = model;
	}

	// FUNCTIONS
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		model.getRectangles().forEach(cr -> drawColoredRectangle(g, cr));
	}
	private void drawColoredRectangle(Graphics g,
		ColoredRectangle coloredRectangle
	){
		Graphics2D g2d = (Graphics2D) g;

		if(!coloredRectangle.isColorless()){
			g2d.setColor(coloredRectangle.getColor());
			g2d.fill(coloredRectangle);
		}else{
			g2d.setColor(Color.BLACK);
			g2d.draw(coloredRectangle);
		}
	}
}
