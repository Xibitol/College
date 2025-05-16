public class CompteurNoeud implements Observateur{
	
	private int nbRepertoires = 0;
	private int nbFichiers = 0;

	// GETTERS
	public int donneNbRepertoires(){ return nbRepertoires; }
	public int donneNbFichiers(){ return nbFichiers; }

	// FUNCTIONS
	@Override
	public void miseAJour(Observable observable, Object o) {
		if(!(o instanceof OpRepertoire op)) return;

		switch(op.donneNoeud()){
			case Repertoire r -> {
				switch(op.donneOperation()){
					case AJOUT -> nbRepertoires ++;
					case SUPPRESSION -> nbRepertoires --;
				}
			}
			case Fichier f -> {
				switch(op.donneOperation()){
					case AJOUT -> nbFichiers++;
					case SUPPRESSION -> nbFichiers--;
				}
			}
			default -> {}
		}
	}
}
