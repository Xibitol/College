import java.util.HashSet;
import java.util.Set;

public class RepertoireObservable extends Repertoire implements Observable{

	private Set<Observateur> observateurs = new HashSet<>();

	public RepertoireObservable(String nom){
		super(nom);
	}

	// SETTERS
	@Override
	public boolean ajouteElt(Noeud nouveau){
		boolean ajoute = super.ajouteElt(nouveau);

		if(ajoute) notifier(new OpRepertoire(Operation.AJOUT, nouveau));

		return ajoute;
	}
	@Override
	public boolean supprimeElt(Noeud existant){
		boolean supprime = super.supprimeElt(existant);

		if(supprime)
			notifier(new OpRepertoire(Operation.SUPPRESSION, existant));

		return supprime;
	}

	@Override
	public void enregistrer(Observateur ob){
		observateurs.add(ob);
	}
	@Override
	public void desEnregistrer(Observateur ob) {
		observateurs.remove(ob);
	}

	// FUNCTIONS
	@Override
	public void notifier(OpRepertoire op){
		observateurs.forEach(ob -> ob.miseAJour(this, op));
	}
}
