package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MyClientHandler implements IClientHandler {
	
	//variables
	private ICashManager cashManager;
	//some people created these two variables INSIDE their handle method, why?
	BufferedReader bufferReader;
	BufferedWriter bufferWriter;
	//buffer string to handle messages from and to the client
	private String inputBuffer;
	private String OutputBuffer;
	
	//C'Tor
	public MyClientHandler(InputStream input, OutputStream output) {
		
	}
	
	//Methods
	@Override
	//gets a client input and output from IServer
	public void handle(InputStream input, OutputStream output) {
		//converts the input and output stream into Strings
		bufferReader = new BufferedReader(new InputStreamReader(input));
		//other people from class said it's better to use PrintWriter, need to test
		bufferWriter = new BufferedWriter(new OutputStreamWriter(output));
		
		
	}

}
