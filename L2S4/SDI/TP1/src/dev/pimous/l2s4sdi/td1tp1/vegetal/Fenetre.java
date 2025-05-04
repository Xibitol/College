package dev.pimous.l2s4sdi.td1tp1.vegetal;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenetre extends JFrame implements ActionListener, MouseListener{

    private JPanel zoneDessin;
    private JPanel p1;
    private JPanel p2;

	private ExecutorService executor;
	private Consumer<Vegetal> drawer;

	// CONSTRUCTEUR
    public Fenetre(String titre, int largeur, int hauteur) {
        super(titre);

        getContentPane().setLayout(new BorderLayout());
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        mise_en_page(largeur, hauteur);

		drawer = (vegetal) -> vegetal.draw(this.zoneDessin.getGraphics());
		restartExecutor();
    }

	// SETTERS
	private void ajouteBouton(String label, JPanel p){
        JButton b = new JButton(label);
        b.addActionListener(this);
        p.add(b);
    }
	private void ajouterArbre(int x, int y){
		CompletableFuture.completedFuture(
			new Vegetal(x, y)
		).thenAcceptAsync(drawer, executor);
	}
	private void restartExecutor(){
		if(executor != null) executor.shutdownNow();
		executor = Executors.newFixedThreadPool(10);
	}

    private void mise_en_page(int maxX, int maxY){
        this.p1 = new JPanel(new GridLayout());

        this.p2 = new JPanel(new GridLayout());
        ajouteBouton("Dessine", p2);
        ajouteBouton("Effacer", p2);

        this.zoneDessin = new JPanel();
        this.zoneDessin.setSize(maxX, maxY);
        this.zoneDessin.setPreferredSize(new Dimension(maxX, maxY));

        this.getContentPane().add(this.p1, "North");
        this.getContentPane().add(this.zoneDessin,"Center");
        this.getContentPane().add(this.p2,"South");

        addMouseListener(this);

        pack();
        setVisible(true);
    }

	// FUNCTIONS
	@Override
    public void paint(Graphics g){
        this.p1.repaint();
        this.p2.repaint();
    }
    private void effacer(){
        Graphics g = this.zoneDessin.getGraphics();

        g.setColor(getBackground());
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

	// LISTENERS
	@Override
    public void actionPerformed(ActionEvent e){
    	switch(e.getActionCommand()){
			case "Dessine":
				ajouterArbre(250, 350);
				break;
			case "Effacer":
				restartExecutor();
				effacer();
				break;
		}
    }

	@Override
    public void mousePressed(MouseEvent e){
		ajouterArbre(e.getX(), e.getY());
	}
	@Override
    public void mouseClicked(MouseEvent e){}
	@Override
    public void mouseReleased(MouseEvent e){}
	@Override
    public void mouseEntered(MouseEvent e){}
	@Override
    public void mouseExited(MouseEvent e){}
}

