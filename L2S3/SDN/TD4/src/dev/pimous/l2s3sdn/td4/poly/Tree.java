package dev.pimous.l2s3sdn.td4.poly;

import java.io.PrintStream;

import dev.pimous.javautils.AutoToString;

public abstract class Tree extends AutoToString{

	public static final int INSTANCIATION_LIMIT = 4;
	
	private static int objectCounts = 0;

	{
		if(objectCounts++ == 4)
			throw new RuntimeException(
				"Too many tree created. Please, stop, we are not Ecosia!"
			);
	}

	// FUNCTIONS
	public final void afficher(PrintStream out){
		out.println(toString());
	}
}
