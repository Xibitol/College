package denree;
/**
 * Classe d'utilisation de Denrée.
 *
 * @author Xibitol
 */
public class AppDenree {
    public static void main(String[] args) {
        Denree p = new Denree();
		p.setNom("Pomme");
		p.setPrixHT(15.65);
		System.out.println(p);

		p.setPrixHT(1.42);
		System.out.println(p);

		p.setPrixTTC(1.99);
		System.out.println(p);
    }
}