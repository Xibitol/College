package dev.pimous.l2s4ri.tp3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Handshaker{

	private static final int PORT = 31120;
	private static final String MESSAGE = "Bonjour!\n";

	// FUNCTIONS
	public void main(String[] args){
		try(ServerSocket ss = new ServerSocket(PORT)){
			System.out.printf("Port is %d.\n", ss.getLocalPort());

			while(!ss.isClosed()){
				try(Socket s = ss.accept()){
					s.getOutputStream().write(MESSAGE.getBytes());
				}catch(IOException e){
					System.err.println("Cannot send message;");
				}
			}
		}catch(IOException e){
			System.err.println("Cannot start listening server;");
		}

		System.out.println("Bye");
	}
}
