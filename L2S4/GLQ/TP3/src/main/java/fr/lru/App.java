package fr.lru;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.lru.fabrique.Fabrique;
import fr.lru.jeu.Entite;
import fr.lru.jeu.GameEventManager;

public class App {
    private static final Logger log = Logger.getLogger("log");

    public static void main( String[] args) {
        GameEventManager manager = new GameEventManager();
		Collection<Entite> entities = Collections.emptyList();

		try{
			entities = Fabrique.getInstance().fromResource(
				"characters_and_monsters.txt"
			);
			entities.forEach(e -> manager.ajouterObserver(e));
		}catch(IOException e){
			e.printStackTrace();
		}

        manager.start();

        // Attente de 10 secondes avant de quitter
        try {
            Thread.sleep(10000);  // Attend 10 secondes
        } catch (InterruptedException e) {
            log.log(Level.SEVERE, "Exception pendant le sleep : {0}", e.getMessage());
        }

        manager.stop();
    }
}
