package dev.pimous.l2s4gl.fvsw;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import dev.pimous.l2s4gl.fvsw.factory.EntityFactory;
import dev.pimous.l2s4gl.fvsw.game.Entity;
import dev.pimous.l2s4gl.fvsw.game.GameEventManager;

class AppLauncher{

	private static final Logger log = Logger.getLogger("log");

	public static void main(String[] args){
		GameEventManager manager = new GameEventManager();
		Collection<Entity> entities = Collections.emptyList();

		try{
			entities = EntityFactory.get().fromResource(
				"characters_and_monsters.txt"
			);
		}catch(IOException e){
			e.printStackTrace();
		}

		entities.forEach(e -> manager.addObserver(e));

        manager.start();

        try {
            Thread.sleep(10000);
        }catch(InterruptedException e){
            log.log(Level.SEVERE, 
				"Exception pendant le sleep : {0}", e.getMessage()
			);
        }

        manager.stop();
	}
}