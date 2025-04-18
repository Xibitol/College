public class TestEvalExpBool {
    public static void main(String[] args) {
        VarBool vA = new VarBool('A');
        VarBool vB = new VarBool('B');
        ExpBooleenne e1 = new ExpEt(vA, vB);

        // test 1 : evaluation de l'expression e1 avec A = vrai et B = faux
        ContexteEval ctxt = new ContexteEval();
        ctxt.affecte('A', true);
        ctxt.affecte('B', false);
        try {
            boolean result = e1.evalue(ctxt);
            if (result != false) {
                throw new Error("Probleme lors de l'evaluation de l'expression e1 : " + result);
            }
        } catch (VariableInconnueException vie) {
            throw new Error(vie.toString());
        }

        // test 2 : verification de la bonne gestion de l'exception VariableInconnueException
        VarBool vC = new VarBool('C');
        ExpBooleenne e2 = new ExpEt(vA, new ExpOu(vB, new ExpNegation(vC)));
        try {
            e2.evalue(ctxt);
            throw new Error("La variable C n'est pas connue, l'exception VariableInconnue doit etre declenchee");
        } catch (VariableInconnueException vie) {
        }

        // test 3 : evaluation de l'expression e2 avec A = vrai et B = faux et C = faux
        ctxt.affecte('C', false);
        try {
            boolean result = e2.evalue(ctxt);
            if (result != true) {
                throw new Error("Probleme lors de l'evaluation de l'expression e2 : " + result);
            }
        } catch (VariableInconnueException vie) {
            throw new Error(vie.toString());
        }

        // test 4 : evaluation de l'expression e3 avec A = vrai et B = faux et C = faux
        ExpBooleenne e3
                = new ExpNegation(
                new ExpEt(vA,
                        new ExpEt(new ExpOu(vB, ExpConstante.FAUX), vC)
                )
        );
        try {
            boolean result = e3.evalue(ctxt);
            if (result != true) {
                throw new Error("Probleme lors de l'evaluation de l'expression e3 : " + result);
            }
        } catch (VariableInconnueException vie) {
            throw new Error(vie.toString());
        }

        // test 5 : evaluation de l'expression e3 avec un autre contexte A = vrai et B = vrai et C = vrai
        ContexteEval ctxt2 = new ContexteEval();
        ctxt2.affecte('A', true);
        ctxt2.affecte('B', true);
        ctxt2.affecte('C', true);
        try {
            boolean result = e3.evalue(ctxt2);
            if (result != false) {
                throw new Error("Probleme lors de l'evaluation de l'expression e3 : " + result);
            }
        } catch (VariableInconnueException vie) {
            throw new Error(vie.toString());
        }

        System.out.println("Tests OK...");
    }
}
