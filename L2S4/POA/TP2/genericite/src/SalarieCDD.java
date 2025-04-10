import java.text.ParseException;
import java.util.Date;

public class SalarieCDD extends Salarie {
    private Date debut, fin; // debut et fin du CDD

    public SalarieCDD(String nom, String prenom,
                      Date naissance, float salaire, Date debut, Date fin) {
        super(nom, prenom, naissance, salaire);
        this.debut = debut;
        this.fin = fin;
    }

    protected static boolean verifiePeriode(Date debut, Date fin) {
        if (debut.before(fin)) {
            return true;
        } else {
            throw new IllegalArgumentException("SalarieCDD : " + debut + " > " + fin);
        }
    }

    public static SalarieCDD creerSalarieCDD(
            String nom, String prenom,
            String dateNaissance, int salaire, String debut, String fin) {
        Date dDebut = verifieDate(debut);
        Date dFin = verifieDate(fin);
        verifiePeriode(dDebut, dFin);
        return new SalarieCDD(nom, prenom, verifieDate(dateNaissance),
                verifieSalaire(salaire), dDebut, dFin);
    }
}
