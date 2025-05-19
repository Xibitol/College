package dev.pimous.l2s4poa.td5;

import javax.swing.JFrame;

public class Main{

	public static void main(String[] args){
		GestionnaireBouton gestBouton = new GestionnaireBouton();
		CompteurGraphique interfaceGraphique = new CompteurGraphique(
			"Comptage", gestBouton
		);
		interfaceGraphique.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		interfaceGraphique.pack();
		interfaceGraphique.setVisible(true);
	}
}