package dev.pimous.l2s4ri.tp1.ex2;

import java.io.IOException;
import java.io.PipedReader;

public class ReaderWorker extends Thread{

	private PipedReader pipe;

	public ReaderWorker(PipedReader pipe){
		super("Reader");

		this.pipe = pipe;
	}
	
	// FUNCTIONS
	@Override
	public void run(){
		StringBuilder sb = new StringBuilder();

		int c;
		try{
			while((c = pipe.read()) != -1)
				sb.append(String.valueOf((char) c));
		}catch(IOException ignored){
			ignored.printStackTrace();
		}

		System.out.printf("Readed: '%s'\n", sb);
	}
}
