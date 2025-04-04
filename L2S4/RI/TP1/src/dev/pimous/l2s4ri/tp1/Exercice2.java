package dev.pimous.l2s4ri.tp1;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

import dev.pimous.l2s4ri.tp1.ex2.ReaderWorker;
import dev.pimous.l2s4ri.tp1.ex2.WriterWorker;

public class Exercice2{

	private static final String text = "Cette suite de charactères n'a pas correctement été transmise. :)";

	public Exercice2(){}
	
	// FUNCTIONS
	public void main(String[] args){
		try(PipedReader pipe = new PipedReader()){
			PipedWriter pipeWriter = new PipedWriter(pipe);

			Thread writerT = new WriterWorker(pipeWriter, text);
			Thread readerT = new ReaderWorker(pipe);

			readerT.start();
			writerT.start();

			writerT.join();
			pipeWriter.close();

			readerT.join();
		}catch(InterruptedException|IOException ignored){}
	}
}
