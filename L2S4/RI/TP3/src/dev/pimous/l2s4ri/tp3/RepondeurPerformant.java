package dev.pimous.l2s4ri.tp3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RepondeurPerformant{

	private static final int PORT = 31128;
	private static final int CLIENT_COUNT = 10;
	private static final String END_MESSAGE = "stop";

	// FUNCTIONS
	public void main(String[] args){
		ThreadPoolExecutor tpe = new ThreadPoolExecutor(
			2, 1 + CLIENT_COUNT,
			0, TimeUnit.SECONDS,
			new LinkedBlockingQueue<>()
		);

		tpe.execute(new ServerWorker());

		for(int i = 0; i < CLIENT_COUNT; i++)
			tpe.execute(new ClientWorker(i));

		tpe.close();
	}

	// INNER CLASSES
	public static class ClientWorker implements Runnable{

		private static final List<String> MESSAGES = List.of(
			"I miss you.",
			"What time is it?",
			"Where do you think you're going?",
			"Is there something I can do for you?",
			"Is the moon flirting with the sun?",
			"You might be my next murder.",
			"If someone ask you the time, don't awnser!",
			"Hi.",
			"..."
		);

		private int index;

		public ClientWorker(int index){
			this.index = index;
		}

		// FUNCTIONS
		@Override
		public void run(){
			String msg = MESSAGES.get((int) (Math.random()*MESSAGES.size()));

			try(Socket s = new Socket(InetAddress.getLoopbackAddress(), PORT)){
				try{
					OutputStream os = s.getOutputStream();
					os.write(msg.length());
					os.write(msg.getBytes());
				}catch(IOException e){
					System.err.println(
						"Client %d cannot send its message;".formatted(index)
					);
				}

				try{
					InputStream is = s.getInputStream();
					int length = is.read();

					System.out.printf(
						"[CLIENT n°%d] Received from server: '%s'.\n",
						index, new String(s.getInputStream().readNBytes(length))
					);
				}catch(IOException e){
					System.err.println(
						"Client %d cannot read the response;".formatted(index)
					);
				}

				try{
					OutputStream os = s.getOutputStream();
					os.write(END_MESSAGE.length());
					os.write(END_MESSAGE.getBytes());
				}catch(IOException e){
					System.err.println(
						"Client %d cannot send its end message;".formatted(
							index
						)
					);
				}
			}catch(IOException e){
				System.err.println(
					"Client %d cannot work with server;".formatted(index)
				);
			}
		}
	}

	public static class ServerWorker implements Runnable{

		@Override
		public void run(){
			ThreadPoolExecutor tpe = new ThreadPoolExecutor(
				2, 4,
				0, TimeUnit.SECONDS,
				new LinkedBlockingDeque<>()
			);

			try(ServerSocket ss = new ServerSocket(PORT)){
				System.out.printf("Server listening on port %d...\n",
					ss.getLocalPort()
				);

				while(!ss.isClosed()){
					try{
						tpe.execute(new ServerConnHandler(ss.accept()));
					}catch(IOException e){
						System.err.println(
							"Server cannot connect to client;"
						);
					}
				}
			}catch(IOException e){
				System.err.println("Server cannot listen for connections;");
			}

			tpe.close();
			System.out.println("Server terminated.");
		}
	}
	public static class ServerConnHandler implements Runnable{

		private static final String RESPONSE_FORMAT = "You said that: '%s'";

		private Socket socket;

		public ServerConnHandler(Socket socket){
			this.socket = socket;
		}

		// FUNCTIONS
		@Override
		public void run() {
			while(!socket.isClosed()){
				String msg = END_MESSAGE;

				try{
					InputStream is = socket.getInputStream();
					int length = is.read();

					msg = new String(
						socket.getInputStream().readNBytes(length)
					);
				}catch(IOException e){
					System.err.println(
						"[%s] Server cannot read a request;".formatted(
							Thread.currentThread().getName()
						)
					);
				}

				if(!msg.equals(END_MESSAGE)){
					try{
						msg = RESPONSE_FORMAT.formatted(msg);

						OutputStream os = socket.getOutputStream();
						os.write(msg.length());
						os.write(msg.getBytes());
					}catch(IOException e){
						System.err.println(
							"[%s] Server cannot send its message;".formatted(
								Thread.currentThread().getName()
							)
						);
					}
				}else{
					try{
						socket.close();
					}catch(IOException e){
						System.err.println(
							"[%s] Server cannot close a connection;".formatted(
								Thread.currentThread().getName()
							)
						);
					}
				}
			}
		}
	}
}
