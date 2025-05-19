package dev.pimous.l2s4poa.td5;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CompteurGraphique extends JFrame{

	private JButton bouton;
	private JLabel label;
	private JTextField affichage;
	private Container panneau;

	public CompteurGraphique(String titre, GestionnaireBouton gestBouton){
		super(titre);

		// Components
		this.bouton = new JButton("Compter");
		JButton bouton10 = new JButton("Par 10");

		this.label = new JLabel("Nb clics");

		this.affichage = new JTextField(2);
		this.affichage.setText("0");
		this.affichage.setHorizontalAlignment(JTextField.CENTER);
		this.affichage.setEditable(false);

		// Layout
		this.panneau = super.getContentPane();
		this.panneau.add(this.bouton, BorderLayout.LINE_START);
		this.panneau.add(bouton10, BorderLayout.LINE_END);

		Container infos = new Container();
		infos.setLayout(new BorderLayout());
		infos.add(this.label, BorderLayout.LINE_START);
		infos.add(this.affichage, BorderLayout.LINE_END);
		this.panneau.add(infos, BorderLayout.CENTER);

		this.panneau.setPreferredSize(
			this.panneau.getLayout().preferredLayoutSize(this.panneau)
		);

		// Listeners
		gestBouton.associeCible(this.affichage);
		gestBouton.setBoutons(List.of(this.bouton, bouton10));

		this.bouton.addActionListener(gestBouton);
		bouton10.addActionListener(gestBouton);
		this.addWindowListener(gestBouton);
		this.addKeyListener(gestBouton);

		this.setFocusable(true);
		requestFocus();
	}
}
