package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.geom.Point2D;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.ControleurDessin;
import model.ColoredRectangle;
import model.Dessin;

public class VueDessin extends JPanel{

	private static final Dimension CANVAS_DIMENSION = new Dimension(
		640, 360
	);
	private static final String COORDS_FORMAT = "Start: %d %d; Size: %d %d";
	private static final String COLOR_LABEL = "Select color";
	
	private ZoneDessin canvas;
	private JPanel toolBar;
	private JLabel coordsLabel;
	private JButton colorButton;

	private Dessin model;
	private ControleurDessin controller = null;

	public VueDessin(Dessin model){
		super(new BorderLayout());

		this.model = model;

		// Canvas
		this.canvas = new ZoneDessin(model);
		this.canvas.setPreferredSize(CANVAS_DIMENSION);
		add(this.canvas, BorderLayout.CENTER);

		// Tools bar
		this.toolBar = new JPanel();
		add(this.toolBar, BorderLayout.SOUTH);

		this.coordsLabel = new JLabel();
		this.coordsLabel.setText(COORDS_FORMAT.formatted(0, 0, 0, 0));
		this.toolBar.add(this.coordsLabel, BorderLayout.WEST);

		this.colorButton = new JButton(COLOR_LABEL);
		this.colorButton.setActionCommand(
			ControleurDessin.CHANGE_COLOR_ACTION_CMD
		);
		this.toolBar.add(this.colorButton, BorderLayout.EAST);
	}

	// SETTERS
	public ControleurDessin attachController(ControleurDessin controller){
		ControleurDessin oldController = detachController();
		this.controller = controller;

		canvas.addMouseListener(this.controller);
		canvas.addMouseMotionListener(this.controller);
		colorButton.addActionListener(this.controller);

		return oldController;
	}
	public ControleurDessin detachController(){
		ControleurDessin oldController = this.controller;
		this.controller = null;

		if(oldController != null){
			canvas.removeMouseListener(oldController);
			canvas.addMouseMotionListener(oldController);
			colorButton.removeActionListener(oldController);
		}

		return oldController;
	}

	// FUNCTIONS
	@Override
	public void repaint(){
		if(canvas != null) canvas.repaint();

		super.repaint();
	}
	@Override
	protected void paintChildren(Graphics g){
		ColoredRectangle cr = model.getCurrentRect();
		Point2D start = model.hasCurrentRect() ? cr.getLocation()
			: MouseInfo.getPointerInfo().getLocation();
		Dimension dim = model.hasCurrentRect() ? cr.getSize() : new Dimension();

		this.coordsLabel.setText(COORDS_FORMAT.formatted(
			(int) start.getX(), (int) start.getY(),
			(int) dim.getWidth(), (int) dim.getHeight()
		));

		super.paintChildren(g);
	}
}
