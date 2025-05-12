package dev.pimous.l2s4ri.tp3;

class Launcher{

	public static void main(String[] args) {
		Thread exo1 = new Thread(() -> new Handshaker().main(args));
		exo1.start();

		new Compagnons().main(args);

		new RepondeurPerformant().main(args);

		try{
			exo1.join();
		}catch(InterruptedException e){}
	}
}
