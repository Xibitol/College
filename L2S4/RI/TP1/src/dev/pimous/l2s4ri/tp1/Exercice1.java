package dev.pimous.l2s4ri.tp1;

public class Exercice1{

	public Exercice1(){}
	
	// FUNCTIONS
	public void main(String[] args){
		Runtime r = Runtime.getRuntime();

		System.out.printf("Processors: %d\n", r.availableProcessors());

		System.out.printf("Memory:\n");
		System.out.printf("\t-> -Xmx=%dB\n", r.maxMemory());
		System.out.printf("\t-> Total mem: %dB\n", r.totalMemory());
		System.out.printf("\t-> Free mem: %dB\n", r.freeMemory());

		System.out.printf("Properties:\n");
		System.out.printf("\t-> ENV:\n");
		System.getenv().forEach((k, v) -> System.out.printf(
			"\t\t-> %s: '%s';\n", k, v
		));
		System.out.printf("\t-> Java:\n");
		System.getProperties().forEach((k, v) -> System.out.printf(
			"\t\t-> %s: '%s';\n", k, v
		));
	}
}
