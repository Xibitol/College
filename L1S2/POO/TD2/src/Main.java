public class Main {

	public static void main(String[] args){
		// Cours
		Cours bdw = new Cours("Bases du Web", 4);
		System.out.println(bdw);
		Cours pej = new Cours("Programmation en Java", 6);
		System.out.println(pej);

		// Etudiants
		Etudiant jfv = new Etudiant(
			1, "VIAUD", "Jean-François",
			"INFORMATIQUE",
			pej, bdw
		);
		System.out.println(jfv);

		Etudiant ab = new Etudiant(2, "BOURMAUD", "Anthony");
		ab.SInscrire(
			pej.getIntitule(), pej.getECTS(),
			bdw.getIntitule(), bdw.getECTS()
		);
		ab.setMention("MATHÉMATIQUES");
		System.out.println(ab);

		// Enseignants
		Enseignant apg = new Enseignant("ITOL", "Xib");
		apg.setCours(pej);
		System.out.println(apg);

		Enseignant fbm = new Enseignant(
			"BESSON-MERRIER", "Frédérique"
		);
		fbm.setCours(bdw);
		System.out.println(fbm);

		System.out.println(bdw);
		System.out.println(pej);
		System.out.println(jfv.memeCours(ab));
		System.out.println(jfv.mesProfs());
		// System.out.println(ab.mesProfs()); -> Error because there is no
		// Enseignant, but there are the same courses !
	}
}