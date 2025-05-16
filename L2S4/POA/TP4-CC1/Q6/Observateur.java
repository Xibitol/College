/**
 * Represente la classe Observateur vue en TD.
 */
public interface Observateur {
    /** methode appelee lors de la notification
     *
     * @param observable le sujet qui notifie l'observateur
     * @param o information fournie par le sujet
     */
    void miseAJour(Observable observable, Object o);
}
