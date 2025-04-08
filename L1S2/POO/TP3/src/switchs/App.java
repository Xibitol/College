package switchs;

import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

/** Tests and runs in a game loop {@see Panneau}. */
public class App{
	
	public static void main(String[] args){
		// TestS
		Panneau p = new Panneau();
		log("Random Panneau : ", p);

		p.interrupteur1();
		log("Interrupteur 1 : ", p);
		p.interrupteur2();
		log("Interrupteur 2 : ", p);
		p.interrupteur3();
		log("Interrupteur 3 : ", p);
		p.interrupteur4();
		log("Interrupteur 4 : ", p);
		p.interrupteur5();
		log("Interrupteur 5 : ", p);

		log("All lights on ? ", p.allumees());
		p.reset();
		log("Reset : ", p);

		// Games
		log("----- NOW THE GAME !!! -----");
		p = new Panneau();
		Integer n = null;

		Scanner sc = new Scanner(System.in);
		log("Etat du panneau : ", p);
		// Not do{}while(); because we not check if the panel is already
		// completed
		while(!p.allumees()){
			do{
				System.out.print("Interrupteur à actionner : ");
				
				if(sc.hasNext(
					Pattern.compile("[1-5]")
				)) n = sc.nextInt();
				else{
					log(String.format(
						"Please enter an integer between 1 and 5 (not \"%s\").",
						sc.next()
					));
					n = null;
				}
			}while(Objects.isNull(n));

			switch(n){
				case 1:
					p.interrupteur1();
					break;
				case 2:
					p.interrupteur2();
					break;
				case 3:
					p.interrupteur3();
					break;
				case 4:
					p.interrupteur4();
					break;
				case 5:
					p.interrupteur5();
					break;
			}

			log("Etat du panneau : ", p);
		}
		sc.close();

		log("Gagné !");
	}

	private static void log(Object... msg){
		StringBuilder sb = new StringBuilder(msg[0].toString());
		for(int i = 1; i < msg.length; i++){
			sb.append(msg[i].toString());
		}
		System.out.println(sb.toString());
	}
}
