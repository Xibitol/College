/**
 * Cette interface represente la classe Sujet vu en TD.
 * C'est une interface car la classe RepertoireObservable qui l'implemente
 * herite deja de la classe Repertoire et ne peut donc pas heriter d'une autre
 * classe.
 */
public interface Observable {
    void enregistrer(Observateur ob);
    void desEnregistrer(Observateur ob);
    void notifier(OpRepertoire op);
    String donneNom();
}
