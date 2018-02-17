package server;

public interface IServer {
	
	void start(ClientHandler clientHandler);
	void stop();

}
