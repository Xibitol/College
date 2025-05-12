package dev.pimous.l2s4ri.tp3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Compagnons{

	private static final int PORT = 31124;
	private static final String END_MESSAGE = "stop";

	// FUNCTIONS
	public void main(String[] args){
		ThreadPoolExecutor tpe = new ThreadPoolExecutor(
			2, 2,
			0, TimeUnit.SECONDS,
			new LinkedBlockingQueue<>()
		);

		tpe.execute(new ClientWorker());
		tpe.execute(new ServerWorker());

		tpe.close();
	}

	// INNER CLASSES
	public static class ClientWorker implements Runnable{

		private static final String MESSAGE = "How are you today?";

		@Override
		public void run(){
			System.out.printf("Client connecting to port %d...\n", PORT);

			try(Socket s = new Socket(InetAddress.getLoopbackAddress(), PORT)){
				try{
					OutputStream os = s.getOutputStream();
					os.write(MESSAGE.length());
					os.write(MESSAGE.getBytes());
				}catch(IOException e){
					System.err.println("Client cannot send its message;");
				}

				try{
					InputStream is = s.getInputStream();
					int length = is.read();

					System.out.printf(
						"Received from server: '%s'.\n",
						new String(s.getInputStream().readNBytes(length))
					);
				}catch(IOException e){
					System.err.println("Client cannot read the response;");
				}

				try{
					OutputStream os = s.getOutputStream();
					os.write(END_MESSAGE.length());
					os.write(END_MESSAGE.getBytes());
				}catch(IOException e){
					System.err.println("Client cannot send its end message;");
				}
			}catch(IOException e){
				System.err.println("Client cannot work with server;");
			}

			System.out.println("Client terminated.");
		}
	}

	public static class ServerWorker implements Runnable{

		private static final String MESSAGE =
			"I'm good; You are the first one asking me that... thanks.";

		@Override
		public void run(){
			try(ServerSocket ss = new ServerSocket(PORT)){
				System.out.printf("Server listening on port %d...\n",
					ss.getLocalPort()
				);

				try(Socket s = ss.accept()){
					while(!s.isClosed()){
						String msg = END_MESSAGE;

						try{
							InputStream is = s.getInputStream();
							int length = is.read();

							msg = new String(
								s.getInputStream().readNBytes(length)
							);

							System.out.printf(
								"Received from client: '%s'.\n", msg
							);
						}catch(IOException e){
							System.err.println(
								"Server cannot read the message;"
							);
						}

						if(!msg.equals(END_MESSAGE)){
							try{
								OutputStream os = s.getOutputStream();
								os.write(MESSAGE.length());
								os.write(MESSAGE.getBytes());
							}catch(IOException e){
								System.err.println(
									"Server cannot send its message;"
								);
							}
						}else
							s.close();
					}
				}catch(IOException e){
					System.err.println("Server cannot work with client;");
				}
			}catch(IOException e){
				System.err.println("Server cannot listen for connections;");
			}

			System.out.println("Server terminated.");
		}
	}
}
