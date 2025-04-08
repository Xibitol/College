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
        GameEventManager manager = new GameEventManager(log);
		Collection<Entite> entities = Collections.emptyList();

		try{
			entities = Fabrique.getInstance().fromResource(
				"characters_and_monsters.txt"
			);
			entities.forEach(manager::ajouterObserver);
		}catch(IOException e){
			log.log(Level.SEVERE, "Impossible de charger les entités.");
		}

        manager.start();

        // Attente de 10 secondes avant de quitter
        try{
            Thread.sleep(10000);  // Attend 10 secondes
        }catch(InterruptedException e){
            log.log(Level.SEVERE, "Exception pendant le sleep : {0}",
				e.getMessage()
			);

			Thread.currentThread().interrupt();
        }

        manager.stop();
    }
}
