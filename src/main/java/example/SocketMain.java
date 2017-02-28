package example;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketMain {
	private static final Logger logger = LogManager.getLogger(SocketMain.class.getName());
	
	public static void main(String[] args) {
		ExecutorService executors = Executors.newFixedThreadPool(4);
		
		try (ServerSocket serverSocket = new ServerSocket(5050, 0, InetAddress.getByName("localhost"))) {
			logger.info("Server started");
			
			for (int i = 0; i < 4; i++) {
				Runnable task = () -> {
					try {
						while (true) {
							Socket socket = serverSocket.accept();
							
							ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
							String result = (String) ois.readObject();
							
							if ("Bue.".equals(result)) {
								socket.close();
								return;
							}
							
							ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
							oos.writeObject(result);
						}
					} catch (Exception e) {
						logger.log(Level.ERROR, e);
					}
				};
				
				executors.submit(task);
			}
			
			executors.shutdown();
		} catch (IOException e) {
			logger.log(Level.ERROR, e);
		}
		
		while (!executors.isShutdown()) {
			
		}
	}
}
