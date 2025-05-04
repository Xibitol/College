package dev.pimous.l2s4sdi.td1tp1.vegetal;

import java.awt.Color;
import java.awt.Graphics;

public class Vegetal{

	private int x, y;
	private int longueur = 40;
	private int profondeur = 3;

	protected Vegetal(int x, int y){
		this.x = x;
		this.y = y;
	}

	// GETTERS
	private static int aleatoire(int min, int max){
		return  min + (int) (Math.random()*(max - min + 1));
	}
	private static Color aleaCouleur(){
		return new Color(
			Vegetal.aleatoire(0,255),
			Vegetal.aleatoire(0,255),
			Vegetal.aleatoire(0,255)
		);
	}


	// FUNCTIONS
	public void draw(Graphics g){
		try{
			branche(x, y, longueur, profondeur, g);
		}catch(InterruptedException e){}
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
		attendre();
		g.drawLine(x, y, x2, y2);
		attendre();

		branche(x1, y1, longueur - 15, profondeur - 1, g);
		attendre();
		g.drawLine(x, y, x1, y1);
		attendre();

		fruit(x, y, g);
	}

	private void attendre() throws InterruptedException{
		Thread.sleep(500);
	}
}