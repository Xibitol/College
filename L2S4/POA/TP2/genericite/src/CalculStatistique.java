import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

public class CalculStatistique {

    // methode s'appliquant a des listes de Personne (ou de sous-type de Personne)
    public static float calculAgeMoyen(List<? extends Personne> personnes) {
        int sommeAge = 0;
        for (Personne p : personnes) {
            // Java 1.8 est necessaire...
            // utilisation de LocalDate (a la place de Date) pour pouvoir
            // utiliser la methode between (cf 2 lignes + bas)
            LocalDate maintenant = LocalDate.of(2023,3,13) ;
            // conversion d'une Date en LocalDate (liee a un calendrier)...
            // Pas simple !... Un peu plus avec Java 1.9
            long time = p.donneDateNaissance().getTime();
            LocalDate naissance = Instant.ofEpochMilli(time).
                    atZone(ZoneId.systemDefault()).toLocalDate();
            // calcul de l'ecart entre maintenant et la date de naissance
            Period periode = Period.between(naissance, maintenant);
            // on veut la valeur en nombre d'annees...
            sommeAge += periode.getYears();
        }
        return sommeAge / personnes.size();
    }

    // methode s'appliquant a des listes de Salarie (ou de sous-type de Salarie)
    public static float calculSalaireMoyen(List<? extends Salarie> salaries) {
        int sommeSalaire = 0;
        for (Salarie s : salaries) {
            sommeSalaire += s.donneSalaire();
        }
        return sommeSalaire / salaries.size();
    }

    public static void main(String[] args) throws ParseException {

        Personne paul = Personne.creerPersonne("durand", "paul", "05-03-1997");
        if (paul == null) {
            throw new Error("Pb creation personne Durand");
        }
        Personne pierre = Personne.creerPersonne("dupont", "pierre", "05-11-1977");
        if (pierre == null) {
            throw new Error("Pb creation personne Dupont");
        }
        List<Personne> personnes = Arrays.asList(paul, pierre);

        float ageMoyen = calculAgeMoyen(personnes);
        if (ageMoyen != 35.) {
            throw new Error("Pb sur calcul age moyen, attendu = 35, obtenu = " + ageMoyen);
        }
        Salarie jacques = Salarie.creerSalarie("martin", "jacques", "30-08-1987", 1900);
        if (jacques == null) {
            throw new Error("Pb creation salarie Martin");
        }
        Salarie henri = Salarie.creerSalarie("durant", "henri", "13-06-1975", 2500);
        if (henri == null) {
            throw new Error("Pb creation salarie Jacques");
        }
        List<Salarie> salaries = Arrays.asList(jacques, henri);

        float ageMoyenSalaries = calculAgeMoyen(salaries);
        if (ageMoyenSalaries != 41.) {
            throw new Error("Pb sur calcul age moyen des salaries, attendu = 41, obtenu = " + ageMoyenSalaries);
        }
        float salaireMoyen = calculSalaireMoyen(salaries);
        if (salaireMoyen != 2200.) {
            throw new Error("Pb sur calcul salaire moyen, attendu = 2200, obtenu = " + salaireMoyen);
        }

        SalarieCDD hubert = SalarieCDD.creerSalarieCDD("bernard", "hubert",
                "05-12-1992", 1600, "01-09-2012", "31-08-2013");
        if (hubert == null) {
            throw new Error("Pb creation salarieCDD Bernard");
        }
        SalarieCDD roger = SalarieCDD.creerSalarieCDD("thomas", "roger",
                "13-05-1997", 1500, "01-09-2017", "31-12-2018");
        if (roger == null) {
            throw new Error("Pb creation salarieCDD Thomas");
        }
        List<SalarieCDD> salariesCDD = Arrays.asList(hubert, roger);

        float ageMoyenSalariesCDD = calculAgeMoyen(salariesCDD);
        if (ageMoyenSalariesCDD != 27.) {
            throw new Error("Pb sur calcul age moyen des salaries en CDD, attendu = 27, obtenu = " + ageMoyenSalariesCDD);
        }
        float salaireMoyenCDD = calculSalaireMoyen(salariesCDD);
        if (salaireMoyenCDD != 1550.) {
            throw new Error("Pb sur calcul salaire moyen CDD, attendu = 1550, obtenu = " + salaireMoyenCDD);
        }
        System.out.println("Tests OK...");
    }
}
