package dev.pimous.l2s4sdi.td1tp1;

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
import java.util.function.BiFunction;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class RecursiveFenetre<D extends RecursiveDrawing>
	extends JFrame
	implements ActionListener, MouseListener{

	private static final int DRAWINGS_LIMIT = 8;

    private JPanel zoneDessin;
    private JPanel p1;
    private JPanel p2;

	private ExecutorService executor;
	private BiFunction<Integer, Integer, D> drawingGetter;
	private int count = 0;

	// CONSTRUCTEUR
    public RecursiveFenetre(String titre, int largeur, int hauteur,
		BiFunction<Integer, Integer, D> drawingGetter
	){
        super(titre);

		this.drawingGetter = drawingGetter;

        getContentPane().setLayout(new BorderLayout());
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        mise_en_page(largeur, hauteur);

		restartExecutor();
		repaint();
    }

	// GETTERS
	protected final ExecutorService getExecutor(){ return executor; }
	public JPanel getZoneDessin(){ return zoneDessin; }

	// SETTERS
	private void ajouteBouton(String label, JPanel p){
        JButton b = new JButton(label);
        b.addActionListener(this);
        p.add(b);
    }
	protected final void restartExecutor(){
		if(executor != null) executor.shutdownNow();
		executor = Executors.newFixedThreadPool(DRAWINGS_LIMIT);
	}
	private final void resetCounter(){
		count = 0;
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
	private void drawDessin(D drawing){
		try{
			drawing.draw(getGraphics());
		}catch(InterruptedException e){}
	}
	private void drawAsync(int x, int y){
		if(++count > DRAWINGS_LIMIT) return;

		CompletableFuture<Integer> xcf = CompletableFuture.completedFuture(x);
		CompletableFuture<Integer> ycf = CompletableFuture.completedFuture(y);

		xcf.thenCombine(ycf, drawingGetter)
			.thenAcceptAsync(this::drawDessin, executor);
	}
	private void drawAsync(){
		Dimension d = getZoneDessin().getSize();
		drawAsync(d.width/2, d.height/2);
	}
    public void effacer(){
        Graphics g = getZoneDessin().getGraphics();

        g.setColor(getBackground());
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

		resetCounter();
    }

	// LISTENERS
	@Override
    public void actionPerformed(ActionEvent e){
    	switch(e.getActionCommand()){
			case "Dessine":
				drawAsync();
				break;
			case "Effacer":
				restartExecutor();
				effacer();
				break;
		}
    }

	@Override
    public void mousePressed(MouseEvent e){
		drawAsync(e.getX(), e.getY());
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