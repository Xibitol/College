package fr.lru.jeu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import fr.lru.observer.Observable;

public class GameEventManager extends Observable{

    private final List<String> evenements;
    private final ScheduledExecutorService scheduler;
    private final Random random;

    public GameEventManager() {
        // Liste d'événements possibles
        this.evenements = new ArrayList<>();
        this.random = new Random();
        this.scheduler = Executors.newScheduledThreadPool(1);

        this.evenements.add("Un dragon apparaît !");
        this.evenements.add("Une tempête magique éclate !");
        this.evenements.add("Un portail démoniaque s'ouvre !");
        this.evenements.add("Un coffre légendaire est découvert !");
        this.evenements.add("Un monstre rare fait son apparition !");
        this.evenements.add("La nuit tombe et les créatures deviennent plus puissantes !");
    }

    // Méthode qui démarre la boucle d'événements toutes les secondes
    public void start() {
        this.scheduler.scheduleAtFixedRate(() -> {
            String evenement = this.evenements.get(random.nextInt(this.evenements.size()));
            this.genererEvenement(evenement);
        }, 0, 1, TimeUnit.SECONDS); // Délai initial : 0 sec, puis toutes les 1 sec
    }

    // Méthode pour générer un événement
    public void genererEvenement(String evenement) {
        System.out.println("⚡ Événement du jeu : " + evenement);

		notify(evenement);
    }

    // Méthode pour arrêter la boucle (utile pour arrêter proprement le jeu)
    public void stop() {
        if (scheduler != null) {
            scheduler.shutdown();
            System.out.println("🛑 La boucle d'événements a été arrêtée.");
        }
    }
}
