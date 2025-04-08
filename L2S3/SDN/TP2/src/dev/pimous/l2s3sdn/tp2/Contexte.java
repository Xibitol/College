package dev.pimous.l2s3sdn.tp2;

import java.awt.Color;

public class Contexte{

	private int x;
	private int y;
	private int angle;
	private Color color;
	private boolean writing;
	private int step;
	
	public Contexte(
		int x, int y, int angle,
		Color color, boolean writing, int step
	){
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.color = color;
		this.writing = writing;
		this.step = step;
	}

	// GETTERS
	public int getX(){ return x; }
	public int getY(){ return y; }
	public int getAngle(){ return angle; }
	public Color getColor(){ return color; }
	public boolean isWriting(){ return writing; }
	public int getStep(){ return step; }
}
