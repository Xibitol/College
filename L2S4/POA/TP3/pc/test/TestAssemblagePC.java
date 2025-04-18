import java.util.List;

class TestAssemblagePC{
	
	public static void main(String[] args){
		PC goodPc = new PC(List.of(
			new Alimentation(0, 150, 750),
			new Processeur(1, 350,
				90, 4000, "AM4"
			),
			new Memoire(2, 80,
				10, 3700
			),
			new Memoire(3, 80,
				10, 3700
			),
			new DisqueDur(4, 120,
				50, 2*1000*1000*1000*1000
			)
		));
		PC badPc = new PC(List.of(
			new Alimentation(0, 150, 450),
			new Processeur(1, 350,
			150, 5000, "AM5"
			),
			new Processeur(2, 350,
			150, 5000, "AM5"
			),
			new Memoire(3, 80,
				20, 4500
			),
			new Memoire(4, 80,
				20, 4500
			),
			new Memoire(5, 80,
				20, 4500
			),
			new Memoire(6, 80,
				20, 4500
			),
			new DisqueDur(7, 120,
				50, 14*1000*1000*1000*1000
			),
			new DisqueDur(8, 120,
				50, 14*1000*1000*1000*1000
			),
			new DisqueDur(9, 120,
				50, 14*1000*1000*1000*1000
			),
			new DisqueDur(10, 120,
				50, 14*1000*1000*1000*1000
			),
			new DisqueDur(11, 120,
				50, 14*1000*1000*1000*1000
			)
		));

		System.out.println("GoodPC:");
		for(Composant c : goodPc.getComposants())
			System.out.printf("\t- %s\n", c);
		assert goodPc.bilanEnergie() >= 0 : "Bilan énergétique négatif.";
		System.out.printf("Bilan énergétique: %f\n",
			goodPc.bilanEnergie()
		);

		System.out.println("BadPC:");
		for(Composant c : badPc.getComposants())
			System.out.printf("\t- %s\n", c);
		assert badPc.bilanEnergie() < 0 : "Bilan énergétique négatif.";
		System.out.printf("Bilan énergétique: %f\n",
			badPc.bilanEnergie()
		);
	}
}
