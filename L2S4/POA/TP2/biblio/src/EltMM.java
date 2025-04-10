public abstract class EltMM{
	
	private String titre;
	private String auteur;
	private int duree;
	private boolean presentEnRayon = true;
	private String commentaires = "<pas de commentaires>";

	protected EltMM(String titre, String auteur, int duree){
		this.titre = titre;
		this.auteur = auteur;
		this.duree = duree;
	}

	// GETTERS
    public String donneTitre(){ return this.titre; }
	public String donneType(){ return getClass().getSimpleName(); }
    public String donneAuteur(){ return this.auteur; }
	public int donneDuree(){ return this.duree; }
	public boolean donneEtatRayon(){ return this.presentEnRayon; }
	public String donneCommentaires(){ return this.commentaires; }

	// SETTERS
	public void changeEtatRayon(boolean etat){
        this.presentEnRayon = etat;
    }
	public void ajouteCommentaires(String comment){
		this.commentaires = comment;
	}

	// FUNCTIONS
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();

		sb.append("%s:\n".formatted(donneType()));
		sb.append("\tTitre: %s\n".formatted(titre));
		sb.append("\tAuteur: %s\n".formatted(auteur));
		sb.append("\tDuree: %d minutes\n".formatted(duree));
		sb.append("\tEtat: %s\n".formatted(
			presentEnRayon ? "disponible" : "indisponible"
		));
		sb.append("\tCommentaires: %s\n".formatted(commentaires));

		return sb.toString();
	}
}
