public class TestEltMM {
    public static void main(String[] args) throws Error {
        Class<EltMM> classeEltMM = EltMM.class;
        // Detection d'attributs non privés dans la classe EltMM...
        int nbMethodes = classeEltMM.getFields().length;
        if (nbMethodes != 0) {
            throw new Error("Probleme : la classe EltMM contient des attributs publics !...");
        }

        // Verification des accesseurs devant etre presents dans EltMM
        try {
            classeEltMM.getDeclaredMethod("donneTitre", (Class<EltMM>[]) null);
            classeEltMM.getDeclaredMethod("donneDuree", (Class<EltMM>[]) null);
            classeEltMM.getDeclaredMethod("donneCommentaires", (Class<EltMM>[]) null);
            classeEltMM.getDeclaredMethod("toString", (Class<EltMM>[]) null);
        } catch (NoSuchMethodException nse) {
            throw new Error("Probleme : la classe EltMM n'a pas de méthode " + nse.getMessage());
        }
        System.out.println("Classe EltMM OK...");
    }
}
