import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test{
	
	public static void main(String[] args){
		Conteneur cs = new Conteneur(100, 10);
	
		assert cs.ajout(new Colis(10, 1));
		assert cs.ajout(new Colis(10, 8));

		assert cs.conditionChargement(new Colis(10, 1));
		assert !cs.ajout(new Colis(10, 2));

		assert cs.donneDistance() == 100;
		assert cs.donnePoids() == 20;
		assert cs.donneVolume() == 9;

		assert cs.cout() == 2000;

		cs = new ConteneurUrgent(100, 10, 10);
	
		assert cs.ajout(new Colis(1, 1));
		assert cs.ajout(new Colis(8, 8));

		assert cs.conditionChargement(new Colis(1, 1));
		assert !cs.ajout(new Colis(1, 2));
		assert !cs.ajout(new Colis(2, 1));

		assert cs.donneDistance() == 100;
		assert cs.donnePoids() == 9;
		assert cs.donneVolume() == 9;

		assert cs.cout() == 1800;

		testToString();
	}

	public static void testToString(){
		Random r = new Random();
		List<Colis> colis = new ArrayList<>();

		for(int i = 0; i < r.nextInt(10, 21); i++){
			int volume = r.nextInt(1, 11);

			colis.add(new Colis(r.nextInt(1, 100)*volume, volume));
		}

		Conteneur cs = new Conteneur(1500, 76);
		for(Colis c : colis) cs.ajout(c);

		System.out.println(cs);

		// ---
		cs = new ConteneurUrgent(
			1500, 76, 2000
		);
		for(Colis c : colis) cs.ajout(c);

		System.out.println(cs);
	}
}
