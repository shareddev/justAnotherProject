package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer implements IServer {
	
	ServerSocket serverSocket;
	Socket clientSocket;
	private int port;
	private IClientHandler clientHandler;
	private boolean stop; // default value is false, no need to initialize
	
	public MyServer(int port) {
		this.port = port;
		this.stop = false;  
	}
	
	@Override
	public void start(IClientHandler clientHandler) {
		this.clientHandler = clientHandler;
		try {
			serverSocket = new ServerSocket(this.port);
			//serverSocket.setSoTimeout(10 * 1000);
			
			while(!this.stop) {
				//waiting for connection from client.
				clientSocket = serverSocket.accept();
				
				//after accepting a client, we send them
				//to the IClientHandler
				clientHandler.handle(clientSocket);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
