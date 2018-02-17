package server;

import java.io.IOException;
import java.net.ServerSocket;

public class MyServer implements IServer {
	
	ServerSocket serverSocket;
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
			server = new ServerSocket(port);
			server.setSoTimeout(10 * 1000);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
