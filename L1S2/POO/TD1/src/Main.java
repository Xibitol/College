public class Main {

	public static void main(String[] args){
		// 2.2. Compréhension de code
		Etudiant jfv;
		jfv = new Etudiant();
		jfv.setNumero(1);
		jfv.setNom("VIAUD");
		jfv.setPrenom("Jean-François");
		jfv.setECTS(15);

		String affichage;
		affichage = "Nom de l'étudiant : " + jfv.getNom();
		System.out.println(affichage);
		System.out.println(jfv.toString());

		// 3.2. Utilisation
		Cours bdw = new Cours();
		bdw.setIntitule("Bases du Web");
		bdw.setECTS(4);
		System.out.println(bdw.toString());
		bdw.setECTS(6);
		System.out.println(bdw.toString());

		// 4. Communication entre objets
		Cours pej = new Cours();
		pej.setIntitule("Programmation en Java");
		pej.setECTS(6);
		System.out.println(pej);

		AutoEtudiant ae = new AutoEtudiant();
		ae.setNumero(2);
		ae.setNom("BOURMAUD");
		ae.setPrenom("Anthony");
		ae.setCours1(pej);
		ae.setCours2(bdw);
		System.out.println("ECTS : " + ae.getECTS());
		System.out.println(ae.toString());
	}
}