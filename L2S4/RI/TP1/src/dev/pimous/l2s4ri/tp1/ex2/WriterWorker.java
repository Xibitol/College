package dev.pimous.l2s4ri.tp1.ex2;

import java.io.IOException;
import java.io.PipedWriter;

public class WriterWorker extends Thread{

	private static final long delay = 500;

	private PipedWriter pipeWriter;
	private String text;

	public WriterWorker(PipedWriter pipeWriter, String text){
		super("Writer");

		this.pipeWriter = pipeWriter;
		this.text = text;
	}
	
	// FUNCTIONS
	@Override
	public synchronized void run(){
		System.out.printf("Writing will take %d seconds to finish.\n",
			text.length()*delay/1000
		);

		try{
			for(char c : text.toCharArray()){
				pipeWriter.write(c);

				try{
					wait(delay);
				}catch(InterruptedException ignored){}
			}
		}catch(IOException ignored){}
	}
}
