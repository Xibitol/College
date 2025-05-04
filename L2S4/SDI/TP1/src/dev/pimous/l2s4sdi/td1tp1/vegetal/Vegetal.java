package dev.pimous.l2s4sdi.td1tp1.vegetal;

import java.awt.Color;
import java.awt.Graphics;

import dev.pimous.l2s4sdi.td1tp1.RecursiveDrawing;

public class Vegetal extends RecursiveDrawing{

	private static final int DEFAULT_TAILLE = 40;
	private static final int DEFAULT_PROFONDEUR = 3;

	public Vegetal(int x, int y){
		super(x, y, DEFAULT_TAILLE, DEFAULT_PROFONDEUR);
	}

	// FUNCTIONS
	@Override
	public void draw(Graphics g) throws InterruptedException{
		branche(getX(), getY(), getTaille(), getProfondeur(), g);
	}

	protected void fruit(int x, int y, Graphics g){
		g.setColor(Vegetal.aleaCouleur());
		g.fillOval(x,y, 5, 5 );
		g.setColor(Color.black);
	}
	protected void branche(
		int x, int y,
		int longueur, int profondeur,
		Graphics g
	) throws InterruptedException{
		if(profondeur <= 0){
			fruit(x, y, g);
			return;
		}

		double angle = Vegetal.aleatoire(10, 13)/10.0;

		int x1= Math.round((float) (x - longueur*Math.cos(angle)));
		int y1= Math.round((float) (y - longueur*Math.sin(angle)));

		int x2=Math.round((float) (x + longueur*Math.cos(angle)));
		int y2=Math.round((float) (y - longueur*Math.sin(angle)));

		branche(x2, y2, longueur - 15, profondeur - 1, g);
		attendre(100);
		g.drawLine(x, y, x2, y2);
		attendre(100);

		branche(x1, y1, longueur - 15, profondeur - 1, g);
		attendre(100);
		g.drawLine(x, y, x1, y1);
		attendre(100);

		fruit(x, y, g);
	}
}