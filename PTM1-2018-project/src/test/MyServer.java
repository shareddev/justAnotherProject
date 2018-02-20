package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MyServer implements IServer {
	
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
			//run private method from start();
			run();
		} catch (Exception e) {}
	}
	
	public void stop() {
		stop = true; 
	}
		
	private void run() throws IOException {
		ServerSocket serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(10000);

		while(!stop)
		{
			try {
				Socket clientSocket = serverSocket.accept(); 
				InputStream inFromClient=clientSocket.getInputStream();
				OutputStream outToClient=clientSocket.getOutputStream();
				
				clientHandler.handleClient(inFromClient, outToClient);
				
				inFromClient.close();
				outToClient.close();
				clientSocket.close();
				} catch (SocketTimeoutException e) {}
			
				finally {		
					this.stop();
					serverSocket.close();
				}
		}	
	}
	
}
