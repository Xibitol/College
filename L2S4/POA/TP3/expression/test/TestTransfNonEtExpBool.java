public class TestTransfNonEtExpBool {
    public static void main(String[] args) {
        // test 1 : elimination des OU pour les remplacer par une combinaison
        // de 'NON' et de 'ET'
        VarBool vA = new VarBool('A');
        VarBool vB = new VarBool('B');
        ExpBooleenne e1 = new ExpOu(vA, vB);
        ExpBooleenne e1sansOu
                = new ExpNegation(
                new ExpEt(
                        new ExpNegation(vA),
                        new ExpNegation(vB)));

        ExpBooleenne e1Transforme = e1.transformeNonEt();
        if (! e1Transforme.equals(e1sansOu)) {
            throw new Error("Probleme lors de la transformation de e1 : " + e1Transforme);
        }

        // test 2 : elimination des OU pour les remplacer par une combinaison
        // de 'NON' et de 'ET'
        VarBool vC = new VarBool('C');
        ExpBooleenne e2
                = new ExpNegation(new ExpOu(vA, new ExpEt(new ExpOu(vB, vC), ExpConstante.FAUX)));
        ExpBooleenne e2sansOu =
                new ExpNegation(
                        new ExpNegation(
                                new ExpEt(new ExpNegation(vA),
                                        new ExpNegation(
                                                new ExpEt(new ExpNegation(
                                                        new ExpEt(new ExpNegation(vB),
                                                                new ExpNegation(vC))
                                                ), ExpConstante.FAUX)
                                        ))
                        )
                );

        ExpBooleenne e2Transforme = e2.transformeNonEt();
        if (! e2Transforme.equals(e2sansOu)) {
            throw new Error("Probleme lors de la transformation de e2 : " + e2Transforme);
        }

        System.out.println("Tests OK...");
    }
}
