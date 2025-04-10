import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Personne {
    private String nom, prenom;
    private Date naissance;
    
    protected static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    // l'appel direct du constructeur n'est pas autoris'e.
    // pour creer une instance il faut passer par la methode de classe creerPersonne
    protected Personne(String nom, String prenom, Date dateNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.naissance = dateNaissance;
    }

    public Date donneDateNaissance() {
        return this.naissance;
    }

    // controle de la date dans une methode de classe qui pourra etre appelee dans les
    // sous-classes de Personne (notamment SalarieCDD)
    protected static Date verifieDate(String date) {
        sdf.setLenient(false); // force detection date incoherente
        try {
            return sdf.parse(date);
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Personne : " + date + " date de naissance incorrecte");
        }
    }

    // utilisation d'une methode de classe pour traiter l'exception (si date fournie incorrecte)
    // et retourner null (impossible a realiser en cas d'appel direct du constructeur)
    public static Personne creerPersonne(String nom, String prenom, String dateNaissance) {
        return new Personne(nom, prenom, verifieDate(dateNaissance));
    }
}
