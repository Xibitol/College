import java.util.SequencedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Conteneur{
	
	private int distance;
	private int volumeMax;
	private SequencedSet<Colis> colis = new TreeSet<>(Conteneur::compareColis);

	public Conteneur(int distance, int volumeMax){
		this.distance = distance;
		this.volumeMax = volumeMax;
	}

	// GETTERS
	private static int compareColis(Colis c1, Colis c2){
		return Integer.compare(c1.numero, c2.numero);
	}

	public int donneDistance(){ return distance; }
	public int donnePoids(){
		return colis.stream()
			.collect(Collectors.summingInt(c -> c.donnePoids()));
	}
	public int donneVolume(){
		return colis.stream()
			.collect(Collectors.summingInt(c -> c.donneVolume()));
	}

	public boolean conditionChargement(Colis c){
		return donneVolume() + c.donneVolume() <= volumeMax;
	}
	public int cout(){
		return donneDistance()*donnePoids();
	}

	// SETTERS
	public boolean ajout(Colis c){
		return conditionChargement(c) && colis.add(c);
	}

	// FUNCTIONS
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();

		sb.append("Conteneur (%d km et %d m³ max):\n".formatted(
			distance, volumeMax
		));
		colis.forEach(c -> sb.append("\t- %s\n".formatted(c.toString())));
		sb.append(
			"| Total: %d colis donnant %d m³ et %d kg pour %d €".formatted(
				colis.size(), donneVolume(), donnePoids(), cout()
			)
		);

		return sb.toString();
	}
}
