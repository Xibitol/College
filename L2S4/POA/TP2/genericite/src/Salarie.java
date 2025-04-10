import java.text.ParseException;
import java.util.Date;

public class Salarie extends Personne {
    private float salaire;

    protected Salarie(String nom, String prenom, Date date, float salaire) {
        super(nom, prenom, date); // appel du cst de la super-classe (Personne)
        this.salaire = salaire;
    }

    public float donneSalaire() {
        return this.salaire;
    }

    protected static int verifieSalaire(int salaire) {
        if (salaire < 0) {
            throw new IllegalArgumentException("Salarie : " + salaire + " salaire negatif");
        } else {
            return salaire;
        }
    }

    public static Salarie creerSalarie(String nom, String prenom,
                                       String dateNaissance, int salaire) {
        return new Salarie(nom, prenom,
                verifieDate(dateNaissance), verifieSalaire(salaire));
    }
}
