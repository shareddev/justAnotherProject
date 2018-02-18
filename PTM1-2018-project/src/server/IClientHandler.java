package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface IClientHandler {
	public void handleClient(InputStream input,OutputStream output) throws IOException;
	public ISolver getSolver();
	
}	
