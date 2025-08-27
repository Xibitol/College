package dev.pimous.l2s4ri.tp4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.channels.ClosedByInterruptException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class RepondeurIncertain{

	private static final int PORT = 31128;
	private static final char END_CHARACTER = 'q';

	// FUNCTIONS
	public void main(String[] args){
		Thread server = new Thread(new ServerWorker());
		server.start();

		bindTerminal();

		while(server.isAlive()){
			try{
				server.join();
			}catch(InterruptedException e){}
		}
	}

	private void bindTerminal(){
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		DatagramPacket dp;
		String buffer;

		try(DatagramSocket ds = new DatagramSocket()){
			while(!ds.isClosed()){
				buffer = sc.next();
				dp = new DatagramPacket(new byte[1], 1,
					InetAddress.getLoopbackAddress(), PORT
				);

				int i = 0;
				do{
					try{
						dp.setData(new byte[]{(byte) buffer.charAt(i++)});
						ds.send(dp);
					}catch(IOException e){
						System.err.println(
							"Client cannot send its character;"
						);
					}
				}while(i < buffer.length()
					&& buffer.charAt(i - 1) != END_CHARACTER
				);

				if(buffer.charAt(i - 1) == END_CHARACTER)
					ds.close();
			}
		}catch(IOException e){
			System.err.println("Client cannot work with server;");
		}

		sc = null;
	}

	// INNER CLASSES
	public static class ServerWorker implements Runnable{

		private static final String RESPONSE_FORMAT = "[%1$tDT%1$tT] '%2$s'\n";

		@Override
		public void run(){
			DatagramPacket dp = new DatagramPacket(
				new byte[1], 1
			);

			try(DatagramSocket dss = new DatagramSocket(PORT)){
				System.out.printf("Server listening on port %d...\n",
					dss.getLocalPort()
				);

				while(!dss.isClosed()
					&& !Thread.currentThread().isInterrupted()
				){
					try{
						dss.receive(dp);
						char msg = (char) dp.getData()[0];

						System.out.printf(RESPONSE_FORMAT,
							LocalDateTime.now(), msg
						);

						if(msg == END_CHARACTER)
							dss.close();
					}catch(ClosedByInterruptException e){
						throw e;
					}catch(IOException e){
						System.err.println(
							"Server cannot receive from client;"
						);
					}
				}
			}catch(ClosedByInterruptException e){
			}catch(IOException e){
				System.err.println("Server cannot listen for packets;");
			}

			System.out.println("Server terminated.");
		}
	}
}
