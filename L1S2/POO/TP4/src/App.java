import java.io.IOException;
import java.util.stream.Collectors;

/**
 *
 * @author P. Rodriguez
 */
public class App {

    public static void main(String[] args) throws IOException {
        Personne marc = new Personne(
			0, "Jean", "Marc",
			'h', 1978,
			"Surfeur", 3
		);
        Personne paul = new Personne(
			0, "Jean", "Paul",
			'h', 1978,
			"Brouilleur", 2
		);

		System.out.println(
			"Première personne : %s, %d, %s".formatted(
				marc.getNom(),
				marc.getAnneeNaiss(),
				marc.getGroupe()
			)
		);
		System.out.println(
			"Deuxième personne : %s, %d, %s".formatted(
				paul.getNom(),
				paul.getAnneeNaiss(),
				paul.getGroupe()
			)
		);

		marc.setGroupe("Obfuscateur");
		System.out.println("Groupe première personne : %s".formatted(
			marc.getGroupe()
		));
		paul.afficher();

		System.out.println("Marc : %s".formatted(marc)); 

		// MARC BIS
		// La valeur de base du toString est la référence vers l'objet dans la
		// mémoire.
		System.out.println("-- MARC BIS. --");
		Personne marcBis =  new Personne(
			marc.getId(), marc.getNom(), marc.getPrenom(),
			marc.estHomme() ? 'h' : 'f', marc.getAnneeNaiss(),
			marc.getGroupe(), marc.getCategorie()
		);

		System.out.println("Is %s == %s ? %b".formatted(
			marc, marcBis, marc == marcBis
		));
		System.out.println("Is %s.equals(%s) ? %b".formatted(
			marc, marcBis, marc.equals(marcBis)
		));

		// MARC TER
		System.out.println("-- MARC TER --");
		Personne marcTer =  marc;

		System.out.println("Is %s == %s ? %b".formatted(
			marc, marcTer, marc == marcTer
		));
		System.out.println("Is %s.equals(%s) ? %b".formatted(
			marc, marcTer, marc.equals(marcTer)
		));

		marcTer.setCategorie(0);
		marc.afficher();

		// COLLECTIONS DE PERSONNES
		System.out.println("-- COLLECTIONS DE PERSONNES. --");
		CollectionPersonnes cp = new CollectionPersonnes();

		cp.afficher();
		System.out.println("Effectif de l'année 1992 : %d".formatted(
			cp.effectifDeLAnnee(1992)
		));
		System.out.println("Personne la plus proche de %s : %s".formatted(
			marc, cp.laPlusProche(marc)
		));
		System.out.println(cp.lesPlusProches(marc).stream()
			.map(p -> p.toString()).collect(
				Collectors.joining(
					";\n\t",
					"Personnes les plus proches de %s :\n\t".formatted(marc),
					"."
				)
			)
		);
		System.out.println(cp.personnesGroupe("Bosseur").stream()
			.map(p -> p.toString()).collect(
				Collectors.joining(
					";\n\t",
					"Personnes du groupe \"Bosseur\" :\n\t",
					"."
				)
			)
		);
		
		// MATCH SCORE
		System.out.println("-- MATCH SCORE. --");
		Personne p1 = new Personne(
			0, null, null, '\0',
			0, "", 0
		);
		Personne p2 = new Personne(0, "", "", '\0',
			0, "", 0
		);

		assert p1.matchScore(p2) == 11 : "Max score should be 11.";

		// Group
		p2.setGroupe("\0");
		assert p1.matchScore(p2) == 8 :
			"Match score should be 8 (Without group, cat. diff. of 0).";
		
		// Cat.
		p1.setCategorie(1);
		assert p1.matchScore(p2) == 6 :
			"Match score should be 6 (Without group, cat. diff. of 1).";
		p1.setCategorie(2);
		assert p1.matchScore(p2) == 4 :
			"Match score should be 4 (Without group, cat. diff. of 2).";
		
		// Age
		p1 = new Personne(0, null, null, '\0',
			0, "", 0
		);
		// Age 4pts
		p2 = new Personne(0, null, null, '\0',
		2, "", 0
		);
		assert p1.matchScore(p2) == 11 :
			"Match score should be 11 (Age diff. of 2).";
		// Age 3pts
		p2 = new Personne(0, null, null, '\0',
			3, "", 0
		);
		assert p1.matchScore(p2) == 10 :
			"Match score should be 10 (Age diff. of 3).";
		p2 = new Personne(0, null, null, '\0',
			5, "", 0
		);
		assert p1.matchScore(p2) == 10 :
			"Match score should be 10 (Age diff. of 5).";
		// Age 2pts
		p2 = new Personne(0, null, null, '\0',
			6, "", 0
		);
		assert p1.matchScore(p2) == 9 :
			"Match score should be 9 (Age diff. of 6).";
		p2 = new Personne(0, null, null, '\0',
			8, "", 0
		);
		assert p1.matchScore(p2) == 9 :
			"Match score should be 9 (Age diff. of 8).";
		// Age 1pts
		p2 = new Personne(0, null, null, '\0',
			9, "", 0
		);
		assert p1.matchScore(p2) == 8 :
			"Match score should be 8 (Age diff. of 9).";
		p2 = new Personne(0, null, null, '\0',
			11, "", 0
		);
		assert p1.matchScore(p2) == 8 :
			"Match score should be 8 (Age diff. of 11).";
		// Age 0pts
		p2 = new Personne(0, null, null, '\0',
			12, "", 0
		);
		assert p1.matchScore(p2) == 7 :
			"Match score should be 7 (Age diff. of 12).";
	}
}
