public class TestSimplifieEltNeutreExpBool {
    public static void main(String[] args) throws VariableInconnueException {
        VarBool vA = new VarBool('A');
        VarBool vB = new VarBool('B');
        ExpBooleenne e1
                = new ExpNegation(
                new ExpEt(vA,
                        new ExpEt(new ExpOu(vB, ExpConstante.FAUX),
                                ExpConstante.VRAI
                        )
                )
        );
        ExpBooleenne e1SimplifieeRef = new ExpNegation(new ExpEt(vA, vB));

        // test 1 : simplification de e1
        ExpBooleenne e1Simplifiee = e1.simplifieEltNeutre();
        if (! e1Simplifiee.equals(e1SimplifieeRef)) {
            throw new Error("Probleme lors de la simplification (elt neutre) de e1 : " + e1Simplifiee);
        }

        // test 2 : simplification de e2
        // ici on inverse volontairement A et B pour montrer que lors de la comparaison :
        // A ou B <=> B ou A
        ExpBooleenne e2 = new ExpOu(vA, new ExpEt(vB, ExpConstante.VRAI));
        ExpBooleenne e2SimplifieeRef = new ExpOu(vB, vA);

        ExpBooleenne e2Simplifiee = e2.simplifieEltNeutre();
        if (! e2Simplifiee.equals(e2SimplifieeRef)) {
            throw new Error("Probleme lors de la simplification (elt neutre + equals) de e2 : " + e2Simplifiee);
        }

        // test 3 : simplification de e3 => vrai
        ExpBooleenne e3 =
                new ExpEt(
                        ExpConstante.VRAI,
                        new ExpEt(
                                new ExpOu(ExpConstante.VRAI, ExpConstante.FAUX),
                                ExpConstante.VRAI
                        )
                );

        ExpBooleenne e3Simplifiee = e3.simplifieEltNeutre();
        if (! e3Simplifiee.equals(ExpConstante.VRAI)) {
            throw new Error("Probleme lors de la simplification (elt neutre) de e3 : " + e3Simplifiee);
        }

        // test 4 : simplification de e4 (A et (B ou C)) ou ((C ou B) et A)
        ExpBooleenne e4 =
			new ExpOu(
				new ExpEt(vA, new ExpOu(vB, new VarBool('C'))),
				new ExpEt(new ExpOu(new VarBool('C'), vB), vA)
			);
		ExpBooleenne e4Final = new ExpEt(vA,
			new ExpOu(vB, new VarBool('C'))
		);

        ExpBooleenne e4Simplifiee = e4.simplifieEltNeutre();
        if (! e4Simplifiee.equals(e4Final)) {
            throw new Error("Probleme lors de la simplification (elt neutre + equals) de e4 : " + e4Simplifiee);
        }

        System.out.println("Tests OK...");
    }
}
