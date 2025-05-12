package dev.pimous.l2s4sdi.td2tp2;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.Consumer;

import dev.pimous.l2s4sdi.td1tp1.RecursiveDrawing;

public class Lindenmayer extends RecursiveDrawing{

	private static final int DEFAULT_TAILLE = 10;
	private static final int DEFAULT_PROFONDEUR = 3;

	private LSystem ls;
	private Map<Character, Consumer<Logo>> actions = new HashMap<>();

	{
		actions.put('[', Logo::memo);
		actions.put(']', Logo::recupMemo);
	}

	public Lindenmayer(int x, int y, LSystem ls,
		Map<Character, Consumer<Logo>>actions,
		int profondeur
	){
		super(x, y, DEFAULT_TAILLE, profondeur);

		this.ls = ls;
		this.actions.putAll(actions);
	}
	public Lindenmayer(int x, int y, LSystem ls,
		Map<Character, Consumer<Logo>>actions
	){
		this(x, y, ls, actions, DEFAULT_PROFONDEUR);
	}

	// FUNCTIONS
	@Override
	public void draw(Graphics g) throws InterruptedException{
		ls.resetExp();
		for(int i = 0; i < getProfondeur(); i++) ls.derivation();

		Logo l = new Logo(g,
			getX(), getY(), -90, getTaille(),
			Color.BLACK, Logo.PEN_UP
		);
		for(char c : ls.getExpCourante().toCharArray())
			actions.get(c).accept(l);
	}

	// INNER CLASSES
	public static class Logo{

		private static final double COEFDIMINUTION = 0.7;

		public static boolean PEN_DOWN = true;
		public static boolean PEN_UP = false;

		private Graphics canvas;
		private int x;
		private int y;
		private int angle;
		private int pas;
		private Color couleur;
		private boolean positionStylo;

		protected static Stack<LogoMemo> p = new Stack<>();

		public Logo(
			Graphics graphics,
			int initX, int initY, int initAngle, int pas,
			Color initColor, boolean posStylo
		){
			this.canvas = graphics;

			this.x = initX;
			this.y = initY;
			this.angle = initAngle;
			this.pas = pas;
			this.couleur = initColor;
			this.positionStylo = posStylo;
		}

		// GETTERS
		public Graphics getCanvas(){ return canvas; }
		public int getX(){ return x; }
		public int getY(){ return y; }
		public int getAngle(){ return angle; }
		public int getPas(){ return pas; }
		public Color getCouleur(){ return couleur; }
		public boolean getEtatStylo(){ return positionStylo; }

		// SETTERS
		public void setX(int x){
			this.x = x;
		}
		public void setY(int y){
			this.y = y;
		}
		public void setAngle(int a){
			this.angle = a;
		}
		public void setPas(int pas){
			this.pas = pas;
		}
		public void setPositionStylo(boolean positionStylo){
			this.positionStylo = positionStylo;
		}

		public void av(){
			double radAngle = Math.toRadians(getAngle());
			int x = getX() + (int) Math.round(Math.cos(radAngle)*getPas());
			int y = getY() + (int) Math.round(Math.sin(radAngle)*getPas());

			getCanvas().setColor(getCouleur());
			getCanvas().drawLine(getX(), getY(), x, y);

			this.x = x;
			this.y = y;
		}
		public void rotG(int a){
			int newAngle = this.angle + a;
			angle = newAngle % 360;
		}
		public void rotD(int a){
			int newAngle = this.angle - a;
			this.angle = newAngle % 360;
		}
		public void reduction(){
			this.pas = (int) (this.pas*COEFDIMINUTION);
		}
		public void styloBas(){
			this.positionStylo = PEN_DOWN;
		}
		public void styleHaut(){
			this.positionStylo = PEN_UP;
		}

		public void memo(){
			Logo.p.push(new LogoMemo(this));
		}
		public void recupMemo(){
			Logo.p.pop().applyTo(this);
		}

		// INNER CLASSSES
		private static class LogoMemo{

			private int x;
			private int y;
			private int angle;
			private int pas;
			private boolean positionStylo;

			public LogoMemo(Logo logo){
				this.x = logo.getX();
				this.y = logo.getY();
				this.angle = logo.getAngle();
				this.pas = logo.getPas();
				this.positionStylo = logo.getEtatStylo();
			}

			// FUNCTIONS
			public void applyTo(Logo logo){
				logo.setX(x);
				logo.setY(y);
				logo.setAngle(angle);
				logo.setPas(pas);
				logo.setPositionStylo(positionStylo);
			}
		}
	}
}
