import java.util.Scanner;

public class Main{

	public static void main(String[] args) {
		Main main = new Main();

		System.out.print("toRadian(45) -> ");
		System.out.println(main.toRadian(45));
		System.out.print("toDegree(%f) -> ".formatted(Math.PI/4));
		System.out.println(main.toDegree(Math.PI/4));

		System.out.print("estBissextile(2023) -> ");
		System.out.println(main.estBissextile(2023));
		System.out.print("estBissextile(2020) -> ");
		System.out.println(main.estBissextile(2020));

		Scanner sc = new Scanner(System.in);
		System.out.print("Entrez un entier : ");
		int n = sc.nextInt();
		System.out.println("Voici la table de multiplication de %d :".
			formatted(n)
		);
		for(int m = 0; m <= 10; m++){
			System.out.println("\t%d fois %d = %d".formatted(n, m, n*m));
		}
		sc.close();
	}

	// CONVERTISSEUR
	public double toRadian(double degre){
		return Math.PI*degre/180;
	}
	public double toDegree(double rad){
		return rad*180/Math.PI;
	}

	// ANNÉE BISSEXTILE
	public boolean estBissextile(int annee){
		return annee%400 == 0 || annee%4 == 0 && annee%100 != 0;
	}
}