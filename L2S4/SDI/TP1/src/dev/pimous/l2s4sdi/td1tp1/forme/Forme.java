package dev.pimous.l2s4sdi.td1tp1.forme;

import java.awt.Graphics;

import dev.pimous.l2s4sdi.td1tp1.RecursiveDrawing;

public class Forme extends RecursiveDrawing{

	private static final int DEFAULT_TAILLE = 60;
	private static final int DEFAULT_PROFONDEUR = 5;

	public Forme(int x, int y){
		super(x, y, DEFAULT_TAILLE, DEFAULT_PROFONDEUR);
	}

	// FUNCTIONS
	@Override
	public void draw(Graphics g) throws InterruptedException{
		figure(getX(), getY(), getTaille(), getProfondeur(), g);
	}

	protected void figure(
		int x, int y,
		int largeur, int profondeur,
		Graphics g
	) throws InterruptedException{
		if(profondeur <= 0) return;
		else if(profondeur > 1){
			for(int i = 0; i < 4; i++){
				figure(
					x + largeur*(i > 0 && i < 3 ? 1 : -1),
					y + largeur*(i > 1 ? 1 : -1),
					largeur/2, profondeur - 1, g
				);
			}
		}

		g.setColor(RecursiveDrawing.aleaCouleur());
		g.fillRect(x - largeur/2, y - largeur/2, largeur, largeur);
		attendre(1);
	}
}