package dev.pimous.l2s3sdn.tp2;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * Fenetre graphique permet d'afficher les segments du Logo
 * @author csempe
 */
public class Fenetre extends JFrame{

    private JPanel zoneDessin;
    private ArrayList<Segment> mem = new ArrayList<>();

    /** Constructeur d'une fenetre granphique
     * @param titre
     * @param largeur
     * @param hauteur
     */
    public Fenetre(String titre, int largeur, int hauteur){
        super(titre);

        getContentPane().setLayout(new BorderLayout());
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.zoneDessin = new JPanel();
        this.zoneDessin.setSize(largeur,hauteur);
        this.zoneDessin.setPreferredSize(new Dimension(largeur,hauteur));
        this.getContentPane().add(this.zoneDessin, "Center");

        pack();
        setVisible(true);
        repaint();
    }

	// FUNCTIONS
	/** Trace une ligne
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param c
     */
    public void tracerLigne(int x1, int y1, int x2, int y2 , Color c){
		this.mem.add(new Segment(x1, y1, x2, y2, c ) );   // on ajoute a la structure de memorisation
		repaint();                                        // on repaint l'ensemble avec paint()
	}

	public void clear(){
		this.mem.clear();
	}

	// LISTENERS
    /** Dessine ou redessine tous les elements à l'ecran
     * @param g : reference du contexte graphique
     */
    @Override
    public void paint(Graphics g){
        g = this.zoneDessin.getGraphics();

        for(int i = 0; i < this.mem.size(); i++){
            Segment s = this.mem.get(i);
            g.setColor(s.getC());
            g.drawLine(s.getX1(),s.getY1(),s.getX2(),s.getY2());
        }
    }

	// INNER CLASSES
	/**
	 * Classe qui modelise un segment
	 * @author csempe
	 */
	private class Segment{

		private int x1, y1, x2, y2;
		private Color c;

		public Segment(int x1, int y1,int x2, int y2, Color c){
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			this.c = c;
		}

		public int getX1(){ return this.x1; }
		public int getY1(){ return this.y1; }
		public int getX2(){ return this.x2; }
		public int getY2(){ return this.y2; }
		public Color getC(){ return this.c; }
	}
}