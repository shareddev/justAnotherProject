package server;

public interface IServer {
	public void start(IClientHandler clientHandler);

	public void stop();
}
