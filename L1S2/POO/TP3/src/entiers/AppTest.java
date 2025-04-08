package entiers;

public class AppTest{
	
	public static void main(String[] args) {
		OutilsEntiers oe = new OutilsEntiers();

		testOutilsEntier(12);
		testOutilsEntier(37);

		System.out.println("Décomposition de 2100 : " +
			oe.decompositionFacteursPremiers(2100)
		);
		System.out.println(oe.nbDiviseursV2(2100));
		System.out.println("Décomposition de 14703 : " +
			oe.decompositionFacteursPremiers(14703)
		);
		System.out.println(oe.nbDiviseursV2(14703));
	}

	private static void testOutilsEntier(int n){
		OutilsEntiers oe = new OutilsEntiers();

		oe.afficherDiviseur(n);
		System.out.println(oe.listeDiviseur(n));
		System.out.println(oe.nbDiviseurs(n));
		System.out.println(oe.estPremier(n));
		System.out.println(oe.decompositionFacteursPremiers(n));
		System.out.println(oe.nbDiviseursV2(n));
	}
}
