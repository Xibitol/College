package fr.lru.jeu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import fr.lru.observer.Observable;

public class GameEventManager extends Observable{

    private final List<String> evenements = new ArrayList<>();
	@SuppressWarnings("squid:S2245")
    private final Random random = new Random();
    private final ScheduledExecutorService scheduler =
		Executors.newScheduledThreadPool(1);
	
	private final Logger log;

    public GameEventManager(Logger logger){
		this.log = logger;

        this.evenements.add("Un dragon apparaît !");
        this.evenements.add("Une tempête magique éclate !");
        this.evenements.add("Un portail démoniaque s'ouvre !");
        this.evenements.add("Un coffre légendaire est découvert !");
        this.evenements.add("Un monstre rare fait son apparition !");
        this.evenements.add("La nuit tombe et les créatures deviennent plus puissantes !");
    }

	// GETTERS
	public Logger getLogger(){ return log; }

	// FUNCTIONS
    // Méthode qui démarre la boucle d'événements toutes les secondes
    public void start() {
        this.scheduler.scheduleAtFixedRate(() -> {
            String evenement = this.evenements.get(random.nextInt(this.evenements.size()));
            this.genererEvenement(evenement);
        }, 0, 1, TimeUnit.SECONDS); // Délai initial : 0 sec, puis toutes les 1 sec
    }

    // Méthode pour générer un événement
    public void genererEvenement(String evenement){
		log.info(() -> "⚡ Événement du jeu : " + evenement);

		notify(evenement);
    }

    // Méthode pour arrêter la boucle (utile pour arrêter proprement le jeu)
    public void stop() {
		scheduler.shutdown();
		log.info("🛑 La boucle d'événements a été arrêtée.");
    }
}
