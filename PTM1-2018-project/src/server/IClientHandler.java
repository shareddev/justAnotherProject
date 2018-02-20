package server;

import java.io.InputStream;
import java.io.OutputStream;

public interface IClientHandler {
	public void handleClient(InputStream input,
			OutputStream output);
	public ISolver getSolver();
	
}	
