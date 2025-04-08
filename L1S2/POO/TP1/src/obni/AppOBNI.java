package obni;

public class AppOBNI {
	
	public static void main(String[] args) {
		ObjetBougeant xibitol = new ObjetBougeant();
		xibitol.setNom("Xibitol");
		xibitol.setX(5);
		xibitol.setY(2);
		xibitol.setR(1.);
		log("Premier OBNI : ", xibitol);

		// Test des fonctionnalitées seules
		log("--- Test des fonctionnalitées seules ---");

		xibitol.deplace(-1, 1);
		log("Après déplacement : ", xibitol);

		log("Est-il un point ? ", xibitol.estPoint());
		
		xibitol.grossir();
		log("Après grossissement : ", xibitol);

		xibitol.retrecir();
		log("Après rétrecissement : ", xibitol);

		xibitol.grossir(2.);
		log("Après grossissement de 2 : ", xibitol);

		xibitol.retrecir(2.);
		log("Après rétrecissement de 2 : ", xibitol);

		try{
			xibitol.absorbe(xibitol);
		}catch(IllegalArgumentException e){
			e.printStackTrace();
		}

		try{
			xibitol.estAbsorbe(xibitol);
		}catch(IllegalArgumentException e){
			e.printStackTrace();
		}

		log("Enfant pondu : ", xibitol.pond());

		// Test des fonctionnalitées avec d'autres OBNI
		log("--- Test des fonctionnalitées avec d'autres OBNI ---");
		
		ObjetBougeant rulietta = new ObjetBougeant();
		rulietta.setNom("Rulietta");
		rulietta.setX(1);
		rulietta.setY(4);
		rulietta.setR(.75);
		log("Nouvel OBNI : ", rulietta);

		log("Enfant pondu des deux OBNI : ", rulietta.pond(xibitol));
	}

	private static void log(Object... msg){
		StringBuilder sb = new StringBuilder(msg[0].toString());
		for(int i = 1; i < msg.length; i++){
			sb.append(msg[i].toString());
		}
		System.out.println(sb.toString());
	}
}
