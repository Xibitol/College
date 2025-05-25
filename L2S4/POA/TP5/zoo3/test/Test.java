import java.util.ArrayList;
import java.util.Collection;

public class Test{

	public static void main(String[] args) {
		Collection<Animal> animals = new ArrayList<>();

		animals.add(new MammifereAquatique(
			"Gérard", true, false
		));
		animals.add(new MammifereTerrestre(
			"Michel", false, "Mathématiques"
		));
		animals.add(new MammifereTerrestre(
			"Frédérique", true, "Monde des bisounours"
		));

		animals.add(new ReptileAquatique(
			"Marinette", true, true
		));
		animals.add(new ReptileTerrestre(
			"Jean-Eudes", true, "Charente-Maritime"
		));

		System.out.printf("Mammifères: %d.\n",
			animals.stream().filter(a -> a instanceof Mammifere).count()
		);
		System.out.printf("Reptiles: %d.\n",
			animals.stream().filter(a -> a instanceof Reptile).count()
		);
	}
}
