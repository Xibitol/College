package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JColorChooser;
import javax.swing.event.MouseInputListener;

import model.Dessin;
import view.VueDessin;

public class ControleurDessin implements MouseInputListener, ActionListener{

	private static final String COLOR_CHOOSER_TITLE
		= "Which color do you feel today?";
	public static final String CHANGE_COLOR_ACTION_CMD = "changeColor";

	private VueDessin view;
	private Dessin model;

	public ControleurDessin(VueDessin view, Dessin model){
		this.view = view;
		this.model = model;
	}

	// LISTENERS
	@Override
	public void mouseClicked(MouseEvent e){
		if(e.getButton() == MouseEvent.BUTTON3)
			model.getRectangles().forEach(rc -> {
				if(rc.contains(e.getPoint()))
					rc.setColor(model.getDefaultColor());
			});
	}
	@Override
	public void mousePressed(MouseEvent e){
		if(model.hasCurrentRect()
			|| e.getButton() != MouseEvent.BUTTON1
		) return;

		model.newRectangle(e.getPoint());
		view.repaint();
	}
	@Override
	public void mouseReleased(MouseEvent e){
		if(!model.hasCurrentRect()) return;

		model.getCurrentRect().setEndLocation(e.getPoint());
		model.pushRectangle();
		view.repaint();
	}
	@Override
	public void mouseEntered(MouseEvent e){}
	@Override
	public void mouseExited(MouseEvent e){
		model.popRectangle();
		view.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e){
		view.repaint();
	}
	@Override
	public void mouseDragged(MouseEvent e){
		if(model.hasCurrentRect())
			model.getCurrentRect().setEndLocation(e.getPoint());

		view.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e){
		switch(e.getActionCommand()){
			case CHANGE_COLOR_ACTION_CMD:
				model.setDefaultColor(JColorChooser.showDialog(view,
					COLOR_CHOOSER_TITLE, model.getDefaultColor()
				));

				break;
		}
	}
}
