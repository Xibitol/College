package dev.pimous.l2s4sdi.td1tp1;

import java.awt.Color;
import java.awt.Graphics;

public abstract class RecursiveDrawing{

	private int x, y;
	private int taille;
	private int profondeur;

	protected RecursiveDrawing(int x, int y, int taille, int profondeur){
		this.x = x;
		this.y = y;
		this.taille = taille;
		this.profondeur = profondeur;
	}

	// GETTERS
	public static int aleatoire(int min, int max){
		return  min + (int) (Math.random()*(max - min + 1));
	}
	public static Color aleaCouleur(){
		return new Color(
			RecursiveDrawing.aleatoire(0,255),
			RecursiveDrawing.aleatoire(0,255),
			RecursiveDrawing.aleatoire(0,255)
		);
	}

	public int getX(){ return x; }
	public int getY(){ return y; }
	public int getTaille(){ return taille; }
	public int getProfondeur(){ return profondeur; }

	// FUNCTIONS
	public abstract void draw(Graphics g) throws InterruptedException;

	protected final void attendre(long millis) throws InterruptedException{
		Thread.sleep(millis);
	}
}