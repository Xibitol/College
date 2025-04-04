package dev.pimous.l2s4gl.fvsw.game;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import dev.pimous.l2s4gl.fvsw.observer.Observable;
import dev.pimous.l2s4gl.fvsw.observer.Observer;

public class GameEventManager implements Observable{

	private final ArrayList<String> events;
	private final ScheduledExecutorService scheduler;
    private final Random random;

	public GameEventManager(){
        this.events = new ArrayList<>();
        this.random = new Random();
        this.scheduler = Executors.newScheduledThreadPool(1);

        this.observers = new ArrayList<>();

        this.events.add("Un dragon apparaît !");
        this.events.add("Une tempête magique éclate !");
        this.events.add("Un portail démoniaque s'ouvre !");
        this.events.add("Un coffre légendaire est découvert !");
        this.events.add("Un monstre rare fait son apparition !");
        this.events.add(
			"La nuit tombe et les créatures deviennent plus puissantes !"
		);
	}

	// FUNCTIONS
	public void start(){
		this.scheduler.scheduleAtFixedRate(() -> {
            String evenement = this.events.get(
				random.nextInt(this.events.size())
			);

            this.genererEvenement(evenement);
        }, 0, 1, TimeUnit.SECONDS);
	}
	public void stop(){
		if(scheduler != null){
            scheduler.shutdown();
            System.out.println("🛑 La boucle d'événements a été arrêtée.");
        }
	}

	public void genererEvenement(String evenement) {
        System.out.println("⚡ Événement du jeu : " + evenement);

		notify(evenement);
    }
}