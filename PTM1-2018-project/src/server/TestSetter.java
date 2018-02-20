package server;

// edit these imports according to your project
import server.ICacheManager;
import server.IClientHandler;
import server.MyCacheManager;
import server.MyClientHandler;
import server.MyServer;
import server.MySolver;
import server.IServer;
import server.ISolver;

public class TestSetter {
	
	public static void setClasses(DesignTest dt){
		
		// set the server's Interface, e.g., "Server.class"
		// don't forget to import the correct package e.g., "import server.Server"
		dt.setServerInteface(IServer.class);
		// now fill in the other types according to their names
		dt.setServerClass(MyServer.class);
		dt.setClientHandlerInterface(IClientHandler.class);
		dt.setClientHandlerClass(MyClientHandler.class);
		dt.setCacheManagerInterface(ICacheManager.class);
		dt.setCacheManagerClass(MyCacheManager.class);
		dt.setSolverInterface(ISolver.class);
		dt.setSolverClass(MySolver.class);
	}
	
	// run your server here
	static Server s;
	public static void runServer(int port){
		s=new MyServer(port);
		s.start(new MyClientHandler(new MyCashManager(System.getProperty("user.dir") + "\\pipeSolutions\\"))));
	}
	// stop your server here
	public static void stopServer(){
		s.stop();
	}

}
