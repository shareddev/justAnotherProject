package server;

import java.io.InputStream;
import java.io.OutputStream;

//handles the client
public interface IClientHandler {
	void handle(InputStream input,OutputStream output);
	
}
