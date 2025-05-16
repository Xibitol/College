public class RepertoireAutoObservable extends RepertoireObservable{

	public RepertoireAutoObservable(String nom){
		super(nom);
	}

	// SETTERS
	@Override
	public boolean ajouteElt(Noeud nouveau){
		boolean ajoute = super.ajouteElt(nouveau);

		if(ajoute && nouveau instanceof RepertoireObservable ro)
			donneObservateurs().forEach(ro::enregistrer);

		return ajoute;
	}
	@Override
	public boolean supprimeElt(Noeud existant) {
		boolean supprime = super.supprimeElt(existant);

		if(supprime && existant instanceof RepertoireObservable ro)
			donneObservateurs().forEach(ro::desEnregistrer);

		return supprime;
	}
}
