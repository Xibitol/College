package dev.pimous.l2s3sdn.tp3.game;

import java.io.PrintStream;

import dev.pimous.l2s3sdn.tp3.Random;

public final class Simulation{

	private static final byte GROUP_MIN_SIZE = 1;
	private static final byte GROUP_MAX_SIZE = 5;

	private PrintStream out;
	private byte groupCount;
	
	public Simulation(PrintStream out, byte groupCount){
		this.out = out;
		this.groupCount = groupCount;
	}

	// FUNCTIONS
	public void test(PUB pub){
		Group[] remainingGroups = new Group[groupCount];

		for(int i = 0; i < groupCount; i++){
			remainingGroups[i] = new Group(
				(byte) Random.randint(GROUP_MIN_SIZE, GROUP_MAX_SIZE),
				(short) Random.randint(0, pub.getOpenDuration() - 1)
			);
		}

		// Start
		out.printf(
			"\033[1;33mStarting test of your PUB %s.\033[0m\n",
			pub.getName()
		);

		

		out.println("\033[1;31mTest failed.\033[0m");
	}
}
