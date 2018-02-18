package server;

import java.io.InputStream;
import java.io.OutputStream;

public interface IClientHandler {
	public void handle(InputStream input,OutputStream output);
	
}	
