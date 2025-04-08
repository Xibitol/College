public class AppScores {
    
    /**
     * Méthode main permettant de tester le projet.
     * 
     * Si les appels aux méthodes ne donnent pas le bon résultat,
     * alors une exception est levée.
     * 
     * Assurez vous de passer tous les tests !
     * 
     * @param args : pas utilisé
     */
    public static void main(String[] args) {
        System.out.println("=== CLASSE PERSONNE ===");

        Personne georges = new Personne(1, "Windsor", "Georges", 12);
        if (!(georges.getNumero() == 1)) {
            throw new Error("Erreur de la méthode getNumero");
        }
        if (!georges.getNom().equals("Windsor")) {
            throw new Error("Erreur de la méthode getNom");
        }
        if (!georges.getPrenom().equals("Georges")) {
            throw new Error("Erreur de la méthode getPrenom");
        }
        if (!georges.toString().equals("Windsor Georges")) {
            throw new Error("Erreur de la méthode toString");
        }

        System.out.println("=== CLASSE COLLECTIONPERSONNES ===");
        // Ces tests sont insuffisants ... mais c'est un début.
        /* L'énoncé ne précise pas ce qu'il faut faire lorsqu'il y a 
           des doublons ... dont on ne teste pas cette situation. etc.
        */
        CollectionPersonnes communaute = new CollectionPersonnes();

        if (!(communaute.personneDeNumero(1) == null)) {
            throw new Error("Erreur de la méthode personneDeNumero");
        }
        if (!(communaute.personneDeNom("Windsor") == null)) {
            throw new Error("Erreur de la méthode personneDeNom");
        }

        communaute.add(georges);
        if (!(communaute.getCollection().size() == 1)) {
            throw new Error("Erreur de la méthode add");
        }

        if (!(communaute.personneDeNumero(1) == georges)) {
            throw new Error("Erreur de la méthode personneDeNumero");
        }
        if (!(communaute.personneDeNom("Windsor") == georges)) {
            throw new Error("Erreur de la méthode personneDeNom");
        }
        if (!(communaute.personneDeNumero(2) == null)) {
            throw new Error("Erreur de la méthode personneDeNumero");
        }
        if (!(communaute.personneDeNom("Bourbon") == null)) {
            throw new Error("Erreur de la méthode personneDeNom");
        }

        Personne louis = new Personne(2, "Bourbon", "Louis", 93);
        communaute.add(louis);
        if (!(communaute.personneDeNumero(2) == louis)) {
            throw new Error("Erreur de la méthode personneDeNumero");
        }
        if (!(communaute.personneDeNom("Bourbon") == louis)) {
            throw new Error("Erreur de la méthode personneDeNom");
        }
        if (!(communaute.personneDeNumero(3) == null)) {
            throw new Error("Erreur de la méthode personneDeNumero");
        }
        if (!(communaute.personneDeNom("Tudor") == null)) {
            throw new Error("Erreur de la méthode personneDeNom");
        }        
        if (!(communaute.personnePlusAgee() == louis)) {
            throw new Error("Erreur de la méthode personnePlusAgee");
        }
        if (!(communaute.nbPersonneAge(12) == 1)) {
            throw new Error("Erreur de la méthode nbPersonneAge");
        }

        Personne henry = new Personne(2, "Tudor", "Henry", 12);
        communaute.add(henry);
        if (!(communaute.nbPersonneAge(12) == 2)) {
            throw new Error("Erreur de la méthode nbPersonneAge");
        }
        if (!(communaute.nbPersonneAge(1) == 0)) {
            throw new Error("Erreur de la méthode nbPersonneAge");
        }

        communaute.afficherPersonnes();

        System.out.println(" --- FIN AFFICHER --- ");

        System.out.println(communaute);

        System.out.println("BRAVO ! Vous avez passé tous les tests !");
    }
}