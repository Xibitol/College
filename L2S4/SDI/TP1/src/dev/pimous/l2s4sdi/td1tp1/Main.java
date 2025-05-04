package dev.pimous.l2s4sdi.td1tp1;

import dev.pimous.l2s4sdi.td1tp1.forme.FormeFenetre;
import dev.pimous.l2s4sdi.td1tp1.vegetal.VegetalFenetre;

public class Main{

	public static void main(String[] args) {
		new VegetalFenetre("Application autonome Java",
			500, 500
		);

		new FormeFenetre("Application formes recursives",
			1000, 1000
		);
	}
}
