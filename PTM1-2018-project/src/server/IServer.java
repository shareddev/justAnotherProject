package server;

public interface IServer {
	
	void start(IClientHandler clientHandler);
	void stop();

}
