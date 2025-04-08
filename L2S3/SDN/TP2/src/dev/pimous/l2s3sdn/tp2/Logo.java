package dev.pimous.l2s3sdn.tp2;

import java.awt.*;
import java.util.Stack;

/** Logo permet de dessiner sur un ecran avec des commandes successives
 * elementaires
 * @author csempe
 * @author Xibitol
 */
public class Logo {

    private static final double COEF_DIMINUTION = 0.7;
    private static final int HAUTEUR_FENETRE = 800;
    private static final int LARGUEUR_FENETRE = 1000;
	protected static Fenetre f = new Fenetre("LOGO",
		LARGUEUR_FENETRE, HAUTEUR_FENETRE
	);
	/** Constante indiquant si le stylo est en bas */
    public static final boolean PEN_DOWN = true;
    /** Constante indiquant si le stylo est en haut donc pas de trace */
    public static final boolean PEN_UP = false;

	private Stack<Contexte> moves = new Stack<>();
	private Stack<Contexte> memories = new Stack<>();
    private int x;
    private int y;
    private int angle;
    private Color color;
    private boolean writing;
    private int step;

    /** Constructeur d'un Logo */
    public Logo(
		int initX, int initY, int initAngle,
		Color initColor, boolean writing, int step
	){
		this.x = initX;
		this.y = initY;
		this.angle = initAngle;
		this.color = initColor;
		this.writing = writing;
        this.step = step;
    }

	// GETTERS
    /** Donne la position x du stylo */
    public int getXStylo(){ return x; }
    /** Donne la position y du stylo */
    public int getYStylo(){ return y; }
	/** Retourne l'angle actuel du Logo */
    public int getAngle(){ return angle; }
	/** Retourne la couleur actuelle */
    public Color getCouleur(){ return color; }
	/** Retourne la position actuelle du stylo */
    public boolean getEtatStylo(){ return writing; }
	/** Retourne le Pas avec lequel on avance */
	public int getPas(){ return step; }
	public boolean memoVide(){ return memories.empty(); }

	// SETTERS
    /** Actualise la position x du stylo */
    public void setXStylo(int x){
		this.x = x;
    }
    /** Actualise la position y du stylo */
    public void setYStylo(int y){
		this.y = y;
	}
	private void move(int x, int y, int angle, Color color){
		double rad = Math.toRadians(angle);

		setXStylo(x + (int) Math.round(Math.cos(rad) * getPas()));
		setYStylo(y + (int) Math.round(Math.sin(rad) * getPas()));

        if(getEtatStylo() == PEN_DOWN)
            f.tracerLigne(
				x, HAUTEUR_FENETRE - y,
				getXStylo(), HAUTEUR_FENETRE - getYStylo(),
				color
			);
	}
	/** Avance de PAS en tracant si le stylo est en bas */
    public void av(){
		move(getXStylo(), getYStylo(), getAngle(), getCouleur());
		save(moves);
    }
	/** Permet de revenir en arriere sur le dernier tracé */
    public void retourArriere(){
		moves.pop();
		f.clear();

		for(Contexte c : moves)
			move(c.getX(), c.getY(), c.getAngle() - 180, c.getColor());
    }

    /** Actualise l'angle */
    public void setAngle(int a){
		this.angle = a;
    }
	/** Rotation a droite sens horaire */
	public void rotD(int a){
		angle = (angle - a)%360;
	}
	/** Rotation a gauche */
	public void rotG(int a){
		angle = (angle + a)%360;
	}

    /** Actualise la couleur */
    public void setColor(Color color){
        this.color = color;
    }

	/** stylo en bas */
    public void StyloBas(){
		writing = Logo.PEN_DOWN;
	}
	/** stylo en haut */
	public void StyloHaut(){
		writing = Logo.PEN_UP;
	}

    /** Actualise le Pas avec lequel on avance */
    public void setPas(int step){
		this.step = step;
    }
	/** Reduction du PAS avec un Coef de Recduction */
	public void reduction(){
		step = (int) (step*COEF_DIMINUTION);
	}

	private void save(Stack<Contexte> contexts){
		contexts.push(new Contexte(x, y, angle, color, writing, step));
	}
	/** Memorisation du contexte actuel du Logo */
    public void memo(){
		save(memories);
    }
    private void load(Stack<Contexte> contexts){
		Contexte c = contexts.pop();

		x = c.getX();
		y = c.getY();
		angle = c.getAngle();
		color = c.getColor();
		writing = c.isWriting();
		step = c.getStep();
    }
    /** Recuperation du dernier contexte et restitution du contexte dans les
	 * variables
	 */
    public void recupMemo(){
		load(memories);
    }

	// FUNCTIONS
	/** Chaine de caractere representant le Logo */
    @Override
    public String toString(){
        return "x= %d y= %d angle : %d EtatStylo : %s".formatted(
			getXStylo(), getYStylo(), getAngle(),
			getEtatStylo() == Logo.PEN_UP ? "en haut" : "en bas"
		);
    }
}