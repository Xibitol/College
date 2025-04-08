public class AppTest {
	
	public static void main(String[] args) {
		/* COMPTE */
		log("--- COMPTE ---");

		Compte c = new Compte(0);
		log("Numéro : ", c.getNumero());
		c.depot(100.);
		log("Montant : ", c.consulte());
		c.retrait(20.);
		log("Montant : ", c.consulte());
		log(c);

		/* UTILISATEUR */
		log("--- UTILISATEUR ---");
		Utilisateur u = new Utilisateur("XI");
		Compte epargne = new Compte(1, u, 1000.);
		log(epargne);

		log("COURANT PRINCIPAL");
		u.setCompteCourantPrincipal(c);
		u.setCompteEpargneSecondaire(epargne);
		log("Courant : ", c.getPrincipal());
		log("Epargne : ", epargne.getSecondaire());

		log("COURANT SECONDAIRE");
		u.setCompteCourantSecondaire(c);
		u.setCompteEpargnePrincipal(epargne);
		log("Courant : ", c.getSecondaire());
		log("Epargne : ", epargne.getPrincipal());

		log("PORTEFEUILLE");
		u.empoche(100.);
		log("(+100) ", u);
		u.depense(20.);
		log("(-20) ", u);

		/* GUICHET AUTOMATIQUE */
		log("--- GUICHET AUTOMATIQUE ---");
		GuichetAutomatique ga = new GuichetAutomatique();
		Utilisateur v = new Utilisateur("FBM");
		v.setCompteCourantPrincipal(new Compte(2, v, 100.));
		v.setCompteEpargnePrincipal(new Compte(3, v, 100.));

		ga.depot(u, 10.);
		log("Courant (+10) : ", u);
		ga.retrait(u, 20.);
		log("Courant (-20) : ", u);

		ga.depot(epargne, u, 10.);
		log("Epargne (+10) : ", u);
		ga.retrait(epargne, u, 20.);
		log("Epargne (-20) : ", u);

		ga.virement(c, epargne, 10.);
		log(c, " -(10)> ", epargne);
		ga.virement(u, v, 10.);
		log(u, " -(10)> ", v);
		ga.virement(u, 10.);
		log("Epargne -(10)> Courant : ", u);
		ga.virementEpargne(u, 10.);
		log("Courant -(10)> Epargne : ", u);
	}

	private static void log(Object... msg){
		StringBuilder sb = new StringBuilder(msg[0].toString());
		for(int i = 1; i < msg.length; i++){
			sb.append(msg[i].toString());
		}
		System.out.println(sb.toString());
	}
}
