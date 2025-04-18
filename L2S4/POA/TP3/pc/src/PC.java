import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class PC{

	private Collection<Composant> composants = new ArrayList<>();

	public PC(Collection<Composant> composants){
		this.composants.addAll(composants);
	}

	// GETTERS
	public Collection<Composant> getComposants(){
		return Collections.unmodifiableCollection(composants);
	}
	public double bilanEnergie(){
		return composants.stream()
			.collect(Collectors.summingDouble(c -> c.donneEnergie()));
	}
}