package model;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.SequencedCollection;

public class Dessin{
	
	private SequencedCollection<ColoredRectangle> rectangles;
	private Color defaultColor = new Color(0xFF9800);

	private ColoredRectangle currentRect;

	{
		rectangles = new ArrayList<>();
	}

	// GETTERS
	public SequencedCollection<ColoredRectangle> getRectangles(){
		SequencedCollection<ColoredRectangle> rects = rectangles;

		if(hasCurrentRect()){
			rects = new ArrayList<>(rectangles);
			rects.add(getCurrentRect());
		}

		return Collections.unmodifiableSequencedCollection(rects);
	}
	public SequencedCollection<ColoredRectangle> getFixedRectangles(){
		return Collections.unmodifiableSequencedCollection(rectangles);
	}
	public boolean hasCurrentRect(){ return currentRect != null; }
	public ColoredRectangle getCurrentRect(){ return currentRect; }
	public Color getDefaultColor(){ return defaultColor; }

	// SETTERS
	public ColoredRectangle newRectangle(Point2D start){
		currentRect = new ColoredRectangle(start);
		return currentRect;
	}
	public void pushRectangle(){
		if(currentRect == null) return;

		rectangles.add(getCurrentRect());
		popRectangle();
	}
	public void popRectangle(){
		currentRect = null;
	}
	public void setDefaultColor(Color defaultColor){
		this.defaultColor = defaultColor;
	}
}
