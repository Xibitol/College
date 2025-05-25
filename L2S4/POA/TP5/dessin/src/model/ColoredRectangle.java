package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

public class ColoredRectangle extends Rectangle{

	private Point2D end;
	private Color color = null;

	public ColoredRectangle(Point2D start){
		super(new Point((int) start.getX(), (int) start.getY()));

		this.end = start;
	}

	// GETTERS
	public Point2D getEndLocation(){ return end; }
	public boolean isColorless(){ return color == null; }
	public Color getColor(){ return color; }

	// SETTERS
	public void setStartLocation(Point2D start){
		setLocation(new Point((int) start.getX(), (int) start.getY()));
	}
	@Override
	public void setLocation(int x, int y){
		super.setLocation(x, y);
		updateDimension();
	}
	public void setEndLocation(Point2D end){
		this.end = end;
		updateDimension();
	}
	private void updateDimension(){
		setSize(new Dimension(
			(int) (this.end.getX() - getLocation().getX()),
			(int) (this.end.getY() - getLocation().getY())
		));
	}
	public void setColor(Color color){
		this.color = color;
	}
}
