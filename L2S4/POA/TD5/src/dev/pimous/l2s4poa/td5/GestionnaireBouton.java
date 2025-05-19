package dev.pimous.l2s4poa.td5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Collection;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JTextField;

public class GestionnaireBouton
	implements ActionListener, WindowListener, KeyListener{
	
	private JTextField affichage;
	private Collection<JButton> boutons = new HashSet<>();

	// SETTERS
	public void associeCible(JTextField cibleAction){
		affichage = cibleAction;
		this.boutons.addAll(boutons);
	}
	public void setBoutons(Collection<JButton> boutons){
		this.boutons.clear();
		this.boutons.addAll(boutons);
	}

	// LISTENERS
	@Override
	public void actionPerformed(ActionEvent e){
		if(!(e.getSource() instanceof JButton b)) return;

		String val = affichage.getText();
		int number = Integer.parseInt(val);

		affichage.setText(String.valueOf(number + switch(b.getText()){
			case "Compter" -> 1;
			case "Par 10" -> 10;
			default -> 0;
		}));
	}

	@Override
	public void windowOpened(WindowEvent e){}
	@Override
	public void windowActivated(WindowEvent e){}
	@Override
	public void windowIconified(WindowEvent e){
		affichage.setText(String.valueOf(0));
	}
	@Override
	public void windowDeiconified(WindowEvent e){}
	@Override
	public void windowDeactivated(WindowEvent e) {}
	@Override
	public void windowClosing(WindowEvent e){}
	@Override
	public void windowClosed(WindowEvent e){}

	@Override
	public void keyPressed(KeyEvent e){}
	@Override
	public void keyTyped(KeyEvent e){}
	@Override
	public void keyReleased(KeyEvent e){
		if(e.getKeyChar() == 'k')
			boutons.forEach(b -> b.setVisible(!b.isVisible()));
	}
}
