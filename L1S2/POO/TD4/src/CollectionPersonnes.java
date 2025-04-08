import java.util.ArrayList;
import java.util.List;

public class CollectionPersonnes {
	
	private List<Personne> groupe;

	public CollectionPersonnes(){
		this.groupe = new ArrayList<>();
	}

	// Getters
	public List<Personne> getCollection(){ return List.copyOf(groupe); }
	public Personne personneDeNumero(int n){
		int i = 0;
		while(i < groupe.size() && groupe.get(i).getNumero() != n)
			i++;
		return i < groupe.size() ? groupe.get(i) : null;
	}
	public Personne personneDeNom(String s){
		int i = 0;
		while(i < groupe.size() && !groupe.get(i).getNom().equals(s))
			i++;
		return i < groupe.size() ? groupe.get(i) : null;
	}
	public Personne personnePlusAgee(){
		Personne per = groupe.get(0);
		for(int i = 1; i < groupe.size(); i++)
			per = groupe.get(i);
		return per;
	}
	public int nbPersonneAge(int age){
		int nb = 0;
		for(Personne p : groupe)
			if(p.getAge() == age)
				nb++;
		return nb;
	}

	// Setters
	public void add(Personne p){ groupe.add(p); }

	// Functions
	public void afficherPersonnes(){
		System.out.println("Personnes du groupe : ");
		for(Personne p : groupe)
			System.out.println("\t" + p);
	}
}
