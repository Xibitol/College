public class TestAffichageExpBool {

    public static void main(String[] args) {
        VarBool vA = new VarBool('A');
        VarBool vB = new VarBool('B');
        ExpBooleenne e1 = new ExpEt(vA, vB);

        // recuperation de la chaine representant e1 et elimination de tous les espaces
        String affichageExp_e1 = e1.toString().replace(" ", "");

        if (!affichageExp_e1.equals("(AetB)")) {
            throw new Error("Mauvais affichage de l'expression e1 :" + e1);
        }

        VarBool vC = new VarBool('C');
        ExpBooleenne e2 = new ExpEt(vA, new ExpOu(vB, new ExpNegation(vC)));

        // recuperation de la chaine representant e1 et elimination de tous les espaces
        String affichageExp_e2 = e2.toString().replace(" ", "");

        if (!affichageExp_e2.equals("(Aet(BounonC))")) {
            throw new Error("Mauvais affichage de l'expression e2 : " + e2);
        }

        ExpBooleenne e3
                = new ExpNegation(
                new ExpEt(vA,
                        new ExpEt(new ExpOu(vB, ExpConstante.FAUX),
                                  ExpConstante.VRAI
                        )
                )
        );
        // recuperation de la chaine representant e3 et elimination de tous les espaces
        String affichageExp_e3 = e3.toString().replace(" ", "");

        if (!affichageExp_e3.equals("non(Aet((Boufaux)etvrai))")) {
            throw new Error("Mauvais affichage de l'expression e3 : " + e3);
        }

        System.out.println("Tests OK...");
    }
}
