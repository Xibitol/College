public class TestToString{
	
	public static void main(String[] args){
		System.out.println("toString() Ok...");

		EltMM dvd = new DVD("Phone Booth", "Joel Schumacher", 81);
		EltMM cd = new CD("Intervalle", "Remaax", 8, 35);

		dvd.changeEtatRayon(false);
		cd.ajouteCommentaires("Sympa.");

		System.out.println(dvd.toString());
		System.out.println(cd.toString());
	}
}
